package com.example.admin.stats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);

        final LinearLayout btnattendance = (LinearLayout) view.findViewById(R.id.btnattendance);
        btnattendance.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Toast.makeText(getContext(), "hasFocus", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "hasFocusnt", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
