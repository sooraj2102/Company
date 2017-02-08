package mbatestseries.com.technologies.gyanjula.gtavantage.company.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.Adapter.ExamDetailAdapter;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.examHistoryPOJO;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.examPostPOJO;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.R;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Request.examHistoryRequest;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Request.examPostRequest;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Util.NetworkCheck;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Util.SelectDateFragment;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostExam extends AppCompatActivity implements View.OnClickListener {
EditText date,time,venue,jobPost,jobDesc,city,state;
    int id;
    TextView title;
    ProgressDialog progressDialog;
    boolean f=false;
    Boolean onDoubleBackPress=false;
    Button submit,examDetails;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<String> ldate,ltime,lvenue,lapplicants,lstart,ldone,ljobPost,lcity,lstate;
    String buttonText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_exam);
        date=(EditText)findViewById(R.id.date);
        time=(EditText)findViewById(R.id.time);
        venue=(EditText)findViewById(R.id.venue);
        title=(TextView)findViewById(R.id.title);
        jobPost=(EditText)findViewById(R.id.job_post);
        jobDesc=(EditText)findViewById(R.id.job_desc);
        city=(EditText)findViewById(R.id.city);
        state=(EditText)findViewById(R.id.state);
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
        lstart=new ArrayList<>();
        ldone=new ArrayList<>();
        recyclerView.setVisibility(View.GONE);
        progressDialog=new ProgressDialog(PostExam.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
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
        buttonText=extra.getString("buttonText","Error");
        Log.e("buttonTextout", buttonText);
        if(click)
        {
          //  title.setText("Exam History");
            //date.setVisibility(View.GONE);
            //time.setVisibility(View.GONE);
            //venue.setVisibility(View.GONE);
            //submit.setVisibility(View.GONE);
            //examDetails.setVisibility(View.GONE);
            //recyclerView.setVisibility(View.VISIBLE);
            exam();
            Log.e("buttonTextin", buttonText);

        }
        submit.setOnClickListener(this);
        examDetails.setOnClickListener(this);

    }
    @Override
        public void onClick(View view) {

        id = view.getId();
        Log.e(Integer.toString(R.id.submit),Integer.toString(getTaskId()));

        if(!NetworkCheck.isNetworkAvailable(this))
        {
            Snackbar.make(findViewById(android.R.id.content),"Check Your Internet Connection",Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (id ==R.id.submit){

            String sdate,stime,svenue,sjobPost,sjobDesc,scity,sstate;
            sdate=date.getText().toString().trim();
            stime=time.getText().toString().trim();
            svenue=venue.getText().toString().trim();
            sjobPost=jobPost.getText().toString().trim();
            sjobDesc=jobDesc.getText().toString().trim();
            scity=city.getText().toString().trim();
            sstate=state.getText().toString().trim();
            if (!NetworkCheck.isNetworkAvailable(this))
            {
                Snackbar.make(findViewById(android.R.id.content),"Check Your Internet Connection",Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(sdate.equals("")||svenue.equals("")||stime.equals("")||sjobPost.equals("")||sjobDesc.equals("")||scity.equals("")||sstate.equals("")){
                Snackbar.make(findViewById(android.R.id.content),"Please fill details completely",Snackbar.LENGTH_SHORT).show();
                return;
            }
            progressDialog.show();
            examPostRequest request= ServiceGenerator.createService(examPostRequest.class);

            //   if(getIntent().getExtras().getString("cid").equals("")){
           //     startActivity(new Intent(this,long.class));
             //   finishAffinity();
               // Toast.makeText(this, "Your login session has expired", Toast.LENGTH_LONG).show();
            //}
            Call<examPostPOJO> call=request.requestResponse(scity,sstate,getIntent().getExtras().getString("cid"),sdate,stime,svenue,sjobPost,sjobDesc);
            Log.e("COMpany id",getIntent().getExtras().getString("cid"));
            call.enqueue(new Callback<examPostPOJO>() {
                @Override
                public void onResponse(Call<examPostPOJO> call, Response<examPostPOJO> response) {
                    progressDialog.dismiss();
                    if(response.body().getMsg()==400)
                    {
                        Toast.makeText(PostExam.this, "There is already a test of your company on same date and time", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(!response.body().getError())
                    {
                        Toast.makeText(PostExam.this, "Your Exam is Posted Successfully", Toast.LENGTH_SHORT).show();
                        //id =R.id.examdetails;
                        exam();
                        Log.e("Entered","submit");
                    }
                    else
                        Toast.makeText(PostExam.this, "Error in Posting Exam", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<examPostPOJO> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(PostExam.this, "Error in Posting Exam", Toast.LENGTH_SHORT).show();
                }
            });
            //update database
        }

        if (id==R.id.examdetails) {
            exam();
        }
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
            jobPost.setVisibility(View.VISIBLE);
            jobDesc.setVisibility(View.VISIBLE);
            city.setVisibility(View.VISIBLE);
            state.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            onDoubleBackPress=false;

        }
        else
         super.onBackPressed();
    }
    void exam()
    {

        final examHistoryRequest request=ServiceGenerator.createService(examHistoryRequest.class);
        Call<examHistoryPOJO> call=request.requestResponse(getIntent().getExtras().getString("cid"));
        progressDialog.show();
        call.enqueue(new Callback<examHistoryPOJO>() {
            @Override
            public void onResponse(Call<examHistoryPOJO> call, Response<examHistoryPOJO> response) {
                progressDialog.dismiss();
                if(!response.body().getError()) {

                    title.setText("Exam History");
                    date.setText("");
                    time.setText("");
                    venue.setText("");
                    submit.setText("");
                    examDetails.setText("");
                    jobDesc.setText("");
                    jobPost.setText("");
                    city.setText("");
                    state.setText("");
                    date.setVisibility(View.GONE);
                    time.setVisibility(View.GONE);
                    venue.setVisibility(View.GONE);
                    submit.setVisibility(View.GONE);
                    examDetails.setVisibility(View.GONE);
                    jobDesc.setVisibility(View.GONE);
                    jobPost.setVisibility(View.GONE);
                    city.setVisibility(View.GONE);
                    state.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    onDoubleBackPress=true;
                    ldate=response.body().getDate();
                    ltime=response.body().getTime();
                    lvenue=response.body().getVenue();
                    lapplicants=response.body().getApplicants();
                    lstart=response.body().getEStart();
                    ldone=response.body().getEDone();
                    ljobPost=response.body().getJobProfile();
                    lcity= response.body().getCity();
                    lstate=response.body().getState();
                    adapter=new ExamDetailAdapter(ljobPost,lcity,lstate,ldate,lvenue,ltime,lapplicants,buttonText,lstart,ldone,getIntent().getExtras().getString("cid"));
                    recyclerView.setAdapter(adapter);
                    Log.e("Entered exam details",buttonText);
                }
            }

            @Override
            public void onFailure(Call<examHistoryPOJO> call, Throwable t) {
                t.printStackTrace();
                Log.e("Failed",examDetails.getText().toString());
            }
        });
    }

}
