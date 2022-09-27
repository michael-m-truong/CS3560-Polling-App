package cs3560_hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SimulationDriver {
    public static void main(String[] args) {

        Random rand = new Random();

        // TODO: 1) some students vote more than once 2) polymorphism test 
        // 3) abstract class Question 4) get correct answer 5) interface for student or something 6) comment code

        /* SINGLE CHOICE QUESTION */
        System.out.println("\n");
        String question = "What's my favorite color?";
        String correctAnswer = "Blue";
        
        List<String> incorrectAnswers = new ArrayList<>(Arrays.asList("Red", "Green", "Orange", "Yellow", "Purple"));
        Question singleAnswerQ = new SingleChoiceQuestion(question, correctAnswer, incorrectAnswers);
        System.out.println("Question: " + singleAnswerQ.getQuestion());
        System.out.println("Numbered choices: " + singleAnswerQ.getAnswerList());
        VotingService votingService = new VotingService(singleAnswerQ);
        for (int i = 0; i < 100; i++) {                   // add students to voting service
            votingService.addStudent(new Student(singleAnswerQ));
        }
        for (int i = 0; i < 100; i++) {                 // students vote (randomly generated vote)
            Student student = votingService.getStudent(i);
            student.setAnswer(Arrays.asList(rand.nextInt(singleAnswerQ.getNumberOfChoices())+1));
            student.submitAnswer();
        }
        votingService.stopVotingPoll();
        votingService.printStats();
        System.out.println("\n");

        //add students to voting service
        //students vote
        //voting service changes question
        
        //TODO: some students submit more than once; see how many students got Q right
        
        /* MULTIPLE CHOICE QUESTION */
        String question2 = "What NBA teams do I like to watch?";
        List<String> correctAnswers = new ArrayList<>(Arrays.asList("Lakers", "Mavericks"));
        List<String> incorrectAnswers2 = new ArrayList<>(Arrays.asList("Warriors"));
        
        Question multipleAnswerQ = new MultipleChoiceQuestion(question2, correctAnswers, incorrectAnswers2);
        votingService.changeQuestion(multipleAnswerQ);
        System.out.println("Multiple Choice Question:" + multipleAnswerQ.getQuestion());
        System.out.println("Numbered choices: " + multipleAnswerQ.getAnswerList());
        for (int i = 0; i < 100; i++) {  // students vote; 
            Student student = votingService.getStudent(i);   
            int numOfSelections = rand.nextInt(multipleAnswerQ.getNumberOfChoices())+1;  // randomly generate number of choices a student chooses
            List<Integer> studentAnswers = new ArrayList<>();
            int maxChoices = multipleAnswerQ.getNumberOfChoices();
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
        votingService.stopVotingPoll();
        votingService.printStats();

    }
}
