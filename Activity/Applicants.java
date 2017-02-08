package mbatestseries.com.technologies.gyanjula.gtavantage.company.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Adapter.ApplicantsAdapter;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Adapter.ScoreAdapter;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.appliedPOJO;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.examPostPOJO;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.otpPOJO;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.scorePOJO;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.R;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Request.appliedRequest;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Request.examDoneRequest;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Request.examStartRequest;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Request.otpRequest;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Request.scoreRequest;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Util.NetworkCheck;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Util.ServiceGenerator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Applicants extends AppCompatActivity {
    List<String> applicants,mobile,score,a,selectedMobile;String mob="";
    RecyclerView.Adapter adapter,scoreAdapter;
    ApplicantsAdapter applicantsAdapter;
    Button submit;
    String eid;
    Boolean click=false;


    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        setContentView(R.layout.activity_applicants);
        final RecyclerView recyclerView;
        recyclerView=(RecyclerView)findViewById(R.id.applicant_recycler);
        submit=(Button)findViewById(R.id.submit);
        submit.setText(getIntent().getStringExtra("buttonText"));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        applicants=new ArrayList<>();

       if(getIntent().getStringExtra("buttonText").equals("Click to Start Test")) {
            progressDialog.show();
            //Name of Applicants

             appliedRequest request=ServiceGenerator.createService(appliedRequest.class);
            Call<appliedPOJO> call=request.request(
                    getIntent().getExtras().getString("cid"),
                    getIntent().getExtras().getString("date"),
                    getIntent().getExtras().getString("time"),
                    getIntent().getExtras().getString("venue")
                    );
            call.enqueue(new Callback<appliedPOJO>() {
                @Override
                public void onResponse(Call<appliedPOJO> call, Response<appliedPOJO> response) {
                    progressDialog.dismiss();
                    if(!response.body().getError())
                    {
                        selectedMobile=mobile=response.body().getMobile();
                        applicants=response.body().getApplicants();
                        eid=response.body().getEid();
                        applicantsAdapter=new ApplicantsAdapter(applicants, mobile);
                        adapter=applicantsAdapter;
                        selectedMobile=applicantsAdapter.getMobile();
                        recyclerView.setAdapter(adapter);

                        for(int i=0;i<selectedMobile.size();i++) {
                            if(i==0)
                            mob = mob+selectedMobile.get(i);
                            else
                                mob=mob+","+selectedMobile.get(i);

                        }

                    }
                    else
                    {
                        Toast.makeText(Applicants.this, "There is no Applicants yet!", Toast.LENGTH_LONG).show();
                        finish();
                    }

                }

                @Override
                public void onFailure(Call<appliedPOJO> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!NetworkCheck.isNetworkAvailable(Applicants.this)){
                        Snackbar.make(findViewById(android.R.id.content),"Check your Internet Connection",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    click = true;
                     //otp
                    progressDialog.show();
                  // Log.e(Integer.toString(mobile.size()),Integer.toString(selectedMobile.size()));
                    Log.e("Mobile no",mob);
                    otpRequest request=ServiceGenerator.createService(otpRequest.class);
                    Call<otpPOJO> call5=request.request(eid,mob);
                    call5.enqueue(new Callback<otpPOJO>() {
                        @Override
                        public void onResponse(Call<otpPOJO> call, Response<otpPOJO> response) {

                        }

                        @Override
                        public void onFailure(Call<otpPOJO> call, Throwable t) {

                        }
                    });
                    //update exam start
                    startTest();
                    String buttonText = "Click Here to See Results";

                    Bundle bundle = new Bundle();
                    bundle.putString("buttonText", buttonText);
                    bundle.putBoolean("click", click);
                    bundle.putString("cid",getIntent().getExtras().getString("cid"));
                    Intent intent = new Intent(Applicants.this, PostExam.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else{
           progressDialog.show();
           //list of applicants with score
           scoreRequest request=ServiceGenerator.createService(scoreRequest.class);
           Call<scorePOJO> call=request.requuest(
                   getIntent().getExtras().getString("cid"),
                   getIntent().getExtras().getString("date"),
                   getIntent().getExtras().getString("time"),
                   getIntent().getExtras().getString("venue")
           );
           call.enqueue(new Callback<scorePOJO>() {
               @Override
               public void onResponse(Call<scorePOJO> call, Response<scorePOJO> response) {
                   progressDialog.dismiss();
                   if(!response.body().getError())
                   { List<String> mobile=new ArrayList<String>();
                       score=response.body().getScore();
                       applicants=response.body().getName();
                       eid=response.body().getEid();
                       mobile=response.body().getMobile();
                       for(int i=0;i<score.size();i++)
                       {
                           for(int j=0;j<score.size()-i-1;j++){
                               if(Integer.parseInt(score.get(j))<Integer.parseInt(score.get(j+1)))
                               {
                                   Collections.swap(score,j,j+1);
                                   Collections.swap(a,j,j+1);
                               }

                           }
                       }
                       scoreAdapter=new ScoreAdapter(applicants,score,mobile);
                       recyclerView.setAdapter(scoreAdapter);

                   }
                   else
                   {
                       Toast.makeText(Applicants.this, "Problem in connection", Toast.LENGTH_SHORT).show();
                   }


               }

               @Override
               public void onFailure(Call<scorePOJO> call, Throwable t) {
                   t.printStackTrace();
               }
           });

           submit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (!NetworkCheck.isNetworkAvailable(Applicants.this)){
                       Snackbar.make(findViewById(android.R.id.content),"Check your Internet Connection",Snackbar.LENGTH_SHORT).show();
                       return;
                   }
                   progressDialog.show();
                   // CV download

                   //update event done
                   examDoneRequest doneRequest=ServiceGenerator.createService(examDoneRequest.class);
                   Call<examPostPOJO> call4=doneRequest.request(eid);
                   call4.enqueue(new Callback<examPostPOJO>() {
                       @Override
                       public void onResponse(Call<examPostPOJO> call, Response<examPostPOJO> response) {
                           progressDialog.dismiss();
                           //Log.e("eid",eid);
                           if (!response.body().getError()){
                               Toast.makeText(Applicants.this, "Exam is Successfully Completed", Toast.LENGTH_LONG).show();



                           }
                           else{
                               Toast.makeText(Applicants.this, "There is some error, please try again later", Toast.LENGTH_LONG).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<examPostPOJO> call, Throwable t) {
                           t.printStackTrace();
                       }
                   });
               }
           });
       }

    }
    void startTest(){

        Log.e("eid",eid);
        examStartRequest startRequest=ServiceGenerator.createService(examStartRequest.class);
        Call<examPostPOJO> call=startRequest.request(eid);
        call.enqueue(new Callback<examPostPOJO>() {
            @Override
            public void onResponse(Call<examPostPOJO> call, Response<examPostPOJO> response) {
                  progressDialog.dismiss();
                if (!response.body().getError()){
                    Toast.makeText(Applicants.this, "Exam is Successfully Started", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(Applicants.this, "There is some error, please try again later", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<examPostPOJO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "Future Studio Icon.png");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("writeResponseBodyToDisk", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
