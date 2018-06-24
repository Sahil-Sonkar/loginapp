package com.example.reborux.login_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText UserId;
    private EditText Password;
    private Button Login;
    private Button SignUp;
    private TextView Info;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserId = (EditText)findViewById(R.id.ET_User_Id);
        Password = (EditText)findViewById(R.id.ET_NPassword);
        Login = (Button) findViewById(R.id.BTN_Log_in);
        Info = (TextView) findViewById(R.id.TV_Info);
        SignUp = (Button)findViewById(R.id.BTN_Sing_Up);

        Info.setText("No of attempts left: 5");

        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(UserId.getText().toString(),Password.getText().toString());
            }
        });

    }

    private void validate(String userName, String userPassword){
        if(userName=="Admin"&& userPassword=="1234"){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else{
            counter--;

            Info.setText("No. of attempts left: "+String.valueOf(counter));

            if(counter==0){
                Login.setEnabled(false);
            }
        }
    }
}
