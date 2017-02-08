package mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sooraj on 26-01-2017.
 */

public class examPostPOJO {
    @SerializedName("error")
    @Expose
    Boolean error;
    public Boolean getError() {
        return error;
    }
    @SerializedName("msg")
    @Expose
    int msg;
    public int getMsg() {
        return msg;
    }
}
