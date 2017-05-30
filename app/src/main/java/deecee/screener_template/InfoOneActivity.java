package deecee.screener_template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InfoOneActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info_one);

        Button continueButton = (Button) findViewById(R.id.infoOne_continueButton);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        // Purposefully empty to disable back button.
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case(R.id.infoOne_continueButton):
                Spinner  age_spinner   = (Spinner)  findViewById(R.id.infoOne_ageSpinner);
                Spinner  race_spinner  = (Spinner)  findViewById(R.id.infoOne_raceSpinner);
                Spinner  state_spinner = (Spinner)  findViewById(R.id.infoOne_stateSpinner);
                EditText city_edittext = (EditText) findViewById(R.id.infoOne_cityEditText);

                String age   = age_spinner.getSelectedItem().toString();
                String race  = race_spinner.getSelectedItem().toString();
                String state = state_spinner.getSelectedItem().toString();
                String city  = city_edittext.getText().toString();

                Intent intent = new Intent(this, InfoTwoActivity.class);
                intent.putExtra("age", age);
                intent.putExtra("race", race);
                intent.putExtra("state", state);
                intent.putExtra("city", city);
                startActivity(intent);
                break;
            default:
        }
    }
}