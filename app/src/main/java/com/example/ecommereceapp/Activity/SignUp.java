package com.example.ecommereceapp.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ecommereceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    TextView textView;
    EditText mname,  memail, mpassword;
     Button btn1;
        FirebaseAuth firebaseAuth;
        FirebaseFirestore fstore;
        String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize textView
        textView = findViewById(R.id.have_an_account);
        mname= findViewById(R.id.editTextText2);
        memail=findViewById(R.id.editTextTextEmailAddress3);
        mpassword=findViewById(R.id.editTextTextPassword);
        btn1 = findViewById(R.id.button7);

         firebaseAuth=FirebaseAuth.getInstance();
         fstore=FirebaseFirestore.getInstance();
         if (firebaseAuth.getCurrentUser()!=null){
             startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
         }

         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String email= memail.getText().toString().trim();
                 String password= mpassword.getText().toString().trim();
                 String name= mname.getText().toString().trim();
                 if (TextUtils.isEmpty(email)){
                     memail.setError("email is required");
                     return;
                 }
                 if (TextUtils.isEmpty(password)){
                     mpassword.setError("password is required");
                     return;
                 }
                 if (password.length()<6){
                     mpassword.setError("password is must greater than 6");
                     return;
                 }
                 firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             Toast.makeText(SignUp.this,"USer created",Toast.LENGTH_SHORT).show();
                             UserId=firebaseAuth.getCurrentUser().getUid();
                             DocumentReference documentReference=fstore.collection("USER").document(UserId);
                             Map<String,Object> User=new HashMap<>();
                             User.put("Name",name);
                             User.put("Email",email);
                             User.put("Password",password);
                             documentReference.set(User).addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void unused) {
                                     Log.d(TAG,"onSuccesss: user is created for "+UserId);
                                 }
                             });
                             startActivity(new Intent(getApplicationContext(),MainActivity.class));
                         finish();
                         }else {
                             Toast.makeText(SignUp.this,"Error Founded"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                         }
                     }
                 });
             }
         });

        // Set click listener on textView
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}