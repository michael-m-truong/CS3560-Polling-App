package cs3560_hw1;

public class Student {
    
    private String unique_id = "";
    private static int number_of_students = 0;
    private final int DIGITS = 5;
    private Answer answer;
    private Answer submittedAnswer;

    public Student() {
        /*String num = String.valueOf(number_of_students);
        int  numberOfExtraZeros = DIGITS - num.length();
        String zeros = "";
        for (int i = 0; i < numberOfExtraZeros; i++) {
            zeros += "0";
        }
        this.unique_id = zeros + num; */    // probably unecessary
        this.unique_id = String.valueOf(number_of_students);
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
    
}
