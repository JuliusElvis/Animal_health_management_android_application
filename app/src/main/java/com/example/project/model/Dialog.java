package com.example.project.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.project.R;

public class Dialog {
    private Activity activity;
    private AlertDialog dialog;

    public Dialog(Activity myActivity){
        activity = myActivity;
    }

    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }
    public void dismissDialog(){
        dialog.dismiss();
    }

}
