import java.util.*;

//VOTING SERVICE CLASS
public class VotingService 
{
    //IMPORTANT VARIABLES
    private Question question;
    private Map<String, List<String>> submissions;

    //VOTING SERVICE HASHMAP
    protected VotingService()
    {
        this.submissions = new HashMap<>();
    }

    //QUESTION CONFIGURE METHOD TO GIVE QUESTION FOR THE VOTING SYSTEM
    protected void questionConfigure(Question q)
    {
        this.question = q;

        //CLEAR IS TO MAKE SURE WE ALWAY ARE WORKING WITH NEW SUBMISSIONS OF STUDENT ANSWERS EVERY TIME
        this.submissions.clear();
    }

    //RETURNS QUESTION TEXT
    protected Question getQuestion()
    {
        return question;
    }

    //SUBMIT ANSWERS FOR EACH INDIVIDUAL STUDENT
    //USED IN FOR LOOP IN SIMULATION DRIVER METHOD
    protected void submitAnswer(String studentID, List<String> answers)
    {
        if (question.answerCORRECTNESS(answers))
        {
            submissions.put(studentID, answers);
        }
        else
        {
            System.out.println("Invalid answer by student #" + studentID);
        }
    }

    //DISPLAY THE RESULTS OF THE VOTING SYSTEM
    protected void displayResults()
    {
        Map<String, Integer> results = new HashMap<>();

        //FOR LOOP, WILL ACCOUNT FOR BOTH SINGLE AND MULTIPLE CHOICE QUESTIONS
        for (List<String> answers : submissions.values()) 
        {
            for (String answer : answers) 
            {
                results.put(answer, results.getOrDefault(answer, 0) + 1);
            }
        }

        //WILL PRINT OUT RESULTS
        for (Map.Entry<String, Integer> entry : results.entrySet()) 
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
