import java.util.*;

//QUESTION INTERFACE
public interface Question {

    //IMPORTANT METHODS
    String questionTEXT();
    List<String>studentANSWERS();
    boolean answerCORRECTNESS(List<String> answers);
}

//CLASS FOR SINGLE CHOICE QUESTIONS
class singleChoiceQuestion implements Question{

    //IMPORTANT VARIABLES
    private String question;
    private List<String> studentAnswerchoices;

    //CONSTRUCTOR
    protected singleChoiceQuestion (String q, List<String> a)
    {
        this.question = q;
        this.studentAnswerchoices = a;
    }

    //RETURN QUESTION TEXT
    public String questionTEXT() {
        return question;
    }

    //STUDENT ANSWER CHOICES
    public List<String> studentANSWERS() {
        return studentAnswerchoices;
    }

    //WILL CHECK TO SEE IF THE ANSWER MADE WAS VALID
    public boolean answerCORRECTNESS(List<String> answers) {
        return answers.size() == 1 && studentAnswerchoices.contains(answers.get(0));
    }

}


//CLASS FOR MULTIPLE CHOICE QUESTIONS
class multipleChoiceQuestion implements Question{

    private String question;
    private List<String> studentAnswerchoices;

    protected multipleChoiceQuestion (String q, List<String> a)
    {
        this.question = q;
        this.studentAnswerchoices = a;
    }

    @Override
    public String questionTEXT() {
        return question;
    }

    @Override
    public List<String> studentANSWERS() {
        return studentAnswerchoices;
    }

    @Override
    public boolean answerCORRECTNESS(List<String> answers) {
        return studentAnswerchoices.containsAll(answers);
    }

}