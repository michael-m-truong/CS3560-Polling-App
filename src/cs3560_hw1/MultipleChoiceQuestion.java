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
    private List<Integer> correctNumberChoices;

    public MultipleChoiceQuestion(String question, List<String> correctChoices, List<String> incorrectChoices) {
        this.question = question;
        this.correctChoices = correctChoices;
        this.incorrectChoices = incorrectChoices;
        this.answerList = new HashMap<>();
        this.answerKey = new HashMap<>();
        this.correctNumberChoices = new ArrayList<>();
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
    public boolean checkAnswer(HashMap<Integer, Boolean> studentAnswer) {
        return answerKey.equals(studentAnswer);
    }

    @Override
    public int getNumberOfChoices() {
        return incorrectChoices.size() + correctChoices.size();
    }

    @Override
    public List<Integer> getAnswer() {
        return correctNumberChoices; 
    }

    @Override
    public String getQuestionType() {
        return "Multiple Choice Question";
    }

    private void randomizeChoiceOrder() {    // example of abstraction; client should not have to worry about randomizing choice order
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
            correctNumberChoices.add(randCorrectNum);
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
