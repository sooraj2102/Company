package mbatestseries.com.technologies.gyanjula.gtavantage.company.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.Activity.*;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.R;

import static java.security.AccessController.getContext;

/**
 * Created by sooraj on 23-01-2017.
 */

public class ExamDetailAdapter extends RecyclerView.Adapter<ExamDetailAdapter.view_holder> {
    List<String> ldate,ltime,lvenue,lapplicants,lstart,ldone,ljobPost,lcity,lstate;
    String cid;
    String buttonText;
    String button;
    public ExamDetailAdapter(List<String> ljobPost,List<String> lcity,List<String> lstate,List<String> ldate,List<String> lvenue,List<String> ltime,List<String>lapplicants,String buttonText,List<String>lstart,List<String>ldone,String cid)
    {
        this.ldate=ldate;
        this.lvenue=lvenue;
        this.ltime=ltime;
        this.lapplicants=lapplicants;
        this.buttonText=buttonText;
        this.lstart=lstart;
        this.ldone=ldone;
        this.cid=cid;
        this.ljobPost=ljobPost;
        this.lcity=lcity;
        this.lstate=lstate;
    }
    public class view_holder extends RecyclerView.ViewHolder {
        TextView date,time,venue,applicants; Button start;
        public view_holder(final View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.datetext);
            time=(TextView)itemView.findViewById(R.id.timetext);
            venue=(TextView)itemView.findViewById(R.id.venuetext);
            applicants=(TextView)itemView.findViewById(R.id.applicants);
            start=(Button)itemView.findViewById(R.id.start_test);


            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(lapplicants.get(getAdapterPosition()).equals("0")) {

                        Toast.makeText(view.getContext(), "No Applicants yet!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(start.getText().equals("Click Here to See Results")) {
                        Bundle bundle=new Bundle();
                        bundle.putString("buttonText","Click to download the CV \'s");
                        bundle.putString("cid",cid);
                        bundle.putString("date",ldate.get(getAdapterPosition()));
                        bundle.putString("time",ltime.get(getAdapterPosition()));
                        bundle.putString("venue",lvenue.get(getAdapterPosition()));

                        view.getContext().startActivity(new Intent(itemView.getContext(), Applicants.class)
                                .putExtras(bundle));
                      //  else if(lstart.get(getAdapterPosition()).equals("1")&&ldone.get(getAdapterPosition()).equals("0")){
                        //    start.setEnabled(false);

                        }

                    else {
                        Bundle bundle=new Bundle();
                        bundle.putString("buttonText", "Click to Start Test");
                        bundle.putString("cid",cid);
                        bundle.putString("date",ldate.get(getAdapterPosition()));
                        bundle.putString("time",ltime.get(getAdapterPosition()));
                        bundle.putString("venue",lvenue.get(getAdapterPosition()));
                        view.getContext().startActivity(new Intent(itemView.getContext(), Applicants.class)
                                .putExtras(bundle));
                        //((PostExam)itemView.getContext()).finish();
                    }
                }
            });

        }
    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.examdetails,parent,false);
        return new view_holder(view);
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {
        holder.date.setText("Date of Exam: "+ldate.get(position));
        holder.venue.setText("Venue of Exam :"+lvenue.get(position)+" \n "+lcity.get(position)+" \n "+lstate.get(position));
        holder.time.setText("Time of Exam :"+ltime.get(position));
        holder.applicants.setText("No. of Applicants :"+lapplicants.get(position));
        if(lstart.get(position).equals("1")&&ldone.get(position).equals("0")) {
           button="Click Here to See Results";
            holder.start.setText(button);
        }
        else if(lstart.get(position).equals("1")&&ldone.get(position).equals("1"))
        {
            holder.start.setVisibility(View.GONE);
        }
        else if(lstart.get(position).equals("0")){
           button="Click To Continue";
            holder.start.setText(button);
        }
       // if(!ldate.get(position).equals(Calendar.getInstance().getTime().toString())){
         //   holder.start.setEnabled(false);
           // Log.e(Calendar.getInstance().getTime().toString(),ldate.get(position)+ltime.get(position));
       // }
        Log.e(Calendar.getInstance().getTime().toString(),ldate.get(position)+ltime.get(position));

    }

    @Override
    public int getItemCount() {
        return ldate.size();
    }
}
