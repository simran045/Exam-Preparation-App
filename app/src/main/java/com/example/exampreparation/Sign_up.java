package com.example.exampreparation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Sign_up extends AppCompatActivity {
    private EditText nameID,emailId,passId,pass1ID,ph;
    Button button;
    FirebaseAuth firebaseAuth;
    String name,email,pass,pass1,phone;
    FirebaseUser user;
    String Uid;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameID=findViewById(R.id.editText3);
        emailId=findViewById(R.id.editText4);
        passId=findViewById(R.id.editText5);
        pass1ID=findViewById(R.id.editText6);
        ph=findViewById(R.id.editText7);
        progressBar=findViewById(R.id.progressBar);

        button=findViewById(R.id.btn_register);

        firebaseAuth=FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                name = nameID.getText().toString();
                email = emailId.getText().toString();
                pass = passId.getText().toString();
                pass1 = pass1ID.getText().toString();
                phone = ph.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter name...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                else if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter Email ID...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                else if(pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter password...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                else if(pass1.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter confirm password...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                else if(phone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter contact number...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                else if(!(pass.equals(pass1)))
                {
                    Toast.makeText(getApplicationContext(), "Password and confirm Password does not match..", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                else {


                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        user = firebaseAuth.getCurrentUser();
                                        Uid = user.getUid();
                                        Toast.makeText(Sign_up.this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("name", name);
                                        hashMap.put("email", email);
                                        hashMap.put("phone", phone);
                                        hashMap.put("UID", Uid);
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                        databaseReference.child(Uid).setValue(hashMap);
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent=new Intent(Sign_up.this,Sign_In.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(Sign_up.this, "Some error occurred!!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });

    }
}
