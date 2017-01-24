package mbatestseries.com.technologies.gyanjula.gtavantage.company;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
public class Applicants extends AppCompatActivity {
    List<String> applicants;
    RecyclerView.Adapter adapter;
    Button submit;
    Boolean click=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants);
        final RecyclerView recyclerView;
        recyclerView=(RecyclerView)findViewById(R.id.applicant_recycler);
        submit=(Button)findViewById(R.id.submit);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        applicants=new ArrayList<>();
        for(int i=1;i<=30;i++)
        {
            applicants.add("Applicant"+Integer.toString(i));
        }
        adapter=new ApplicantsAdapter(applicants);
        recyclerView.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click=true;
                String buttonText="Click Here to See Results";
                Bundle bundle=new Bundle();
                bundle.putString("buttonText",buttonText);
                bundle.putBoolean("click",click);
                Intent intent=new Intent(Applicants.this,PostExam.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }

}
