package cs3560_hw1;

public class Student {
    
    private String unique_id = "";
    private static int number_of_students = 0;
    private final int DIGITS = 8;
    private Answer answer;
    private Answer submittedAnswer;

    public Student() {
        String num = String.valueOf(number_of_students);
        int  numberOfExtraZeros = DIGITS - num.length();
        String zeros = "";
        for (int i = 0; i < numberOfExtraZeros; i++) {
            zeros += "0";
        }
        this.unique_id = zeros + num;
        number_of_students++;
    } 

    public String getID() {
        return unique_id;
    }

    public void setAnswer(Answer newAnswer) {
        answer = newAnswer;
    }

    public void submitAnswer() {
        submittedAnswer = answer;
    }

    public Answer getSubmittedAnswer() {
        return submittedAnswer;
    }
    
    
    /*public void changeAnswer(Answer answer) {
        String questionType = question.getQuestionType();
        switch (questionType) {
            case "SingleChoiceQuestion":
                answer.setSingleAnswer(answer.getSingleAnswer());
                break;
            case "MultipleChoiceQuestion":
                answer.setMultipleAnswer(answer.getMultipleAnswer());
                break;
        }
    }*/

    /*public void newQuestion(Question newQ) {
        question = newQ;
    }*/



}
