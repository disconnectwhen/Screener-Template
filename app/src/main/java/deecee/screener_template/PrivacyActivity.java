package deecee.screener_template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class PrivacyActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_privacy);

        Button return_button = (Button) findViewById(R.id.privacy_returnButton);
        return_button.setOnClickListener(this);
    }

    @Override
    public void onBackPressed(){
        // purposefully empty to disable back button
    }

    public void onClick(View view){
        switch(view.getId()){
            case(R.id.privacy_returnButton):
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}