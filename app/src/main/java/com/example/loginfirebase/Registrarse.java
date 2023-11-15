package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo, pass, vpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.etmailreg);
        pass = findViewById(R.id.etpassreg);
        vpass = findViewById(R.id.etvpass);
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    public void registrarUsuario(View view){
        if(pass.getText().toString().equals(vpass.getText().toString())){
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Usuario Registrado con éxito", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Registrarse.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }


            });
        }
    }
}