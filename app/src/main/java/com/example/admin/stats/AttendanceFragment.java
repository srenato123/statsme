package com.example.admin.stats;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {


    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_attendance, container, false);

        String[] arraySpinner = new String[] {
                "MathPlus", "EnglishMinus", "Science"
        };
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final TextView studentid = (TextView) view.findViewById(R.id.studentid);
        final TextView studentinitial = (TextView) view.findViewById(R.id.studentinitial);
        final TextView studentname = (TextView) view.findViewById(R.id.studentname);

        final LinearLayout btnattendance = (LinearLayout) view.findViewById(R.id.btnattendance);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnattendance.setBackgroundResource(R.drawable.attendancebtn);

                studentid.setTextColor(Color.BLACK);
                studentinitial.setTextColor(Color.BLACK);
                studentname.setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*btnattendance.setBackgroundResource(R.drawable.attendancebtn);

        studentid.setTextColor(Color.BLACK);
        studentinitial.setTextColor(Color.BLACK);
        studentname.setTextColor(Color.BLACK);*/

        btnattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnattendance.setBackgroundResource(R.drawable.attendancebtn_pressed);

                studentid.setTextColor(Color.WHITE);
                studentinitial.setTextColor(Color.WHITE);
                studentname.setTextColor(Color.WHITE);
            }
        });
        spinner.setAdapter(adapter);

        return view;
    }

}
