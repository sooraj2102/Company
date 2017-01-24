package mbatestseries.com.technologies.gyanjula.gtavantage.company;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sooraj on 23-01-2017.
 */

public class ExamDetailAdapter extends RecyclerView.Adapter<ExamDetailAdapter.view_holder> {
    List<String> ldate,ltime,lvenue,lapplicants;
    String buttonText;
    public ExamDetailAdapter(List<String> ldate,List<String> lvenue,List<String> ltime,List<String>lapplicants,String buttonText)
    {
        this.ldate=ldate;
        this.lvenue=lvenue;
        this.ltime=ltime;
        this.lapplicants=lapplicants;
        this.buttonText=buttonText;
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
            start.setText(buttonText);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(itemView.getContext(),Applicants.class));
                    ((PostExam)itemView.getContext()).finish();
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
        holder.date.setText(ldate.get(position));
        holder.venue.setText(lvenue.get(position));
        holder.time.setText(ltime.get(position));
        holder.applicants.setText(lapplicants.get(position));
        if(position!=0)
        holder.start.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return ldate.size();
    }
}
