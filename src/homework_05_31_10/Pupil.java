package homework_05_31_10;

import java.util.ArrayList;
import java.util.List;

public class Pupil {
    private int id;
    private List<ExamResult> examResults = new ArrayList<>();

    public Pupil(int id){
        this.id = id;
    }

    public void setExams (String[] subjectNames,int[] marks){
        if(subjectNames.length!=marks.length)
            throw new IndexOutOfBoundsException();
        for(int i=0;i<subjectNames.length;i++){
            this.examResults.add(new ExamResult(subjectNames[i],marks[i]));
        }
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Id студента: ").append(id).append('\n');
        for (ExamResult examResult : examResults){
            str.append("Предмет: ").append(examResult.getSubjectName()).append('\n');
            str.append("Оценка: ").append(examResult.getMark()).append('\n');
            str.append("Результат: ").append(examResult.isPassed()?"Прошел":"Не прошел").append("\n\n");
        }
        return str.toString();
    }
    class ExamResult{
        private String subjectName;
        private int mark;
        private boolean passed;

        String getSubjectName() {
            return subjectName;
        }

        int getMark() {
            return mark;
        }

        boolean isPassed() {
            return passed;
        }

        ExamResult(String subjectName, int examMark){
            this.subjectName = subjectName;
            this.mark = examMark;
            this.passed = examMark>2;
        }
    }
}
