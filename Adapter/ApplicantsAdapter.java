package mbatestseries.com.technologies.gyanjula.gtavantage.company.Adapter;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


import java.util.List;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.R;

/**
 * Created by sooraj on 23-01-2017.
 */
//Changes for HactoberFest 2019
public class ApplicantsAdapter extends RecyclerView.Adapter<ApplicantsAdapter.viewHolder> {
    List<String> applicants,selectedMobile,mobile;
    // OnItemCheckListener onItemCheckListener;
    public ApplicantsAdapter(List<String> applicants,List<String> mobile){
        this.applicants=applicants;
      this.mobile=mobile;
        //this.onItemCheckListener=onItemCheckListener;
    }


 /*   public interface OnItemCheckListener{
        void onItemCheck(String mob);
        void onItemUncheck(String mob);
    }
   */

    public List<String> getMobile() {
        return mobile;
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
        public viewHolder(final View itemView) {
            super(itemView);
            applicant=(CheckBox)itemView.findViewById(R.id.applicant_check);
         /*   applicant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("Size", Integer.toString(mobile.size()));
                    if(applicant.isChecked()){
                        onItemCheckListener.onItemCheck(mobile.get(getAdapterPosition()));
                        Log.e("Size check", Integer.toString(mobile.size()));

                    }
                    else {
                        onItemCheckListener.onItemUncheck(mobile.get(getAdapterPosition()));
                        Log.e("Size uncheck", Integer.toString(mobile.size()));

                    }
                }
           });
*/
        }
    }
}

