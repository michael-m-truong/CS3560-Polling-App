package cs3560_hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MultipleChoiceQuestion implements Question{

    private String question;
    private HashMap<Integer, String> answerList;
    private HashMap<Integer, Boolean> answerKey;
    private List<String> incorrectChoices;
    private List<String> correctChoices;

    public MultipleChoiceQuestion(String question, List<String> correctAnswers, List<String> incorrectAnswers2) {
        this.question = question;
        this.correctChoices = correctAnswers;
        this.incorrectChoices = incorrectAnswers2;
        this.answerList = new HashMap<>();
        this.answerKey = new HashMap<>();
        this.randomizeChoiceOrder();
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
    public boolean checkAnswer(Answer questionNums) {
        List<Integer> answer = questionNums.getMultipleAnswer();
        for (int i = 0; i < correctChoices.size(); i++) {
            
        }
        return false;
    }

    @Override
    public String getQuestionType() {
        return "MultipleChoiceQuestion";
    }

    @Override
    public int getNumberOfChoices() {
        return incorrectChoices.size() + correctChoices.size();
    }

    private void randomizeChoiceOrder() {
        Random rand =  new Random();
        for (int i = 0; i < correctChoices.size(); i++) {
            int randCorrectNum = rand.nextInt(incorrectChoices.size() + correctChoices.size())+1;
            if (answerList.containsKey(randCorrectNum)) {
                for (int j = 0; j < incorrectChoices.size() + correctChoices.size(); j++) {
                    randCorrectNum++;
                    randCorrectNum = randCorrectNum % (incorrectChoices.size() + correctChoices.size()+1);
                    if (randCorrectNum == 0) randCorrectNum++;
                    if (!answerList.containsKey(randCorrectNum)) {
                        answerList.put(randCorrectNum, correctChoices.get(i));
                        answerKey.put(randCorrectNum, true);
                        break;
                    }
                } 
            }
            else {
                answerList.put(randCorrectNum, correctChoices.get(i));
                answerKey.put(randCorrectNum, true);
            }
        }

        Collections.shuffle(incorrectChoices);
        for (int i = 0; i < incorrectChoices.size(); i++) {
            if (answerList.containsKey(i+1)) {
                int randIncorrectNum = i+1;
                for (int j = 0; j < incorrectChoices.size() + correctChoices.size(); j++) {
                    randIncorrectNum++;
                    randIncorrectNum = randIncorrectNum % (incorrectChoices.size() + correctChoices.size()+1);
                    if (randIncorrectNum == 0) randIncorrectNum++;
                    if (!answerList.containsKey(randIncorrectNum)) {
                        answerList.put(randIncorrectNum, incorrectChoices.get(i));
                        answerKey.put(randIncorrectNum, false);
                        break;
                    }
                }
            } 
            else {
                answerList.put(i+1, incorrectChoices.get(i));
                answerKey.put(i+1, false);
            }
        }
    }


}
