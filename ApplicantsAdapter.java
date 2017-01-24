package mbatestseries.com.technologies.gyanjula.gtavantage.company;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;

import java.util.List;

/**
 * Created by sooraj on 23-01-2017.
 */

public class ApplicantsAdapter extends RecyclerView.Adapter<ApplicantsAdapter.viewHolder> {
    List<String> applicants;
    public ApplicantsAdapter(List<String> applicants){
        this.applicants=applicants;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.applicants_adapter,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.applicant.setText(applicants.get(position));
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

