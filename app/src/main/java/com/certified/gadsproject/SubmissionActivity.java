package com.certified.gadsproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.certified.gadsproject.retrofit.ApiClient;
import com.certified.gadsproject.retrofit.Submit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SubmissionActivity";

//    Widgets
    private EditText firstName, lastName, emailAddress, projectLink;
    private Button btnSubmit, btnConfirm;
    private ProgressBar progressBar;
    private ImageView back, close;

//    fields
    public static String fName;
    public static String lName;
    public static String eAddress;
    public static String pLink;

//    dialog
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

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

        back.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                startActivity(new Intent(SubmissionActivity.this, LeaderBoardActivity.class));
//            finish();
                super.onBackPressed();
                break;

            case R.id.btn_submit:
                Log.d(TAG, "onClick: Launching confirmation dialog");
                displayConfirmationDialog();
                break;
        }
    }

    private void submitProject() {

        fName = firstName.getText().toString();
        lName = lastName.getText().toString();
        eAddress = emailAddress.getText().toString();
        pLink = projectLink.getText().toString();

        Call<Void> call = ApiClient.submitProjectApi().submitProject(fName, lName, eAddress, pLink);

        progressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "onResponse: Input: \nFirst name: " + fName + "\nLast name: " +
                lName + "\nEmail: " + eAddress + "\nProject link: " + pLink);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                progressBar.setVisibility(View.GONE);

                Log.d(TAG, "onResponse: Code " + response.code());

                int code = response.code();

                if (!response.isSuccessful()) {
                    Log.d(TAG, "onFailure: Launching failure dialog");
                    Log.d(TAG, "onResponse: error: " + response.code());

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(SubmissionActivity.this, "An error occurred: "
                            + response.code(), Toast.LENGTH_SHORT).show();

                    displayFailureDialog();
                    return;
                }

                Log.d(TAG, "onResponse: launching success dialog");
                Log.d(TAG, "onResponse: successful: " + response.code());
                Log.d(TAG, "onResponse: Input: \nFirst name: " + fName + "\nLast name: " +
                        lName + "\nEmail: " + eAddress + "\nProject link: " + pLink);

                Toast.makeText(SubmissionActivity.this, "Success: "
                        + response.code(), Toast.LENGTH_SHORT).show();

                displaySuccessDialog();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.d(TAG, "onFailure: Launching failure dialog");
                Log.d(TAG, "onFailure: An error occurred: " + throwable.getMessage());

                progressBar.setVisibility(View.GONE);

                Toast.makeText(SubmissionActivity.this, "An error occurred: "
                        + throwable.getMessage(), Toast.LENGTH_SHORT).show();

                displayFailureDialog();
            }
        });
    }

    public class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.btn_confirm:
                    submitProject();
                    alertDialog.dismiss();
                    break;

                case R.id.close:
                    alertDialog.dismiss();
                    break;
            }
        }
    }

    public void displayConfirmationDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.confirmation_dialog, null);

        alertDialog = builder.create();
        alertDialog.setView(v);

        ButtonListener buttonListener = new ButtonListener();
        btnConfirm = v.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(buttonListener);
        close = v.findViewById(R.id.close);
        close.setOnClickListener(buttonListener);

        alertDialog.show();
    }

    public void displaySuccessDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.success_dialog, null);

        alertDialog = builder.create();
        alertDialog.setView(v);
        alertDialog.show();
    }

    public void displayFailureDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.failure_dialog, null);

        alertDialog = builder.create();
        alertDialog.setView(v);
        alertDialog.show();
    }
}