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
    }
    
    public void stopVotingPoll() {
        String questionType = question.getQuestionType();
        //System.out.println(questionType);
        switch (questionType) {
            case "SingleChoiceQuestion":
                this.singleAnswerCheck();
                break;
            case "MultipleChoiceQuestion":
                this.multipleAnswerCheck();
                break;
        }
        
    }

    private void singleAnswerCheck() {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            Answer studentAnswer = student.getSubmittedAnswer();
            boolean result = question.checkAnswer(studentAnswer);
            if (result) correctStudents.add(student.getID());
            else incorrectStudents.add(student.getID());
            String questionType = question.getQuestionType();  ///////////
            if (!stats.containsKey(studentAnswer.getSingleAnswer())) {
                stats.put(studentAnswer.getSingleAnswer(), 0);
            }
            else {
                stats.replace(studentAnswer.getSingleAnswer(), stats.get(studentAnswer.getSingleAnswer())+1);
            }
        }
    }

    private void multipleAnswerCheck() {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            Answer studentAnswer = student.getSubmittedAnswer();
            boolean result = question.checkAnswer(studentAnswer);
            if (result) correctStudents.add(student.getID());
            else incorrectStudents.add(student.getID());
            for (int j = 0; j < studentAnswer.getMultipleAnswer().size(); j++) {
                if (!stats.containsKey(studentAnswer.getMultipleAnswer().get(j))) {
                    stats.put(studentAnswer.getMultipleAnswer().get(j), 0);
                }
                else {
                    stats.replace(studentAnswer.getMultipleAnswer().get(j), stats.get(studentAnswer.getMultipleAnswer().get(j))+1);
                }
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
        for (int i = 0; i < students.size(); i++) {
            students.get(i).setAnswer(null);
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
