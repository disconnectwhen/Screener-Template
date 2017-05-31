package deecee.screener_template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    private int count = 0;
    private int score = 0;
    private String answers[];
    private String questions[];
    private String SERVER_URL;
    private TextView questionTextView;

    private final String maleQuestions[]   = {"Sample Question #1", "Sample Question #2"};
    private final String femaleQuestions[] = {"Sample Question #1"};
    private final String extraQuestions[]  = {"date", "age", "race", "state", "city", "agency", "social", "juvenile", "assaults"};
    private       String extraAnswers[]    = new String[extraQuestions.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();

        switch(intent.getStringExtra("gender")){
            case("male"):
                answers = new String[maleQuestions.length];
                questions = maleQuestions;
                SERVER_URL = "MALE-PHP-URL";
                break;
            case("female"):
                answers = new String[femaleQuestions.length];
                questions = femaleQuestions;
                SERVER_URL = "FEMALE-PHP-URL";
                break;
            default:
                break;
        }

        questionTextView = (TextView) findViewById(R.id.quiz_questionTextView);
        questionTextView.setText(questions[0]);

        Button yesButton     = (Button) findViewById(R.id.quiz_yesButton);
        Button noButton      = (Button) findViewById(R.id.quiz_noButton);
        Button unknownButton = (Button) findViewById(R.id.quiz_unknownButton);

        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
        unknownButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        // purposely empty to disable back button
    }

    public void onClick(View view){
        switch(view.getId()){
            case(R.id.quiz_yesButton):
                answers[count] = "Y";
                score += 1;
                break;
            case(R.id.quiz_noButton):
                answers[count] = "N";
                break;
            case(R.id.quiz_unknownButton):
                answers[count] = "U";
                break;
        }
        count += 1;

        if(count < questions.length)
            questionTextView.setText(questions[count]);
        else
            sendResults();
    }

    public void sendResults(){

        Intent prev = getIntent();
        for(int i = 0; i < extraQuestions.length; ++i) {
            extraAnswers[i] = prev.getStringExtra(extraQuestions[i]);
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                for(int i = 0; i < extraQuestions.length; ++i){
                    params.put(extraQuestions[i], extraAnswers[i]);
                }
                for(int i = 0; i < questions.length; ++i){
                    params.put("q" + String.valueOf(i + 1), answers[i]);
                }
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.start();
        requestQueue.add(stringRequest);

        Intent next = new Intent(this, ResultsActivity.class);
        next.putExtra("score", Integer.toString(score));
        startActivity(next);
    }
}
