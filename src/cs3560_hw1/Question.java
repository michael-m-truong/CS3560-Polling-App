package cs3560_hw1;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public interface Question {

    /*private String question;
    protected HashMap<Integer, String> answerList;
    protected HashMap<Integer, Boolean> answerKey;
    protected ArrayList<String> incorrectChoices;

    public Question(String question, ArrayList<String> incorrectChoices) {
        this.question = question;
        this.incorrectChoices = incorrectChoices;
        this.answerList = new HashMap<>();
        this.answerKey = new HashMap<>();
    }*/

    /*protected Question(HashMap<Integer, String> answerList, HashMap<Integer, Boolean> answerKey) {
        this.answerList = answerList;
        this.answerKey = answerKey;
    } */


    String getQuestion();

    HashMap<Integer, String> getAnswerList();

    void modifyQuestion(String newQuestion);

    String getQuestionType();

    int getNumberOfChoices();

    boolean checkAnswer(Answer questionNum);  //different implementation to check answer based on mc or single, thus interface

    //public void changeAnswer(HashMap<Character, Boolean> newAnswerList) {this.answerList = newAnswerList; };


}
