package cs3560_hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SingleChoiceQuestion implements Question {

    private String question;
    private HashMap<Integer, String> answerList;
    private HashMap<Integer, Boolean> answerKey;
    private List<String> incorrectChoices;  //change to answer
    private String correctChoice; //change to answer
    private List<Integer> correctNumberChoice;

    public SingleChoiceQuestion(String question, String correctChoice, List<String> incorrectChoices) {
        this.question = question;
        this.correctChoice = correctChoice;
        this.incorrectChoices = incorrectChoices;
        this.answerList = new HashMap<>();
        this.answerKey = new HashMap<>();
        this.correctNumberChoice = new ArrayList<>();
        this.randomizeChoiceOrder();
    }

    @Override
    public boolean checkAnswer(HashMap<Integer, Boolean> studentAnswer) {
        return answerKey.equals(studentAnswer);
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public HashMap<Integer, String> getAnswerList() {
        return answerList;
    }

    @Override
    public void modifyQuestion(String newQuestion) {
        question = newQuestion;    
    }

    @Override
    public int getNumberOfChoices() {
        return incorrectChoices.size() + 1;
    }

    @Override
    public List<Integer> getAnswer() {
        return correctNumberChoice;
    }

    @Override
    public String getQuestionType() {
        return "Single Choice Question";
    }

    private void randomizeChoiceOrder() {     // example of abstraction; client should not have to worry about randomizing choice order
        Random rand =  new Random(); 
        int randCorrectNum = rand.nextInt(1+incorrectChoices.size())+1;
        correctNumberChoice.add(randCorrectNum);
        answerList.put(randCorrectNum, correctChoice);
        answerKey.put(randCorrectNum, true);
        Collections.shuffle(incorrectChoices);
        for (int i = 0; i < incorrectChoices.size(); i++) {
            if (i+1 == randCorrectNum) {
                answerList.put(incorrectChoices.size()+1, incorrectChoices.get(incorrectChoices.size()-1));
                answerKey.put(incorrectChoices.size()+1, false);
            } 
            else {
                answerList.put(i+1, incorrectChoices.get(i));
                answerKey.put(i+1, false);
            }
        }
    }

    
}
