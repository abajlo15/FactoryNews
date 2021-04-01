package com.abajlo.factorynews.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Dialogs extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showErrorDialog(this, "Greška", "Ups, došlo je do pogreške");

    }

    public void showErrorDialog(Context context, String title, String message){
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setPositiveButton("U redu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
              .setTitle(title)
              .setMessage(message)
              .create();
        alertDialog.show();
    }


}
