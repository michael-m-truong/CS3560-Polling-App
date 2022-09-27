package cs3560_hw1;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public interface Question {

    String getQuestion();

    HashMap<Integer, String> getAnswerList();

    void modifyQuestion(String newQuestion);

    String getQuestionType();

    int getNumberOfChoices();

    boolean checkAnswer(Answer questionNum);  //different implementation to check answer based on mc or single, thus interface

}
