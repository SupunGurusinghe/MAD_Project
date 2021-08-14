package com.example.notevibes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PasswordEncryptActivity extends AppCompatActivity {

    //Default Key
    public static String defaultKey = "abc";
    String temp = "";




    private ImageView imageBack;
    private ImageView imageNote;
    private Button encryptButton;
    private TextView textDateTime;
    private TextView textWebUrl;
    private LinearLayout layoutWebUrl;
    private LinearLayout layoutForgotPassword;
    private EditText titleEdit;
    private EditText passwordEdit;
    private EditText descriptionEdit;
    private Button mButton;
    private TextView inputURL;
    private EditText keyEdit;
    private String selectedImagePath;
    private String title, password, description, Url, key;
    private AlertDialog dialogAddUrl;
    private AlertDialog dialogIncorrectPassword;
    private AlertDialog dialogChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_encrypt);


        imageBack = findViewById(R.id.imageBack);
        encryptButton = findViewById(R.id.encryptButton);
        textDateTime = findViewById(R.id.textDateTime);
        textWebUrl = findViewById(R.id.textWebUrl);
        layoutWebUrl = findViewById(R.id.layoutWebUrl);

        //come back
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        //update current date and time
        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())

        );

        //get inputs
        mButton = (Button) findViewById(R.id.encryptButton);
        titleEdit = (EditText) findViewById(R.id.InputPasswordTitle);
        passwordEdit = (EditText)findViewById(R.id.inputPassword);
        descriptionEdit = (EditText) findViewById(R.id.inputDescription);
        keyEdit = (EditText) findViewById(R.id.key);
        inputURL = (TextView) findViewById(R.id.textWebUrl);


            mButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendData();
                        }
                    }
            );



        // add url dialog layout popup
        LinearLayout layoutAddUrl = findViewById(R.id.layoutAddUrl);
        layoutAddUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddUrlDialog();
            }
        });


        findViewById(R.id.imageRemoveWebUrl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textWebUrl.setText(null);
                layoutWebUrl.setVisibility(View.GONE);
            }
        });

        findViewById(R.id.imageRemoveImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageNote.setImageBitmap(null);
                imageNote.setVisibility(View.GONE);
                findViewById(R.id.imageRemoveImage).setVisibility(View.GONE);
                selectedImagePath = "";

            }
        });


        //change password
        layoutForgotPassword = findViewById(R.id.layoutForgotPassword);
        layoutForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangePasswordDialog();
            }
        });



    }






    private void showAddUrlDialog() {
        if (dialogAddUrl == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(PasswordEncryptActivity.this);
            View view = LayoutInflater.from(this).inflate(
                    R.layout.layout_add_url,
                    (ViewGroup) findViewById(R.id.layoutAddUrlContainer)
            );
            builder.setView(view);

            dialogAddUrl = builder.create();
            if (dialogAddUrl.getWindow() != null) {
                dialogAddUrl.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            final EditText inputUrl = view.findViewById(R.id.inputUrl);
            inputUrl.requestFocus();

            view.findViewById(R.id.textAdd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (inputUrl.getText().toString().trim().isEmpty()) {
                        Toast.makeText(PasswordEncryptActivity.this, "Enter URL", Toast.LENGTH_SHORT).show();
                    } else if (!Patterns.WEB_URL.matcher(inputUrl.getText().toString()).matches()) {
                        Toast.makeText(PasswordEncryptActivity.this, "Enter valid URL", Toast.LENGTH_SHORT).show();
                    } else {
                        textWebUrl.setText(inputUrl.getText().toString());
                        layoutWebUrl.setVisibility(View.VISIBLE);
                        dialogAddUrl.dismiss();
                    }
                }
            });

            view.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogAddUrl.dismiss();
                }
            });
        }
        dialogAddUrl.show();
    }





    //send Data method
    public void sendData(){
        title = titleEdit.getText().toString().trim();
        password = passwordEdit.getText().toString().trim();
        Url = inputURL.getText().toString().trim();
        description = descriptionEdit.getText().toString().trim();
        key = keyEdit.getText().toString().trim();

        if(key.equals(defaultKey)){

            char[] pwd = password.toCharArray();

            String temp = "";
            for(char c: pwd) {
                c += 5;
                temp = temp + c;
            }

            Intent i = new Intent(PasswordEncryptActivity.this, PasswordDecryptActivity.class);

            i.putExtra(PasswordDecryptActivity.TITLE, title);
            i.putExtra(PasswordDecryptActivity.PASSWORD, temp);
            i.putExtra(PasswordDecryptActivity.URL, Url);
            i.putExtra(PasswordDecryptActivity.DESCRIPTION, description);

            startActivity(i);
        }else{
            showPasswordIncorrectDialog();
        }


    }


    //password incorrect dialog
    private void showPasswordIncorrectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PasswordEncryptActivity.this);
        View view = LayoutInflater.from(this).inflate(
                R.layout.layout_password_incorrect,
                (ViewGroup) findViewById(R.id.layoutIncorrectPasswordContainer)
        );
        builder.setView(view);
        dialogIncorrectPassword = builder.create();
        if (dialogIncorrectPassword.getWindow() != null) {
            dialogIncorrectPassword.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }



        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogIncorrectPassword.dismiss();
            }
        });
        dialogIncorrectPassword.show();
    }


    //password change dialog
    private void showChangePasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PasswordEncryptActivity.this);
        View view = LayoutInflater.from(this).inflate(
                R.layout.layout_change_password,
                (ViewGroup) findViewById(R.id.layoutChangePasswordContainer)
        );
        builder.setView(view);
        dialogChangePassword = builder.create();
        if (dialogChangePassword.getWindow() != null) {
            dialogChangePassword.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }



        view.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogChangePassword.dismiss();
            }
        });
        dialogChangePassword.show();
    }

}

