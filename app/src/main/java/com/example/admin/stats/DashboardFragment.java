package com.example.admin.stats;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class DashboardFragment extends Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }

    View mview;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mview = view;
        final ReminderListAdapter reminderListAdapter;

        Button addreminder = (Button) view.findViewById(R.id.addreminder);

        //TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog, onTimeSetListener, hour, minute, is24Hour);

        LayoutInflater inflater1 = getActivity().getLayoutInflater();

        addreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new AddReminderFragment()).commit();

                /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(inflater.inflate(R.layout.reminderdialog, null))
                        .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //LoginDialogFragment.this.getDialog().cancel();
                            }
                        });
                builder.create();
                builder.setTitle("ADD REMINDER");
                AlertDialog show = builder.show();*/
            }
        });

        final ListView reminderlist = (ListView) view.findViewById(R.id.reminderlist);
        final ArrayList<Reminder> reminderArrayList = new ArrayList<>();
        reminderListAdapter = new ReminderListAdapter(getContext(),R.layout.reminderlayout,reminderArrayList);
        reminderlist.setAdapter(reminderListAdapter);
        ListView mystatslist = (ListView) view.findViewById(R.id.mystatslist);
        ArrayList<MyStats> myStatsArrayList = new ArrayList<>();
        myStatsArrayList.add(new MyStats(1,"3 Absences","10:00 AM", "11/14/18","Mathplus"));
        MyStatsListAdapter myStatsListAdapter = new MyStatsListAdapter(getContext(),R.layout.mystatslayout,myStatsArrayList);
        mystatslist.setAdapter(myStatsListAdapter);
        Firebase.setAndroidContext(getContext());
        Firebase myFirebase = new Firebase("https://statsreminders.firebaseio.com/");
        myFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                reminderListAdapter.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Reminder reminder = postSnapshot.getValue(Reminder.class);
                    reminderArrayList.add(0,reminder);
                }
                reminderlist.smoothScrollToPosition(0);
                reminderListAdapter.notifyDataSetChanged();


                NotificationCompat.Builder
                        notifBuilder = new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(android.R.drawable.ic_menu_agenda)
                        .setContentTitle("STATS Notification")
                        .setContentText("New reminders have been broadcast")
                        .setContentIntent(PendingIntent.getActivity(getActivity()
                                ,0
                                ,new Intent(getActivity(),MainActivity.class)
                                ,PendingIntent.FLAG_NO_CREATE))
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notifBuilder.build());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        //final CardView btnupdate = (CardView) view.findViewById(R.id.btnupdate);
        //btnupdate.setOnClickListener(new View.OnClickListener() {

           // public void onClick(View v) {
                /*PopupMenu popupMenu = new PopupMenu(getActivity(),btnupdate);
                popupMenu.getMenuInflater().inflate(R.menu.optionmenu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("UPDATE")){
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new UpdateReminderFragment()).commit();
                        }else{
                            btnupdate.setVisibility(View.INVISIBLE);
                        }
                        return true;
                    }
                });

                popupMenu.show();*/
           // }
        //});

        //new BackgroundTask().execute();



        return view;
    }

    public void getJSON(View view){
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void,Void,String>{

        String json_url;


        @Override
        protected void onPreExecute() {
            json_url = "http://172.16.123.156/android/getalldata.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String response="";
                String line = "";

                while((line = bufferedReader.readLine())!=null){

                    response=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;

            }catch (Exception e){
                return ""+e;
            }
            //return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
class Reminder {

    public Reminder(){

    }
    private int id;
    private String subject;
    private String time;
    private String date;
    private String location;
    private String agenda;

    public Reminder(int id,String subject,String time,String date,String location,String agenda){
        this.id = id;
        this.subject = subject;
        this.time = time;
        this.date = date;
        this.location = location;
        this.agenda = agenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}

class ReminderListAdapter extends ArrayAdapter<Reminder> {

    private Context context;
    private int resource;
    private ArrayList arrayList;

    TextView tv_subject, tv_location, tv_time, tv_date;

    public ReminderListAdapter(Context context, int resource, ArrayList<Reminder> arrayList) {
        super(context, resource,arrayList);
        this.context =context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        int id = getItem(position).getId();
        String subject = getItem(position).getSubject();
        String time = getItem(position).getTime();
        String date = getItem(position).getDate();
        String location = getItem(position).getLocation();
        String agenda = getItem(position).getAgenda();

        Reminder reminder = new Reminder(id,subject,time,date,location,agenda);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        tv_subject = (TextView) convertView.findViewById(R.id.subject);
        tv_location = (TextView) convertView.findViewById(R.id.location);
        tv_time = (TextView) convertView.findViewById(R.id.time);
        tv_date = (TextView) convertView.findViewById(R.id.date);

        tv_subject.setText(reminder.getSubject());
        tv_location.setText(reminder.getLocation());
        tv_time.setText(reminder.getTime());
        tv_date.setText(reminder.getDate());


        return convertView;
    }
}

class MyStats {
    private int id;
    private String updateSubject;
    private String time;
    private String date;
    private String subject;

    public MyStats(int id,String updateSubject,String time,String date,String subject){
        this.id = id;
        this.updateSubject = updateSubject;
        this.time = time;
        this.date = date;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdateSubject() {
        return updateSubject;
    }

    public void setUpdateSubject(String updateSubject) {
        this.updateSubject = updateSubject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

class MyStatsListAdapter extends ArrayAdapter<MyStats> {

    private Context context;
    private int resource;
    private ArrayList arrayList;

    TextView tv_updateSubject, tv_subject, tv_time, tv_date;

    public MyStatsListAdapter(Context context, int resource, ArrayList<MyStats> arrayList) {
        super(context, resource,arrayList);
        this.context =context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        int id = getItem(position).getId();
        String updateSubject = getItem(position).getUpdateSubject();
        String subject = getItem(position).getSubject();
        String time = getItem(position).getTime();
        String date = getItem(position).getDate();

        MyStats myStats = new MyStats(id,updateSubject,time,date,subject);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        tv_updateSubject = (TextView) convertView.findViewById(R.id.updateSubject);
        tv_subject = (TextView) convertView.findViewById(R.id.subject);
        tv_time = (TextView) convertView.findViewById(R.id.time);
        tv_date = (TextView) convertView.findViewById(R.id.date);

        tv_updateSubject.setText(myStats.getUpdateSubject());
        tv_subject.setText(myStats.getSubject());
        tv_time.setText(myStats.getTime());
        tv_date.setText(myStats.getDate());


        return convertView;
    }
}



