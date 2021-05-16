package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.resetPass.PassActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailActivity extends AppCompatActivity {

    EditText etMessage;
    TextInputEditText etSubject;
    Button sendmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        etSubject = findViewById(R.id.subject);
        etMessage = findViewById(R.id.message);
        sendmail = findViewById(R.id.sendmail);

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmail();
            }
        });

    }
    private void sendmail(){
        String recMail = "juliuselvis56@gmail.com";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{recMail});
        intent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());

        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent,"choose an email client"));
    }
}
