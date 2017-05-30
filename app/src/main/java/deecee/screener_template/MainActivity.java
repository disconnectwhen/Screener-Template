package deecee.screener_template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Button continueButton = (Button) findViewById(R.id.main_continueButton);
        continueButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch(item.getItemId()){
            case(R.id.option_about):
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case(R.id.option_contact):
                intent = new Intent(this, ContactActivity.class);
                startActivity(intent);
                return true;
            case(R.id.option_privacy):
                intent = new Intent(this, PrivacyActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onBackPressed(){
        // purposefully empty to disable back button
    }

    public void onClick(View view){
        switch(view.getId()){
            case(R.id.main_continueButton):
                Intent intent = new Intent(this, InfoOneActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
