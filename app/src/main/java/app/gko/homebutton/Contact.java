package app.gko.homebutton;

/**
 * Created by Administrator on 2/15/2015.
 */
public class Contact {

    //private variables
    int _id;
    String _question;
    String _answer1;
    String _answer2;
    String _answer3;
    String _answer4;
    String _correct_answer;

    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String question, String _answer1,String _answer2,String _answer3,String _answer4,String _correct_answer){
        this._id = id;
        this._question = question;
        this._answer1 = _answer1;
        this._answer2 = _answer2;
        this._answer3 = _answer3;
        this._answer4 = _answer4;
        this._correct_answer = _correct_answer;
    }

    // constructor
    public Contact(String question, String _answer1,String _answer2,String _answer3,String _answer4, String _correct_answer){
        this._question = question;
        this._answer1 = _answer1;
        this._answer2 = _answer2;
        this._answer3 = _answer3;
        this._answer4 = _answer4;
        this._correct_answer = _correct_answer;

    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getQuestion(){
        return this._question;
    }

    // setting name
    public void setQuestion(String question){
        this._question = question;
    }

    // getting phone number
    public String getAnswer1(){
        return this._answer1;
    }

    // setting phone number
    public void setAnswer1(String answer1){
        this._answer1 = answer1;


    } public String getAnswer2(){
        return this._answer2;
    }

    // setting phone number
    public void setAnswer2(String answer2){
        this._answer2 = answer2;


    } public String getAnswer3(){
        return this._answer3;
    }

    // setting phone number
    public void setAnswer3(String answer3){
        this._answer3 = answer3;
    }

    public String getAnswer4() {
        return this._answer4;
    }

    // setting phone number
    public void setAnswer4(String answer4) {
        this._answer4 = answer4;
    }

    public String getCorrectAnswer() {
        return this._correct_answer;
    }

    // setting phone number
    public void setCorrectAnswer(String correctAnswer) {
        this._correct_answer = correctAnswer;
    }

}
