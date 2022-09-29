package cs3560_hw1;

import java.util.HashMap;
import java.util.List;

public interface Question {  // used an interface because implementation easier with it

    String getQuestion();  // used to display question in console

    HashMap<Integer, String> getAnswerList();  // used to display answer list in console

    void modifyQuestion(String newQuestion);  // used to display answer list in console

    int getNumberOfChoices();  // used to randomize student votes
     
    List<Integer> getAnswer();  // used to collect voting statistics for question

    String getQuestionType ();  // used to display question type in console

    boolean checkAnswer(HashMap<Integer, Boolean> questionNum);  // used to check student vote

}
