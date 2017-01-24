package mbatestseries.com.technologies.gyanjula.gtavantage.company;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PostExam extends AppCompatActivity implements View.OnClickListener {
EditText date,time,venue;
    SelectDateFragment dateFragment=new SelectDateFragment();
    TextView title;
    Boolean onDoubleBackPress=false;
    Button submit,examDetails;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<String> ldate,ltime,lvenue,lapplicants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_exam);
        date=(EditText)findViewById(R.id.date);
        time=(EditText)findViewById(R.id.time);
        venue=(EditText)findViewById(R.id.venue);
        title=(TextView)findViewById(R.id.title);
        submit=(Button)findViewById(R.id.submit);
        examDetails=(Button)findViewById(R.id.examdetails);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_examdetails);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ldate=new ArrayList<>();
        lvenue=new ArrayList<>();
        ltime=new ArrayList<>();
        lapplicants=new ArrayList<>();
        recyclerView.setVisibility(View.GONE);
       date.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              // dateFragment.show(getSupportFragmentManager(),"",date,"","");
               Calendar mcurrentDate = Calendar.getInstance();
               int mYear = mcurrentDate.get(Calendar.YEAR);
               int mMonth = mcurrentDate.get(Calendar.MONTH);
               int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog mDatePicker;
               mDatePicker = new DatePickerDialog(PostExam.this, new DatePickerDialog.OnDateSetListener() {
                   public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                       selectedmonth = selectedmonth + 1;
                       date.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                   }
               }, mYear, mMonth, mDay);
               mDatePicker.setTitle("Select Date");
               mDatePicker.show();

           }
       });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PostExam.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        Bundle extra= getIntent().getExtras();
        Boolean click=extra.getBoolean("click",false);
        String buttonText=extra.getString("buttonText","Error");
        Log.e("buttonTextout", buttonText);
        if(click)
        {
            title.setText("Exam History");
            date.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
            venue.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
            examDetails.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            Log.e("buttonTextin", buttonText);

        }
        for (int i=1;i<=10;i++)
        {
            ldate.add("Date: "+"2017/02/2"+Integer.toString(i));
            lvenue.add("Venue: "+"KIET,Muradnagar,Ghaziabad,UP");
            ltime.add("Time: "+"02:30 am");
            lapplicants.add("No. of Applicants: "+Integer.toString(i+20));
        }
        adapter=new ExamDetailAdapter(ldate,lvenue,ltime,lapplicants,buttonText);
        recyclerView.setAdapter(adapter);
        submit.setOnClickListener(this);
        examDetails.setOnClickListener(this);

    }
    @Override
        public void onClick(View view) {
        int id=getTaskId();
        if (id==R.id.submit){



            if(date.getText().toString().trim().equals("")||
                    venue.getText().toString().trim().equals("")||
                    time.getText().toString().trim().equals("")){
                Snackbar.make(findViewById(android.R.id.content),"Please fill details completely",Snackbar.LENGTH_SHORT).show();
                return;
            }
            //update database
        }

        else if (id==R.id.examdetails) {

        }
        title.setText("Exam History");
        date.setVisibility(View.GONE);
        time.setVisibility(View.GONE);
        venue.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        examDetails.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        onDoubleBackPress=true;

    }
    @Override
    public void onBackPressed() {
        if(onDoubleBackPress)
        {
            title.setText("Post Your Exam Here");
            date.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
            venue.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
            examDetails.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            onDoubleBackPress=false;

        }
        else
        super.onBackPressed();
    }

}
