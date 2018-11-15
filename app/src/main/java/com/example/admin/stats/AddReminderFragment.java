package com.example.admin.stats;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddReminderFragment extends Fragment {


    public AddReminderFragment() {
        // Required empty public constructor
    }
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_reminder, container, false);

        final EditText time = (EditText) view.findViewById(R.id.time);
        time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    timepickershow(time);

                    //Toast.makeText(getContext(), "asdasd", Toast.LENGTH_SHORT).show();
                }
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timepickershow(time);
            }
        });

        final EditText date = (EditText) view.findViewById(R.id.date);
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    datepickershow(date);
                    //Toast.makeText(getContext(), "asdasd", Toast.LENGTH_SHORT).show();
                }
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickershow(date);
            }
        });

        Button back = (Button) view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new DashboardFragment()).commit();
            }
        });

        return view;
    }

    public void timepickershow(final EditText time){


        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        //dont show keyboard
        time.setInputType(0);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String meridian = "AM";
                        if(hourOfDay>12){
                            hourOfDay-=12;
                            meridian="PM";
                        }
                        if(hourOfDay==0){
                            hourOfDay=12;
                            meridian="AM";
                        }
                        String minute_string = minute+"";
                        if(minute_string.length()>1){
                            minute_string="";
                        }else{
                            minute_string="0";
                        }

                        time.setText(hourOfDay + ":" + minute_string+minute+" "+meridian);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void datepickershow(final EditText date){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //dont show keyboard
        date.setInputType(0);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        //time.setText();
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
