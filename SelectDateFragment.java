package mbatestseries.com.technologies.gyanjula.gtavantage.company;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    String date,min,max;
    EditText et;
    Date d = null;
    Date minDate = null;
    Date maxDate = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {

            d = sdf.parse(date);
            minDate=sdf.parse(min);
            maxDate=sdf.parse(max);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(d);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog d = new DatePickerDialog(getActivity(), this, year, month, day);
        d.getDatePicker().setMaxDate(maxDate.getTime());
        d.getDatePicker().setMinDate(minDate.getTime());
        // Create a new instance of DatePickerDialog and return it
        return d;
    }

    public void show(FragmentManager manager, String tag, EditText et, String minDate, String max) {
        super.show(manager, tag);
        this.date = tag;
        this.et=et;
        this.min=minDate;
        this.max=max;
    }
    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm + 1, dd);
    }



    public void populateSetDate(int year, int month, int day) {
        et.setText(year+"-"+ String.format("%02d", month) + "-" +String.format("%02d", day) );
    }
}