package com.certified.gadsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SubmissionActivity extends AppCompatActivity {

    private static final String TAG = "SubmissionActivity";
    private EditText firstName, lastName, emailAddress, projectLink;
    private Button btnSubmit;
    private ProgressBar progressBar;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        firstName = findViewById(R.id.et_first_name);
        lastName = findViewById(R.id.et_last_name);
        emailAddress = findViewById(R.id.et_email);
        projectLink = findViewById(R.id.et_project_link);
        btnSubmit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> startActivity(new Intent(SubmissionActivity.this, LeaderBoardActivity.class )));

        btnSubmit.setOnClickListener(view -> {
            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();
            String eAddress = emailAddress.getText().toString();
            String pLink = projectLink.getText().toString();
        });
    }
}