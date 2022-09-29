package cs3560_hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VotingService {
    
    private Question question;                 // example of composition
    private HashMap<Integer, Integer> stats;
    private List<String> correctStudents;
    private List<String> incorrectStudents;
    private List<Student> students;

    public VotingService(Question question) {
        this.question = question;
        this.students = new ArrayList<Student>();
        this.stats = new HashMap<>();
        this.correctStudents = new ArrayList<>();
        this.incorrectStudents = new ArrayList<>();

        for (int i = 1; i <= question.getNumberOfChoices(); i++) {  // initialize stats hashmap to tally student vote for each question
            stats.put(i, 0);
        }
    }
    
    public void stopVotingPoll() {
        System.out.println(question.getQuestionType() + ": " + question.getQuestion());
        System.out.println("Numbered choices: " + question.getAnswerList());
        this.answerCheck();   
    }

    private void answerCheck() {                      // check all student votes
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            HashMap<Integer, Boolean> studentAnswer = student.getSubmittedAnswer();
            boolean result = question.checkAnswer(studentAnswer);
            if (result) correctStudents.add(student.getID());
            else incorrectStudents.add(student.getID());
            for (int j = 1; j <= studentAnswer.size(); j++) {
                if (!studentAnswer.get(j)) continue;
                else stats.replace(j, stats.get(j)+1);
            }
        }
    }


    public void printStats() {
        System.out.println("\nAnswers per question: " + stats);
        System.out.println("Correct number choice was: " + question.getAnswer());
        System.out.println("\n" + correctStudents.size() + " students got it correct (List of student ID's): \n\n" + correctStudents);
        System.out.println("\n" + incorrectStudents.size() + " students got it incorrect (List of student ID's): \n\n" + incorrectStudents);
    }

    public void changeQuestion(Question newQuestion) {   
        this.question = newQuestion; 
        stats.clear();                 // clear because new question
        correctStudents.clear();
        incorrectStudents.clear();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            student.clearAnswer();
            student.changeQuestion(newQuestion);
        }
        for (int i = 1; i <= question.getNumberOfChoices(); i++) {  // initialize stats hashmap to tally student vote for each question
            stats.put(i, 0);
        }
    }

    public void addStudent(Student student) {   // add student to voting service
        students.add(student);   
    }

    public Student getStudent(int index) {   // used to randomly generate student vote
        return students.get(index);
    }
}
