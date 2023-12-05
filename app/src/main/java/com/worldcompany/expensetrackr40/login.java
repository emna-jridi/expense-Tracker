package com.worldcompany.expensetrackr40;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

  private EditText email;
  private EditText mdp ;
  private Button signup ;
private Button log;

private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();

        LogIn();
    }
    public void LogIn(){
        email = findViewById(R.id.email);
        mdp = findViewById(R.id.mdp);
        log = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chemail = email.getText().toString().trim();
                String chMdp = mdp.getText().toString().trim();

                if(TextUtils.isEmpty(chemail)){
                    email.setError("ce champs est obligatoire ");
                    return;
                }
                if(TextUtils.isEmpty(chMdp)){
                    mdp.setError("ce champs est obligatoire ");
                    return;
                }
                mAuth.signInWithEmailAndPassword(chemail,chMdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "signup fini", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        } else {

                            Toast.makeText(getApplicationContext(), "erreur", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent( getApplicationContext(),sign_up.class));
    }
});
}

}