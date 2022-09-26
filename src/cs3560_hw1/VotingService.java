package cs3560_hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VotingService {
    
    private Question question;
    private HashMap<Integer, Integer> stats;
    private HashMap<String, Boolean> correctStudents;
    private HashMap<String, Boolean> incorrectStudents;
    private List<Student> students;

    public VotingService(Question question) {
        this.question = question;
        this.students = new ArrayList<Student>();
        this.stats = new HashMap<>();
        this.correctStudents = new HashMap<>();
        this.incorrectStudents = new HashMap<>();
    }
    
    public HashMap<Integer, Integer> stopVotingPoll() {
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
        for (int i = 0; i < stats.size(); i++) {
            //do something with stats here maybe for fun
        }

        return null;
        
    }

    private void singleAnswerCheck() {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            Answer studentAnswer = student.getSubmittedAnswer();
            boolean result = question.checkAnswer(studentAnswer);
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
            //System.out.println(studentAnswer.getMultipleAnswer());
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
        System.out.println("Answers per question: " + stats);
    }

    public void changeQuestion(Question newQuestion) {
        //students.clear();
        this.question = newQuestion;
        stats.clear();
        for (int i = 0; i < students.size(); i++) {
            students.get(i).setAnswer(null);
        }
    }

    public void addStudentVote(int studentIndex) {
        //students.set(studentIndex, students.get(studentIndex));
    }

    public void addStudent(Student student) {
        students.add(student);
        //student.
    }

    public Student getStudent(int index) {
        return students.get(index);
    }
}
