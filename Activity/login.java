package mbatestseries.com.technologies.gyanjula.gtavantage.company.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.loginPOJO;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.R;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Request.loginRequest;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Util.NetworkCheck;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.Util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
EditText username,password;
    TextView register;
    Button login;
    String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        register=(TextView)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!NetworkCheck.isNetworkAvailable(login.this)){
                    Snackbar.make(findViewById(android.R.id.content),"Check your Internet Connection",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
                    Snackbar.make(findViewById(android.R.id.content), "Please fill the details completely", Snackbar.LENGTH_SHORT).show();
                    return;

                }
                final ProgressDialog progressDialog=new ProgressDialog(login.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                loginRequest request = ServiceGenerator.createService(loginRequest.class);
                Call<loginPOJO> call = request.requestResponse(username.getText().toString().trim(), password.getText().toString().trim());
                call.enqueue(new Callback<loginPOJO>() {
                    @Override
                    public void onResponse(Call<loginPOJO> call, Response<loginPOJO> response) {
                        progressDialog.dismiss();
                        if(!response.body().getError()) {
                            Snackbar.make(findViewById(android.R.id.content),"You are successfully logged in",Snackbar.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            cid=response.body().getCid();
                            bundle.putBoolean("click", false);
                            bundle.putString("cid", cid);
                            bundle.putString("buttonText", "Click To Continue");
                            startActivity(new Intent(login.this, PostExam.class).putExtras(bundle));
                            finish();
                        }
                        else
                            Snackbar.make(findViewById(android.R.id.content),"Error while login",Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<loginPOJO> call, Throwable t) {
                        t.printStackTrace();

                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, CompanyRegister.class));
                finish();
            }
        });
    }
}
