package cs3560_hw1;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SingleChoiceQuestion implements Question {

    private String question;
    private HashMap<Integer, String> answerList;
    private HashMap<Integer, Boolean> answerKey;
    private List<String> incorrectChoices;
    private String correctChoice;

    public SingleChoiceQuestion(String question, String correctChoice, List<String> incorrectAnswers) {
        this.question = question;
        this.correctChoice = correctChoice;
        this.incorrectChoices = incorrectAnswers;
        this.answerList = new HashMap<>();
        this.answerKey = new HashMap<>();
        this.randomizeChoiceOrder();
    }

    @Override
    public boolean checkAnswer(Answer questionNum) {
        int answer = questionNum.getSingleAnswer();
        return answerKey.get(answer);
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
    public String getQuestionType() {
        return "SingleChoiceQuestion";
    }

    @Override
    public int getNumberOfChoices() {
        return incorrectChoices.size() + 1;
    }


    private void randomizeChoiceOrder() {
        Random rand =  new Random();
        int randCorrectNum = rand.nextInt(1+incorrectChoices.size())+1;
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
