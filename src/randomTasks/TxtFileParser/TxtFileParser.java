package randomTasks.TxtFileParser;

import homework_4_29_10.MyList.MyList;

import java.io.FileReader;
import java.util.*;

public class TxtFileParser {
    private Map<String, Word> wordMap;
    private Map<String, Phrase> phraseMap;
    private Map<Character, Integer> charMap;
    private Map<Integer, List<String>> wordsByCharNum;
    private Map<Integer, List<String>> wordsByCharNumNoStopWords;
    private long totalLetterCount;
    private MyList<Word> topTenWords;
    private MyList<Phrase> topTenPhrases;


    public void ReadFile(String path, int numOfWordsInPhrase) {
        try {
            FileReader newFileReader = new FileReader(path);
            int i;
            char letter;
            String stopWords = "a|as|on|the|an|to|in|up|of|and|i|you|he|it|she|her|him|his|that|this|there|here|was|is|were|with|are|am|by|has|have|not|had|but" +
                    "|at|be|for|all|if|me|do";
            StringBuilder currentWord = new StringBuilder();

            wordMap = new HashMap<>();
            phraseMap = new HashMap<>();
            charMap = new HashMap<>();
            Deque<Word> phraseQueue = new ArrayDeque<>();
            topTenWords = new MyList<>();
            topTenPhrases = new MyList<>();
            wordsByCharNum = new HashMap<>();
            wordsByCharNumNoStopWords = new HashMap<>();

            while ((i = newFileReader.read()) != -1) {
                letter = Character.toLowerCase((char) i);
                if (letter == '\'')
                    continue;
                if (Character.isAlphabetic(letter)) {
                    currentWord.append(letter);
                    Integer charNum = charMap.get(letter);
                    charMap.put(letter, (charNum == null) ? 1 : charNum + 1);
                    totalLetterCount++;
                } else if (currentWord.length() > 0) {
                    String currentStrWorld = currentWord.toString();
                    Word word = wordMap.get(currentStrWorld);
                    if (word == null) {
                        wordMap.put(currentStrWorld, new Word(currentStrWorld));
                        word = wordMap.get(currentStrWorld);
                    }
                    word.incrementQuantity();
                    addWord(currentStrWorld, wordsByCharNum);
                    if (!currentStrWorld.matches(stopWords)) {
                        addWord(currentStrWorld, wordsByCharNumNoStopWords);
                        if (topTenWords.size() > 0)
                            topTenWordsCheck(word);
                        else
                            topTenWords.add(word);
                    }
                    phraseQueue.addLast(word);
                    if (phraseQueue.size() > numOfWordsInPhrase)
                        phraseQueue.removeFirst();
                    if (phraseQueue.size() == numOfWordsInPhrase) {
                        String currentPhrase = getStrPhrase(phraseQueue);
                        Phrase phrase = phraseMap.get(currentPhrase);
                        if (phrase == null) {
                            phraseMap.put(currentPhrase, new Phrase(phraseQueue));
                            phrase = phraseMap.get(currentPhrase);
                        }
                        phrase.incrementQuantity();
                        if (topTenPhrases.size() > 0)
                            topTenPhrasesCheck(phrase);
                        else
                            topTenPhrases.add(phrase);
                    }
                    currentWord = new StringBuilder();
                }
            }
            List<String> words = topTenWords.toStringList();
            List<String> phrases = topTenPhrases.toStringList();
            System.out.println("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<String> getTopTenWordsInString() {
        return topTenWords.toStringList();
    }

    public List<String> getTopTenPhrasesInString() {
        return topTenPhrases.toStringList();
    }

    public int getPhraseQuantity(String phrase) {
        return phraseMap.get(phrase).getQuantity();
    }

    public double getLetterFrequencyInPercent(char letter) {
        return totalLetterCount / (double) charMap.get(letter);
    }

    private void addWord(String word, Map<Integer, List<String>> hashMap) {
        List<String> listOfWordsByCharNum = hashMap.get(word.length());
        if (listOfWordsByCharNum == null) {
            List<String> newList = new ArrayList<>();
            newList.add(word);
            hashMap.put(word.length(), newList);
        } else {
            if (!listOfWordsByCharNum.contains(word)) {
                listOfWordsByCharNum.add(word);
            }
        }
    }

    private void topTenWordsCheck(Word currentWord) {
        if (!topTenWords.contains(currentWord)) {
            if (topTenWords.peekLast().getQuantity() < currentWord.getQuantity()) {
                int currentIndex = topTenWords.size() - 2;
                while (currentIndex >= 0 && topTenWords.get(currentIndex).getQuantity() > currentWord.getQuantity())
                    currentIndex--;
                if (currentIndex == -1)
                    topTenWords.shift(currentWord);
                else
                    topTenWords.putAfter(currentIndex, currentWord);
                if (topTenWords.size() > 10)
                    topTenWords.pop();
            }

        } else {
            int wordIndex = topTenWords.getIndex(currentWord);
            if (topTenWords.getIndex(currentWord) > 0 &&
                    topTenWords.get(wordIndex - 1).getQuantity() < currentWord.getQuantity()) {
                int currentIndex = wordIndex - 2;
                while (currentIndex >= 0 && topTenWords.get(currentIndex).getQuantity() > currentWord.getQuantity())
                    currentIndex--;
                topTenWords.remove(wordIndex);
                if (currentIndex == -1)
                    topTenWords.shift(currentWord);
                else {
                    topTenWords.putAfter(currentIndex, currentWord);
                }

            }
        }
    }

    private void topTenPhrasesCheck(Phrase currentPhrase) {
        if (!topTenPhrases.contains(currentPhrase)) {
            if (topTenPhrases.peekLast().getQuantity() < currentPhrase.getQuantity()) {
                int currentIndex = topTenWords.size() - 2;
                while (currentIndex >= 0 && topTenWords.get(currentIndex).getQuantity() > currentPhrase.getQuantity())
                    currentIndex--;
                if (currentIndex == -1)
                    topTenPhrases.shift(currentPhrase);
                else
                    topTenPhrases.putAfter(currentIndex, currentPhrase);
                if (topTenPhrases.size() > 10)
                    topTenPhrases.pop();
            }

        } else {
            int phrasesIndex = topTenPhrases.getIndex(currentPhrase);
            if (topTenPhrases.getIndex(currentPhrase) > 0 &&
                    topTenPhrases.get(phrasesIndex - 1).getQuantity() < currentPhrase.getQuantity()) {
                int currentIndex = phrasesIndex - 2;
                while (currentIndex >= 0 && topTenPhrases.get(currentIndex).getQuantity() > currentPhrase.getQuantity())
                    currentIndex--;
                topTenPhrases.remove(phrasesIndex);
                if (currentIndex == -1)
                    topTenPhrases.shift(currentPhrase);
                else {
                    topTenPhrases.putAfter(currentIndex, currentPhrase);
                }
            }
        }
    }

    private String getStrPhrase(Deque<Word> phraseQueue) {
        StringBuilder str = new StringBuilder();
        for (Word word : phraseQueue) {
            str.append(word.getWord()).append(" ");
        }
        return str.toString().trim();
    }

    public Map<String, Word> getWordMap() {
        return wordMap;
    }

    public Map<String, Phrase> getPhraseMap() {
        return phraseMap;
    }

    public Map<Character, Integer> getCharMap() {
        return charMap;
    }

    public Map<Integer, List<String>> getWordsByCharNum() {
        return wordsByCharNum;
    }

    public Map<Integer, List<String>> getWordsByCharNumNoStopWords() {
        return wordsByCharNumNoStopWords;
    }

    public long getTotalLetterCount() {
        return totalLetterCount;
    }
}
