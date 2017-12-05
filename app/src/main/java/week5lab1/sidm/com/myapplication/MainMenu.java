package week5lab1.sidm.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener{

    // Define buttons
    private Button btn_start;
    private Button btn_credits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.mainmenu);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this); // Set listener to this button ---> Start Button

        //btn credits
        // btn_credits = (Button)findViewById(R.id.btn_credits);
        // btn_credits.setOnClickListener(this);
    }
    @Override
    // Invoke a callback event in view
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();

        if(v == btn_start)
        {
            // intent ---> to set to another class which another page
            // or screen that we are launching
            //intent.setClass(this, SplashPage.class);
            intent.setClass(this, GamePage.class);
        }
        // else if (v == btn_credits)
        //{
            // intent.setClass(this, Credits.class);
        //}
        startActivity(intent);


    }
}

