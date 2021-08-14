package com.example.notevibes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_PASSWORD = 1;
    public static boolean isSet = true;


    private AlertDialog dialogSetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView addPasswordMain = findViewById(R.id.addPasswordMain);
        addPasswordMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSet){
                    startActivity(
                            new Intent(MainActivity.this, PasswordEncryptActivity.class)
                    );
                }else{
                    showSetPasswordDialog();
                }

            }
        });
    }


    //set password dialog
    public void showSetPasswordDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = LayoutInflater.from(this).inflate(
                R.layout.layout_set_password,
                (ViewGroup) findViewById(R.id.layoutSetPasswordContainer)
        );
        builder.setView(view);
        dialogSetPassword = builder.create();
        if (dialogSetPassword.getWindow() != null) {
            dialogSetPassword.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }



        view.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSetPassword.dismiss();
            }
        });
        dialogSetPassword.show();
    }
}