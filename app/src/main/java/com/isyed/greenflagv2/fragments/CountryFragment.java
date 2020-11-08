package com.isyed.greenflagv2.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.support.v4.app.*;
import android.view.ContextThemeWrapper;

import com.isyed.greenflagv2.R;

public class CountryFragment extends DialogFragment {

    private boolean selectedItem = false;
    private boolean dialogCanceled = false;
    private CountryFragmentListener listener;
    public static final String CANCEL = "CANCEL";
    /**
     * Listner that host class must implement for the callback
     */
    public interface CountryFragmentListener{
        void passData(String country);
    }


    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(),R.style.customDialogStyle));
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose your country");


        //setting countries
        CharSequence[] countries = getResources().getStringArray(R.array.countries);
        builder.setItems(countries, (dialog,which) -> {

            selectedItem = true;
            //close the dialog
            dialog.dismiss();

            //pass data
            listener.passData(countries[which].toString());
        });

        //setting cancel button
        builder.setNegativeButton("Cancel", (dialog,which) ->{
            dialogCanceled = true;
            listener.passData(CANCEL);
        });

        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (CountryFragmentListener) context;
    }

    @Override
    public void onStop() {
        super.onStop();

        if (!selectedItem && !dialogCanceled){
            listener.passData(CANCEL);
        }

    }
}
