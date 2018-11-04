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
                    //Добавление буквы к текущему слову
                    currentWord.append(letter);
                    //Добавление/обоновление буквы в хэш таблицы букв с увеличение количество повторений данной буквы
                    Integer charNum = charMap.get(letter);
                    charMap.put(letter, (charNum == null) ? 1 : charNum + 1);
                    totalLetterCount++;
                } else if (currentWord.length() > 0) {
                    String currentStrWorld = currentWord.toString();
                    //Добавление в хэш таблицу слов текущего слова, при его отсутствии
                    //(при добавлении слова в таблицу количество повторений равно 0)
                    Word word = wordMap.get(currentStrWorld);
                    if (word == null) {
                        wordMap.put(currentStrWorld, new Word(currentStrWorld));
                        word = wordMap.get(currentStrWorld);
                    }
                    //Увеличение количества повторений данного слова
                    word.incrementQuantity();
                    //Добавление текущего слова в хэш таблицу со словами, отсортированными по количеству букв
                    addWord(currentStrWorld, wordsByCharNum);
                    if (!currentStrWorld.matches(stopWords)) {
                        //Добавление текущего слова в хэш таблицу со словами, отсортированными по количеству букв, без стоп слов
                        addWord(currentStrWorld, wordsByCharNumNoStopWords);
                    }
                    //Добавление слова если топ пустой, а иначе сравнение его повторений с топом
                    if (topTenWords.size() > 0)
                        topTenWordsCheck(word);
                    else
                        topTenWords.add(word);
                    //Добавление текущего слова в фразу
                    phraseQueue.addLast(word);
                    //Если длинна фразы превышает заданную, то удаляем первое слово
                    if (phraseQueue.size() > numOfWordsInPhrase)
                        phraseQueue.removeFirst();
                    if (phraseQueue.size() == numOfWordsInPhrase) {
                        String currentPhrase = getStrPhrase(phraseQueue);
                        //Добавление в хэш таблицу фраз текущей фразы, при ее отсутствии
                        //(при добавлении фразы в таблицу количество повторений равно 0)
                        Phrase phrase = phraseMap.get(currentPhrase);
                        if (phrase == null) {
                            phraseMap.put(currentPhrase, new Phrase(phraseQueue));
                            phrase = phraseMap.get(currentPhrase);
                        }
                        //Увеличение количества повторений данной фразы
                        phrase.incrementQuantity();
                        //Добавление фразы если топ пустой, а иначе сравнение ее повторений с топом
                        if (topTenPhrases.size() > 0)
                            topTenPhrasesCheck(phrase);
                        else
                            topTenPhrases.add(phrase);
                    }
                    //Создание нового пустого слова
                    currentWord = new StringBuilder();
                }
            }
            //Для убоного дебага топов
            List<String> words = topTenWords.toStringList();
            List<String> phrases = topTenPhrases.toStringList();
            //Для breakpoint'а
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
        return phraseMap.get(phrase.toLowerCase().trim()).getQuantity();
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
        //Если в топе нет текущего слова
        if (!topTenWords.contains(currentWord)) {
            //Если количество повторений данного слова больше чем количество повторений последнего в топе слова
            if (topTenWords.peekLast().getQuantity() < currentWord.getQuantity()) {
                int currentIndex = topTenWords.size() - 2;
                //Ищем индекс слова в топе которое имеет больше повторений чем текущее
                while (currentIndex >= 0 && topTenWords.get(currentIndex).getQuantity() < currentWord.getQuantity())
                    currentIndex--;
                //Индекс -1 означает что данное слово имеет самое большое количество повторений
                if (currentIndex == -1)
                    //Ставим данное слово на первое место в топе
                    topTenWords.shift(currentWord);
                else
                    //Добавляем данное слова после найденного слова с большим количеством повторений в топе
                    topTenWords.putAfter(currentIndex, currentWord);
                if (topTenWords.size() > 10)
                    //Если топ превышает 10 слов, то удаляем последнее
                    topTenWords.pop();
            }
        } else {
            int wordIndex = topTenWords.getIndex(currentWord);
            //Если данное слово не является первым в топе и имеет количество повторений больше чем слово находящееся над ним в топе
            if (wordIndex > 0 &&
                    topTenWords.get(wordIndex - 1).getQuantity() < currentWord.getQuantity()) {
                int currentIndex = wordIndex - 2;
                //Ищем индекс слова в топе которое имеет больше повторений чем текущее
                while (currentIndex >= 0 && topTenWords.get(currentIndex).getQuantity() < currentWord.getQuantity())
                    currentIndex--;
                //Удаляем слово из топа
                topTenWords.remove(wordIndex);
                //Индекс -1 означает что данное слово имеет самое большое количество повторений
                if (currentIndex == -1)
                    //Ставим данное слово на первое место в топе
                    topTenWords.shift(currentWord);
                else {
                    //Добавляем данное слова после найденного слова с большим количеством повторений в топе
                    topTenWords.putAfter(currentIndex, currentWord);
                }

            }
        }
    }

    private void topTenPhrasesCheck(Phrase currentPhrase) {
        //Если в топе нет текущей фразы
        if (!topTenPhrases.contains(currentPhrase)) {
            //Если количество повторений данной фразы больше чем количество повторений последней фразы в топе
            if (topTenPhrases.peekLast().getQuantity() < currentPhrase.getQuantity()) {
                int currentIndex = topTenPhrases.size() - 2;
                //Ищем индекс фразы в топе которая имеет больше повторений чем текущая
                while (currentIndex >= 0 && topTenPhrases.get(currentIndex).getQuantity() < currentPhrase.getQuantity())
                    currentIndex--;
                //Индекс -1 означает что данная фраза имеет самое большое количество повторений
                if (currentIndex == -1)
                    //Ставим данную фразу на первое место в топе
                    topTenPhrases.shift(currentPhrase);
                else
                    //Добавляем данную фразу после найденной фразы с большим количеством повторений в топе
                    topTenPhrases.putAfter(currentIndex, currentPhrase);
                if (topTenPhrases.size() > 10)
                    //Если топ превышает 10 фраз, то удаляем последнюю
                    topTenPhrases.pop();
            }

        } else {
            int phraseIndex = topTenPhrases.getIndex(currentPhrase);
            //Если данная фраза не является первой в топе и имеет количество повторений больше чем фраза находящееся перед ней в топе
            if (phraseIndex > 0 &&
                    topTenPhrases.get(phraseIndex - 1).getQuantity() < currentPhrase.getQuantity()) {
                int currentIndex = phraseIndex - 2;
                //Ищем индекс фразы в топе которая имеет больше повторений чем текущая
                while (currentIndex >= 0 && topTenPhrases.get(currentIndex).getQuantity() < currentPhrase.getQuantity())
                    currentIndex--;
                topTenPhrases.remove(phraseIndex);
                //Индекс -1 означает что данная фраза имеет самое большое количество повторений
                if (currentIndex == -1)
                    //Ставим данную фразу на первое место в топе
                    topTenPhrases.shift(currentPhrase);
                else {
                    //Добавляем данную фразу после найденной фразы с большим количеством повторений в топе
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
