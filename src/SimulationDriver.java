import java.util.*;

//SIMULATION DRIVER
public class SimulationDriver {

    //CHANGE HERE FOR THE AMOUNT OF STUDENTS THERE ARE TO SUBMIT
    private static int studentNumber = 100;

    //MAIN METHOD
    public static void main(String[] args) {

        System.out.println("VOTING SIMULATOR WITH 100 STUDENTS...");

        //EDIT HERE THE QUESTIONS AND THE LIST OF THE ANSWERS OF THE QUESTION
        String questionString = "[Question is here]";
        List<String> listOfQuestions = Arrays.asList("A. ", "B. ", "C. ", "D. ");

        //NEW SINGLE AND MULTIPLE QUESTIONS
        Question singleQuestion = new singleChoiceQuestion(questionString, listOfQuestions);
        Question multipleQuestion = new multipleChoiceQuestion(questionString, listOfQuestions);

        //ENABLE VOTING SERVICE
        VotingService vote = new VotingService();

        //VOTING FOR SINGLE CHOICE QUESTIONS
        System.out.print("Single Choice Question Voting: ");
        //PRINT WHAT THE QUESTION IS '[Question is here]'
        System.out.println(singleQuestion.questionTEXT());
        //GIVES VOTING SYSTEM THE QUESTION STRING AND LIST OF ANSWERS TO VOTE
        vote.questionConfigure(singleQuestion);
        //VOTE
        simulateStudentSubmissions(vote,listOfQuestions.toArray(new String[0]));
        vote.displayResults();

        //VOTING FOR MULTIPLE CHOICE QUESITONS
        System.out.print("\nMultiple Choice Question Voting: ");
        //PRINT WHAT THE QUESTION IS '[Question is here]'
        System.out.println(multipleQuestion.questionTEXT());
        //GIVES VOTING SYSTEM THE QUESTION STRING AND LIST OF ANSWERS TO VOTE
        vote.questionConfigure(multipleQuestion);
        //VOTE
        simulateStudentSubmissions(vote,listOfQuestions.toArray(new String[0]));
        vote.displayResults();
    }

    //IMPORTANT TO ALLOW SUBMISSION OF ANSWERS PER STUDENT
    private static void simulateStudentSubmissions(VotingService votingService, String[] possibleAnswers) {

        //RANDOM VARIABLE
        Random random = new Random();

        //LIST FOR ALL STUDENTS
        List<Student> students = new ArrayList<>();

        //FOR LOOP FOR EACH STUDENT
        for (int i = 1; i <= studentNumber; i++) {

            //STUDENT NUMBER
            Student student = new Student("Student" + i);
            students.add(student);

            //STUDENT ANSWERS FOR EITHER SINGLE OR MULTIPLE QUESTIONS
            List<String> studentAnswers = new ArrayList<>();
            // FOR SINGLE CHOICE QUESTIONS
            if (votingService.getQuestion() instanceof singleChoiceQuestion)
            {
                //RANDOMLY PICK ANSWER
                studentAnswers.add(possibleAnswers[random.nextInt(possibleAnswers.length)]);
            }
            // FOR MULTIPLE CHOICE QUESTIONS
            else if (votingService.getQuestion() instanceof multipleChoiceQuestion) {
                // RANDOMLY PICK ANSWERS
                // RANDOM NUMBER BETWEEN 1 AND MAX POSSIBLE ANSWERS
                int numAnswers = random.nextInt(possibleAnswers.length) + 1;
                //WHILE LOOP FOR THE NUMBER OF ANSWERS
                while (studentAnswers.size() < numAnswers) 
                {
                    //ENTERS ANSWER
                    String answer = possibleAnswers[random.nextInt(possibleAnswers.length)];
                    if (!studentAnswers.contains(answer)) {
                        studentAnswers.add(answer);
                    }
                }
            }

            //SUBMIT ANSWERS TO THE VOTING SERVICE FOR THE INDIVIDUAL STUDENT
            votingService.submitAnswer(student.getId(), studentAnswers);
        }
    }

    
}