package cs3560_hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VotingService {
    
    private Question question;
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

        for (int i = 1; i <= question.getNumberOfChoices(); i++) {
            stats.put(i, 0);
        }
    }
    
    public void stopVotingPoll() {
        this.answerCheck();   
    }

    private void answerCheck() {
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
        System.out.println("Correct answer was: ");
        System.out.println("\n" + correctStudents.size() + " students got it correct (List of student ID's): \n\n" + correctStudents);
        System.out.println("\n" + incorrectStudents.size() + " students got it incorrect (List of student ID's): \n\n" + incorrectStudents);
    }

    public void changeQuestion(Question newQuestion) {
        this.question = newQuestion;
        stats.clear();
        correctStudents.clear();
        incorrectStudents.clear();
        //System.out.println(students.size());
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            student.clearAnswer();
            student.changeQuestion(newQuestion);
        }
        for (int i = 1; i <= question.getNumberOfChoices(); i++) {
            stats.put(i, 0);
        }
    }

    /*public void addStudentVote(int studentIndex) {
        // make post request to API if web app 
    } */

    public void addStudent(Student student) {
        students.add(student);   
    }

    public Student getStudent(int index) {
        return students.get(index);
    }
}
