package com.example.project.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.R;

public class DocRegActivity extends AppCompatActivity {
    EditText etfullname, etidNo, etphoneNo;
    ImageView mImageView;
    Button reg, uploadImage;



    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_reg);

        etfullname = findViewById(R.id.fullnameEt);
        etidNo = findViewById(R.id.idNo);
        etphoneNo = findViewById(R.id.phoneNo);
        mImageView = findViewById(R.id.attachImage);
        reg = findViewById(R.id.registerButton);
        uploadImage = findViewById(R.id.uploadButton);


            uploadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_DENIED) {
                            //permission not granted,request it
                            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                            requestPermissions(permissions, PERMISSION_CODE);

                        } else {
                            pickImageFromGallery();
                        }
                    } else {
                        pickImageFromGallery();
                    }
                }
            });

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String fullname, idno, phone;
                    fullname = String.valueOf(etfullname.getText());
                    idno = String.valueOf(etidNo.getText());
                    phone = String.valueOf(etphoneNo.getText());

                    if (!fullname.equals("") && !idno.equals("") && !phone.equals("")) {
                        openNewActivity();

                    }else {
                        Toast.makeText(getApplicationContext(),"Please enter all details..",Toast.LENGTH_SHORT).show();
                        }
                }
            });

    }
    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length>0 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }else {
                    Toast.makeText(this,"Permission denied..",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            mImageView.setImageURI(data.getData());
        }
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void sendToDb(){
    }
}
