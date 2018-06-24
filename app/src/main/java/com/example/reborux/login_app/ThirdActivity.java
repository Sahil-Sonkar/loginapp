package com.example.reborux.login_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ThirdActivity extends AppCompatActivity {

    private EditText FName,LName,UserId,Password,Password2,Email;
    private Button SignUp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String user_email = Email.getText().toString().trim();
                    String password = Password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ThirdActivity.this, "Registration Sucessful!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ThirdActivity.this, MainActivity.class));
                            }
                            else
                                Toast.makeText(ThirdActivity.this, "Registration UnSucessful!!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void setupUIViews(){
        FName = (EditText)findViewById(R.id.ET_FName);
        LName = (EditText)findViewById(R.id.ET_LName);
        UserId = (EditText)findViewById(R.id.ET_NUserId);
        Password = (EditText)findViewById(R.id.ET_NPassword);
        Password2 = (EditText)findViewById(R.id.ET_NPassword2);
        Email = (EditText)findViewById(R.id.ET_EmaiId);
    }

    private Boolean validate(){
        Boolean result =false;

        String fnme = FName.getText().toString();
        String lnme = LName.getText().toString();
        String uid = UserId.getText().toString();
        String pass = Password.getText().toString();
        String pass2 = Password2.getText().toString();
        String email = Email.getText().toString();

        if(fnme.isEmpty() || lnme.isEmpty() || pass.isEmpty() || uid.isEmpty() || pass2.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please enter all the details.", Toast.LENGTH_SHORT).show();
        }
        else if(!(pass.equals(pass2))){
            Toast.makeText(this,"Passwords do not match.", Toast.LENGTH_SHORT).show();
        }
        else{
            result=true;
        }
        return result;
    }
}
