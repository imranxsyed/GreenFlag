package com.isyed.greenflagv2.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DateFragment extends DialogFragment {

    private DateFragmentListener listener;

    /**
     * Calling host must implement this interface
     */
    public interface DateFragmentListener{
        void transferAge(String age);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (DateFragmentListener)context;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Calendar today = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext()
                                            ,getOnDateSetListner()
                                            ,today.get(Calendar.YEAR)
                                            ,today.get(Calendar.MONTH)
                                            ,today.get(Calendar.DAY_OF_MONTH)
                                            );

        return datePickerDialog;

    }

    private  DatePickerDialog.OnDateSetListener getOnDateSetListner(){

        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int age = determineAge(year,month,dayOfMonth);
                listener.transferAge(String.valueOf(age));
            }
        };
    }

    private int determineAge(int year, int month, int date){
        Calendar today = Calendar.getInstance();

        Calendar dob = Calendar.getInstance();
        dob.set(year,month,date);

        return today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    }


}
