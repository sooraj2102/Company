package mbatestseries.com.technologies.gyanjula.gtavantage.company;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
EditText username,password;
    TextView register;
    Button login;
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
                if(username.getText().toString().trim().equals("")||password.getText().toString().trim().equals(""))
                {
                    Snackbar.make(findViewById(android.R.id.content),"Please fill the details completely",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle=new Bundle();
                bundle.putBoolean("click",false);
                bundle.putString("buttonText","Click To Continue");
                startActivity(new Intent(login.this,PostExam.class).putExtras(bundle));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,CompanyRegister.class));
                finish();
            }
        });
    }
}
