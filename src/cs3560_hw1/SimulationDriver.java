package cs3560_hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SimulationDriver {
    public static void main(String[] args) {

        Random rand = new Random();

        /* SINGLE CHOICE QUESTION */
        System.out.println("\n");
        String question = "What's my favorite coloree?";
        String correctAnswer = "Blue";
        List<String> incorrectAnswers = new ArrayList<>(Arrays.asList("Red", "Green", "Orange", "Yellow", "Purple"));
        
        Question TestQuestion = new SingleChoiceQuestion(question, correctAnswer, incorrectAnswers);
        // AAH I made a typo in my question! Lets fix it
        TestQuestion.modifyQuestion("What's my favorite color?");
        VotingService votingService = new VotingService(TestQuestion);
        for (int i = 0; i < 100; i++) {                   // add 100 students to voting service
            votingService.addStudent(new Student(TestQuestion));
        }
        for (int i = 0; i < 100; i++) {                 // 100 students vote (randomly generated vote)
            randomizeStudentVote_SingleChoice(rand, TestQuestion, votingService, i);
            if (rand.nextBoolean()) {
                randomizeStudentVote_SingleChoice(rand, TestQuestion, votingService, i);  // 50/50 change a student resubmits their choice
            }
            if (rand.nextBoolean()) {
                randomizeStudentVote_SingleChoice(rand, TestQuestion, votingService, i);
            }
        }
        votingService.stopVotingPoll();
        votingService.printStats();          //100 total votes, showing only most recent submission is counted
        System.out.println("\n");

        /* MULTIPLE CHOICE QUESTION */
        String question2 = "What NBA teams do I like to watch?";
        List<String> correctAnswers = new ArrayList<>(Arrays.asList("Lakers", "Mavericks"));
        List<String> incorrectAnswers2 = new ArrayList<>(Arrays.asList("Warriors"));
        
        /* EXAMPLE OF POLYMORPHISM; SingleChoiceQuestion and MultipleChoiceQuestion are both type Question */
        TestQuestion = new MultipleChoiceQuestion(question2, correctAnswers, incorrectAnswers2);
        votingService.changeQuestion(TestQuestion);
        for (int i = 0; i < 100; i++) {  // 100 students vote randomly; 
            randomizeStudentVote_MultipleChoice(rand, TestQuestion, votingService, i);
            if (rand.nextBoolean()) {
                randomizeStudentVote_MultipleChoice(rand, TestQuestion, votingService, i);  // 50/50 change a student resubmits their choice
            }
            if (rand.nextBoolean()) {
                randomizeStudentVote_MultipleChoice(rand, TestQuestion, votingService, i);  
            }
        }
        votingService.stopVotingPoll();
        votingService.printStats();
    }

    private static void randomizeStudentVote_SingleChoice(Random rand, Question TestQuestion, VotingService votingService, int index) {
        Student student = votingService.getStudent(index);
        student.setAnswer(Arrays.asList(rand.nextInt(TestQuestion.getNumberOfChoices())+1));
        student.submitAnswer();
    }

    private static void randomizeStudentVote_MultipleChoice(Random rand, Question TestQuestion, VotingService votingService, int index) {
        Student student = votingService.getStudent(index);   
        int numOfSelections = rand.nextInt(TestQuestion.getNumberOfChoices())+1;  // randomly generate number of choices a student chooses
        List<Integer> studentAnswers = new ArrayList<>();
        int maxChoices = TestQuestion.getNumberOfChoices();
        for (int j = 0; j < numOfSelections; j++) {        // randomly pick a choice 
            int randAnswer = rand.nextInt(maxChoices)+1;
            if (studentAnswers.contains(randAnswer)) {
                for (int k = 0; k < maxChoices; k++) {
                    randAnswer++;
                    randAnswer = randAnswer % (maxChoices+1);
                    if (randAnswer == 0) randAnswer++;
                    if (!studentAnswers.contains(randAnswer)) {
                        studentAnswers.add(randAnswer);
                        break;
                    }
                }
            }
            else {
                studentAnswers.add(randAnswer);
            }
        }
        student.setAnswer(studentAnswers);
        student.submitAnswer();
    } 
}
