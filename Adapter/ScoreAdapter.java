package mbatestseries.com.technologies.gyanjula.gtavantage.company.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


import java.util.List;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.R;

/**
 * Created by sooraj on 23-01-2017.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.viewHolder> {
    List<String> applicants,score,selectedCv,mobile;
    public ScoreAdapter(List<String> applicants,List<String> score,List<String> mobile){
        this.applicants=applicants;
        this.score=score;
        this.mobile=mobile;
    }

    //List<String> getSelectedCv()
    //{

    //}
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.applicants_adapter,parent,false);
        return new viewHolder(view);
    }

    @Override

    public void onBindViewHolder(viewHolder holder, int position) {
        holder.applicant.setText(Integer.toString(position+1)+" . "+applicants.get(position)+"\nScore : "+score.get(position)+"\nContact no : "+mobile.get(position));
    }

    @Override
    public int getItemCount() {
        return applicants.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CheckBox applicant;
        public viewHolder(View itemView) {
            super(itemView);
            applicant=(CheckBox)itemView.findViewById(R.id.applicant_check);

        }
    }
}
