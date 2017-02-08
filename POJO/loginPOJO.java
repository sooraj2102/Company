package mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class loginPOJO {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("cname")
    @Expose
    private String cname;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

}
