package com.example.simon.myapplicationpatipirma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnToSignUp;
    Button btnToChooseGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnToChooseGame = (Button) findViewById(R.id.btnLogin);
        btnToChooseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ChooseGameActivity.class);
                startActivity(i);
            }
        });

        btnToSignUp = (Button) findViewById(R.id.btnSignup);
        btnToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(ii);
            }
        });

    }
}
