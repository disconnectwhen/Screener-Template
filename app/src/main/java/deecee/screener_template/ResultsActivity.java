package deecee.screener_template;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        TextView result = (TextView) findViewById(R.id.score_textview);
        result.append(intent.getStringExtra("score"));

        Button restart_button = (Button) findViewById(R.id.restart_button);
        Button website_button = (Button) findViewById(R.id.website_button);

        restart_button.setOnClickListener(this);
        website_button.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        // Purposefully empty to disable back button.
    }

    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case(R.id.restart_button):
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case(R.id.website_button):
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}