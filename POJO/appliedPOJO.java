package mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO;
        import java.util.ArrayList;
        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class appliedPOJO {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("eid")
    @Expose
    private String eid;
    @SerializedName("mobile")
    @Expose
    private List<String> mobile = new ArrayList<>();
    @SerializedName("applicants")
    @Expose
    private List<String> applicants = new ArrayList<>();

    public Boolean getError() {
        return error;
    }


    public String getEid() {
        return eid;
    }


    public List<String> getMobile() {
        return mobile;
    }



    public List<String> getApplicants() {
        return applicants;
    }



}