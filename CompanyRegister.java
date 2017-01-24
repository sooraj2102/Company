package mbatestseries.com.technologies.gyanjula.gtavantage.company;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CompanyRegister extends AppCompatActivity {

    EditText comp_name,job_name,job_desc,comp_desc,salary,exp,reg_start,reg_end,note,website,ven,tim;
    Button reg;
    Spinner ten_per,twel_per,grad_per;
    int k;
    Calendar myCalendar = Calendar.getInstance();
    List<String> per1=new ArrayList<String>(),per2=new ArrayList<String>(),per3=new ArrayList<String>();
    ArrayAdapter<String> adapter1,adapter2,adapter3;
    int pos1,pos2,pos3;

    String ve,etim,cname,jname,jdesc,cdesc,sal,ex,reg_st,reg_en,no,web,tper,twper,gper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);

        comp_name=(EditText)findViewById(R.id.comp_name);
        job_name=(EditText)findViewById(R.id.job_name);
        job_desc=(EditText)findViewById(R.id.job_desc);
        comp_desc=(EditText)findViewById(R.id.comp_desc);
        salary=(EditText)findViewById(R.id.salary);
        exp=(EditText)findViewById(R.id.exp_req);
        reg_start=(EditText)findViewById(R.id.reg_start_date);
        reg_end=(EditText)findViewById(R.id.reg_end_date);
        note=(EditText)findViewById(R.id.note);
        website=(EditText)findViewById(R.id.comp_website);
        ven=(EditText)findViewById(R.id.venue);
        tim=(EditText)findViewById(R.id.event_time);
        ten_per=(Spinner) findViewById(R.id.ten_per_required);
        twel_per=(Spinner) findViewById(R.id.twel_per_required);
        grad_per=(Spinner) findViewById(R.id.grad_per_required);
        reg=(Button)findViewById(R.id.comp_reg);


        per1.add("10th% required");
        per1.add("no bar");
        per1.add("60");
        per1.add("70");
        per1.add("80");
        adapter1= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,per1);
        ten_per.setAdapter(adapter1);
        ten_per.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos1=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        per2.add("12th% required");
        per2.add("no bar");
        per2.add("60");
        per2.add("70");
        per2.add("80");
        adapter2= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,per2);
        twel_per.setAdapter(adapter2);
        twel_per.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos2=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        per3.add("Graduation% required");
        per3.add("no bar");
        per3.add("60");
        per3.add("70");
        per3.add("80");
        adapter3= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,per3);
        grad_per.setAdapter(adapter3);
        grad_per.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos3=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
               /* if(k==0) {
                    updateLabel(reg_end);
                }*/
                if(k==1) {
                    updateLabel(reg_start);
                }
            }

        };


        reg_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                k=1;
                // TODO Auto-generated method stub
                new DatePickerDialog(CompanyRegister.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cname=comp_name.getText().toString();
                jname=job_name.getText().toString();
                jdesc=job_desc.getText().toString();
                cdesc=comp_desc.getText().toString();
                sal=salary.getText().toString();
                ex=exp.getText().toString();
                reg_st=reg_start.getText().toString();
                reg_en=reg_end.getText().toString();
                no=note.getText().toString();
                web=website.getText().toString();
                tper=per1.get(pos1);
                twper=per2.get(pos2);
                ve=ven.getText().toString();
                etim=tim.getText().toString();
                gper=per3.get(pos3);

                if(NetworkCheck.isNetworkAvailable(getApplicationContext())) {

                    if(cname.equals("") || jname.equals("") || jdesc.equals("") || cdesc.equals("") || sal.equals("") ||
                            ex.equals("") || reg_st.equals("") || reg_en.equals("") || no.equals("") || web.equals("") ||
                            tper.equals("") || twper.equals("") || ve.equals("") || etim.equals("") || gper.equals("")||
                            pos1==0 || pos2==0 || pos3==0)
                    {
                        Snackbar.make(findViewById(android.R.id.content), "Please fill all entries", Snackbar.LENGTH_LONG)
                                .setActionTextColor(Color.RED)
                                .show();
                    }
                    else{
                    register(cname, jname, jdesc, cdesc, sal, ex, reg_st, reg_en, no, web, tper, twper, gper,ve,etim);
                }
                }
                else{
                    Snackbar.make(findViewById(android.R.id.content), "No Network Connection", Snackbar.LENGTH_LONG)
                            .show();
                }

                /*Intent intent=new Intent(CompanyRegister.this,LaunchActivity.class);
                startActivity(intent);*/
            }
        });

    }

    private void updateLabel(EditText edittext) {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }


    public void register(final String cname,final String jname,final String jdesc,final String cdesc,final String sal,final String ex,final String reg_st,final String reg_en,final String no,final String web,final String tper,final String twper,final String gper,final String venue,final String event_time){
        class RegisterAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(CompanyRegister.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                String cna = params[0];
                String jna=params[1];
                String jdes=params[2];
                String cdes=params[3];
                String sa=params[4];
                String ex=params[5];
                String rs=params[6];
                String ren=params[7];
                String note=params[8];
                String we=params[9];
                String tp=params[10];
                String twp=params[11];
                String gp=params[12];
                String ven=params[13];
                String etime=params[14];

                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", cna));
                nameValuePairs.add(new BasicNameValuePair("job_post", jna));
                nameValuePairs.add(new BasicNameValuePair("job_desc", jdes));
                nameValuePairs.add(new BasicNameValuePair("comp_desc", cdes));
                nameValuePairs.add(new BasicNameValuePair("salary", sa));
                nameValuePairs.add(new BasicNameValuePair("experience", ex));
                nameValuePairs.add(new BasicNameValuePair("event_date", rs));
                nameValuePairs.add(new BasicNameValuePair("per_req", ren));
                nameValuePairs.add(new BasicNameValuePair("note", note));
                nameValuePairs.add(new BasicNameValuePair("website", we));
                nameValuePairs.add(new BasicNameValuePair("10th", tp));
                nameValuePairs.add(new BasicNameValuePair("12th", twp));
                nameValuePairs.add(new BasicNameValuePair("graduation", gp));
                nameValuePairs.add(new BasicNameValuePair("venue", ven));
                nameValuePairs.add(new BasicNameValuePair("event_time", etime));
                Log.e("detail",nameValuePairs.toString());


                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://gyanjulatechnologies.com/MbaTestSeries/index.php/TestSeries/company_registration");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }


            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                loadingDialog.dismiss();
                Intent intent=new Intent(CompanyRegister.this,login.class);
                startActivity(intent);
                finish();

            }
        }

        RegisterAsync la = new RegisterAsync();
        la.execute(cname,jname,jdesc,cdesc,sal,ex,reg_st,reg_en,no,web,tper,twper,gper,venue,event_time);

    }

}
