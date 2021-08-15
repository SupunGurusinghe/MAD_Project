package com.example.notevibes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ResetKeyActivity extends AppCompatActivity {


    private ImageView imageBack;
    private Button setKeyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_key);

        //come back
        imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(ResetKeyActivity.this, MainActivity.class)
                );
            }
        });

        //reset key button
        setKeyButton = findViewById(R.id.setKeyButton);
        setKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(ResetKeyActivity.this, PasswordDecryptActivity.class)
                );
            }
        });
    }
}