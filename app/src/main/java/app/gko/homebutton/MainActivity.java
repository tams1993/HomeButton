package app.gko.homebutton;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button button, btnBack, btnNext;
    private TextView textView, textView2;
    private String jsonString ="{\"question_all\":{\"question\":[{\"q\":\"question1\",\"a1\":\"answer1\",\"a2\":\"answer2\",\"a3\":\"answer3\",\"a4\":\"answer4\"},{\"q\":\"question2\",\"a1\":\"answer1\",\"a2\":\"answer2\",\"a3\":\"answer3\",\"a4\":\"answer4\"},{\"q\":\"question3\",\"a1\":\"answer1\",\"a2\":\"answer2\",\"a3\":\"answer3\",\"a4\":\"answer4\"},{\"q\":\"question4\",\"a1\":\"answer1\",\"a2\":\"answer2\",\"a3\":\"answer3\",\"a4\":\"answer4\"}]}}";

    private String[] quetion;

    private static final String TAG = "json";


    private int Counter = 0;


//    @Override
//    protected void onPause() {
//
//
//        super.onPause();
//
//        Log.d("test", "OnPause" + String.valueOf(Counter));
//
//
//
//    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        View decorView = getWindow().getDecorView();

        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}


    }

    @Override
    protected void onStop() {
        super.onStop();


        Counter++;

        Log.d("test", "Home has been press");



        Log.d("test", String.valueOf(Counter));


    }


    @Override
    protected void onResume() {
        super.onResume();

        if (Counter == 5) {

            TextView textView = (TextView) findViewById(R.id.textview);
                     textView.setText("Home button has been press " + Counter + " times" );

        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        textView2 = (TextView) findViewById(R.id.textView2);


        btnBack = (Button) findViewById(R.id.backButton);
        btnNext = (Button) findViewById(R.id.nextButton);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView2);

        final SharedPreferences sp = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        textView2.setText(String.valueOf(Counter));






        ((RadioButton)radioGroup.getChildAt(3)).setChecked(true);

       final int selectID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(selectID);
       final int idx = radioGroup.indexOfChild(radioButton);

        editor.putString("answer_" + Counter, String.valueOf(idx));
        editor.commit();


        for (Counter = 0; Counter <= 20; Counter++) {

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    try {







                        editor.putString("answer_" + Counter, String.valueOf(Counter));
                        editor.commit();




//                    if (selectID == 2) {
//
//                        Counter++;
//
//
//
//                    }
                        Toast.makeText(getApplicationContext(), sp.getString("answer_" + Counter, String.valueOf(-1)), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), String.valueOf(Counter), Toast.LENGTH_SHORT).show();


//                    textView.setText(String.valueOf(Counter));

//                    textView.setText(radioButton.getText());
//                    Toast.makeText(MainActivity.this, String.valueOf(Counter), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {


                    }



                }
            });


        }





        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Counter == 19) {

                    Counter = 0;
                    textView2.setText(String.valueOf(Counter));
                    quetion[Counter] = String.valueOf(idx);

                    editor.putString("answer " + Counter, String.valueOf(idx));
                    editor.commit();

                } else {
                    Counter++;
                    textView2.setText(String.valueOf(Counter));
                    quetion[Counter] = String.valueOf(idx);
                    editor.putString("answer " + Counter, String.valueOf(idx));
                    editor.commit();

                }



            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Counter <= 0) {

                    Counter = 19;
                    textView2.setText(String.valueOf(Counter));
                    quetion[Counter] = String.valueOf(idx);
                    editor.putString("answer " + Counter, String.valueOf(idx));
                    editor.commit();


                } else {
                    Counter--;
                    textView2.setText(String.valueOf(Counter));
                    quetion[Counter] = String.valueOf(idx);
                    editor.putString("answer " + Counter, String.valueOf(idx));
                    editor.commit();

                }

            }
        });





        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");

//        db.CheckIsDataAlreadyInDBorNot();


        db.addContact(new Contact("question1", "Answer1","Answer2","Answer3","Answer4","1"));
        db.addContact(new Contact("question2", "Answer1","Answer2","Answer3","Answer4","2"));
        db.addContact(new Contact("question3", "Answer1","Answer2","Answer3","Answer4","3"));
        db.addContact(new Contact("question4", "Answer1","Answer2","Answer3","Answer4","4"));


        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Question: " + cn.getQuestion() + " ,Answer1: " + cn.getAnswer1()+ " ,Answer2: " + cn.getAnswer2()+ " ,Answer3: " + cn.getAnswer3()+ " ,Answer4: " + cn.getAnswer4()+ " ,CorrectAnswer: " + cn.getCorrectAnswer() ;
            // Writing Contacts to log
            Log.d("Name: ", log);
        }




        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);



        final String[] radioText = getResources().getStringArray(R.array.question);







//        JSONObject jsonObject;
//        try {
//
//            ArrayList<String> temp = new ArrayList<>();
//            jsonObject = new JSONObject(jsonString);
//            JSONArray question_array = jsonObject.getJSONArray("question_all");
//            for (int i = 0; i < question_array.length(); i++) {
//
//                JSONObject question = question_array.getJSONObject(i);
//                JSONArray q = question.getJSONArray("question");
//                for (int j = 0; i < question.length(); i++) {
//
//                    temp.add(q.getString(j));
//
//                }
//
//            }
//
//            Toast.makeText(this, "JSON: " + temp, Toast.LENGTH_SHORT).show();
//
//            Log.d("json", "JSON: " + temp);
//
//        } catch (Exception e) {
//
//
//            e.printStackTrace();
//
//        }

        try {
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONObject questionAll = jsonResponse.getJSONObject("question_all");
            JSONArray questionArray = questionAll.getJSONArray("question");
            List<String[]> allNames = new ArrayList<>();
//            JSONObject q = questionArray.getJSONObject(0);


//            String[] question = {q.getString("q"),q.getString("a1"),q.getString("a2"),q.getString("a3"),q.getString("a4")};

//            textView.setText(question[0]);
//            for(int i = 1; i <= 4; i++) {
//                radioButton = new RadioButton(this);
//                radioButton.setText(question[i]);
//                radioGroup.addView(radioButton);
//                radioGroup.getChildAt(i);
//            }


//            String[] arr = new String[questionArray.length()];
//            for (int i = 0; i < questionArray.length(); i++) {
//
//                arr[i] = questionArray.getString(i);
//                Log.d(TAG, "arr[ "+i+"]= " + arr[i]);
//            }
//
//            allNames.add(arr);


            for (int i = 0; i < questionArray.length(); i++) {


                JSONObject q = questionArray.getJSONObject(i);

                String[] question = {q.getString("q"),q.getString("a1"),q.getString("a2"),q.getString("a3"),q.getString("a4")};
                String answer = q.getString("q");  //  specific item
                allNames.add(question);



            }

//            Log.d(TAG, "question : " + allNames[0]);


        } catch (JSONException e) {
            e.printStackTrace();
        }


//        try {
//
//            JSONObject jsonResponse = new JSONObject(jsonString);
//
//            List<String> allNames = new ArrayList<String>();
//
//            JSONArray question_all = jsonResponse.getJSONArray("question_all");
//            for (int i=0; i<question_all.length(); i++) {
//                JSONObject actor = question_all.getJSONObject(i);
//                String question = actor.getString("question");
//
//
//                allNames.add(question);
//
//                for (int j = 0; j < actor.length(); j++) {
//                    JSONArray eachquestion = actor.getJSONArray("q");
//                    Log.d(TAG, "question: " + eachquestion.getJSONObject(j).getString("q"));
//
//                }
//            }
//
//
//            Log.d(TAG, "JSON: " + allNames);
//
//
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }










    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
