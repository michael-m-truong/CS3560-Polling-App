package cs3560_hw1;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    
    private int singleAnswer;
    private List<Integer> multipleAnswer;

    public Answer(int singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    public Answer(List<Integer> multipleAnswer) {
        this.multipleAnswer = multipleAnswer;
    }

    public int getSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(int newAnswer) {
        singleAnswer = newAnswer;
    }

    public List<Integer> getMultipleAnswer() {
        return multipleAnswer;
    }

    public void setMultipleAnswer(ArrayList<Integer> newAnswer) {
        multipleAnswer = newAnswer;
    }

}

