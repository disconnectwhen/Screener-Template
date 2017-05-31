package deecee.screener_template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InfoTwoActivity extends AppCompatActivity implements View.OnClickListener{

    private String age;
    private String race;
    private String state;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info_two);

        Button maleButton   = (Button) findViewById(R.id.infoTwo_maleButton);
        Button femaleButton = (Button) findViewById(R.id.infoTwo_femaleButton);

        maleButton.setOnClickListener(this);
        femaleButton.setOnClickListener(this);

        Intent prev = getIntent();

        age   = prev.getStringExtra("age");
        race  = prev.getStringExtra("race");
        state = prev.getStringExtra("state");
        city  = prev.getStringExtra("city");
    }

    @Override
    public void onBackPressed() {
        // Purposefully empty to disable back button.
    }

    public void onClick(View view){
        Spinner  juvenile_spinner = (Spinner)  findViewById(R.id.infoTwo_juvenileSpinner);
        Spinner  social_spinner   = (Spinner)  findViewById(R.id.infoTwo_socialSpinner);
        Spinner  assault_spinner  = (Spinner)  findViewById(R.id.infoTwo_assaultSpinner);
        EditText agency_edittext  = (EditText) findViewById(R.id.infoTwo_agencyEditText);

        String juvenile_text = juvenile_spinner.getSelectedItem().toString();
        String social_text   = social_spinner.getSelectedItem().toString();
        String assault_text  = assault_spinner.getSelectedItem().toString();
        String agency_text   = agency_edittext.getText().toString();

        Intent next = new Intent(this, QuizActivity.class);
        next.putExtra("date", new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(new Date()));
        next.putExtra("age", age);
        next.putExtra("race", race);
        next.putExtra("state", state);
        next.putExtra("city", city);
        next.putExtra("juvenile", juvenile_text);
        next.putExtra("social", social_text);
        next.putExtra("assault", assault_text);
        next.putExtra("agency", agency_text);

        switch(view.getId()){
            case(R.id.infoTwo_maleButton):
                next.putExtra("gender", "male");
                break;
            case(R.id.infoTwo_femaleButton):
                next.putExtra("gender", "female");
                break;
            default:
                break;
        }

        startActivity(next);
    }
}