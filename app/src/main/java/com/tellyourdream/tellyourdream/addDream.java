package com.tellyourdream.tellyourdream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.google.android.gms.ads.AdView;

public class addDream extends AppCompatActivity {

    // initialize the views
    private EditText dreamEditText, dreamEditEditText;
    private TextView dreamTranslation;
    //private AdView adView;
    private Button actionButton;

    //details variables
    private String owner;
    private String dream;
    private String dreamDate;
    private int age;
    private String marriageStatus;
    private String Gender;
    private String Privacy;
    private String Reply;
    private String userEmail;
    private String replyStatus;
    private String openStatus;
    private String parentKey;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dream);

        //find the views in the activity
        dreamEditText = findViewById(R.id.editText_dream_details);
        dreamTranslation = findViewById(R.id.textView_translation);
        actionButton = findViewById(R.id.action_button);

        actionButton = findViewById(R.id.action_button);



    }
}
