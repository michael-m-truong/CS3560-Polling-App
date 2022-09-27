package cs3560_hw1;

import java.util.HashMap;
import java.util.List;

public class Student {
    
    private String unique_id = "";
    private static int number_of_students = 0;
    //private final int DIGITS = 5;
    private HashMap<Integer, Boolean> answer;
    private HashMap<Integer, Boolean> submittedAnswer;
    private Question question;

    public Student(Question question) {
        this.unique_id = String.valueOf(number_of_students);
        this.answer = new HashMap<>();
        this.question = question;
        number_of_students++;

        for (int i = 1; i <= question.getNumberOfChoices(); i++) {
            answer.put(i, false);
        }
    } 

    public String getID() {
        return unique_id;
    }

    public void setAnswer(List<Integer> newAnswer) {
        // if single choice, make sure length == 1
        answer.replaceAll((key, value) -> false);
        for (int i = 0; i < newAnswer.size(); i++) {
            answer.replace(newAnswer.get(i), true);
        }
    }

    public void submitAnswer() {
        submittedAnswer = answer;
    }

    public void changeQuestion(Question newQuestion) {
        question = newQuestion;
        answer.clear();
        for (int i = 1; i <= question.getNumberOfChoices(); i++) {
            answer.put(i, false);
        }
        
    }

    public HashMap<Integer, Boolean> getSubmittedAnswer() {
        return submittedAnswer;
    }

    public void clearAnswer() {
        answer.replaceAll((key, value) -> false);
        submittedAnswer.replaceAll((key, value) -> false);
    }
    
}
