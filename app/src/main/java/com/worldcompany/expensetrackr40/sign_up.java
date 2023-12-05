package com.worldcompany.expensetrackr40;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {
    private EditText email ;
    private EditText nom ;
    private EditText budget ;
    private EditText mdp ;
    private Button signUp;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();

        signUp();
    }
    public void signUp(){
        email = findViewById(R.id.email);
        nom = findViewById(R.id.nom);
        budget = findViewById(R.id.budget);
        mdp = findViewById(R.id.mdp);
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chEmail = email.getText().toString().trim();
                String chMdp = mdp.getText().toString().trim();
                float chBudget = Float.parseFloat(budget.getText().toString().trim());
                if(TextUtils.isEmpty(chEmail)){
                    email.setError("ce champs est obligatoire ");
                    return;
                }
                if(TextUtils.isEmpty(chMdp)){
                    mdp.setError("ce champs est obligatoire ");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(chEmail,chMdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task task) {
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

    }
}