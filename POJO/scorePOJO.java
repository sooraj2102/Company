package mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sooraj on 30-01-2017.
 */
public class scorePOJO {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("eid")
    @Expose
    private String eid;
    @SerializedName("name")
    @Expose
    private List<String> name = new ArrayList<>();
    @SerializedName("score")
    @Expose
    private List<String> score = new ArrayList<>();
    @SerializedName("mobile")
    @Expose
    private List<String> mobile = new ArrayList<>();
    @SerializedName("cv")
    @Expose
    private List<String> cv = new ArrayList<>();

    public Boolean getError() {
        return error;
    }
    public String getEid() {
        return eid;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
    public List<String> getScore() {
        return score;
    }

    public List<String> getMobile() {
        return mobile;
    }

    public void setMobile(List<String> mobile) {
        this.mobile = mobile;
    }

    public List<String> getCv() {
        return cv;
    }

    public void setCv(List<String> cv) {
        this.cv = cv;
    }

}