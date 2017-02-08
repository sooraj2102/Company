package mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sooraj on 26-01-2017.
 */

public class examHistoryPOJO {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("date")
    @Expose
    private List<String> date = new ArrayList<>();
    @SerializedName("time")
    @Expose
    private List<String> time = new ArrayList<>();
    @SerializedName("job_profile")
    @Expose
    private List<String> jobProfile = new ArrayList<>();
    @SerializedName("city")
    @Expose
    private List<String> city =new ArrayList<>();
    @SerializedName("state")
    @Expose
    private List<String> state = new ArrayList<>();
    @SerializedName("venue")
    @Expose
    private List<String> venue = new ArrayList<>();
    @SerializedName("e_start")
    @Expose
    private List<String> eStart = new ArrayList<>();
    @SerializedName("e_done")
    @Expose
    private List<String> eDone = new ArrayList<>();
    @SerializedName("applicants")
    @Expose
    private List<String> applicants = new ArrayList<>();


    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getVenue() {
        return venue;
    }

    public void setVenue(List<String> venue) {
        this.venue = venue;
    }

    public List<String> getEStart() {
        return eStart;
    }
    public List<String> getApplicants() {
        return applicants;
    }


    public void setEStart(List<String> eStart) {
        this.eStart = eStart;
    }

    public List<String> getEDone() {
        return eDone;
    }

    public void setEDone(List<String> eDone) {
        this.eDone = eDone;
    }

    public List<String> getJobProfile() {
        return jobProfile;
    }


    public List<String> getCity() {
        return city;
    }


    public List<String> getState() {
        return state;
    }

}