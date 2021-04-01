package com.abajlo.factorynews.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.abajlo.factorynews.R;

public class LoadingDialog {

    public Activity activity;
    public AlertDialog dialog;

    public LoadingDialog(Activity myActivity){

        activity = myActivity;

    }

    public void startLoadingDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}
