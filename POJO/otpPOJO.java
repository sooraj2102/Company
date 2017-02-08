package mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO;

/**
 * Created by sooraj on 03-02-2017.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class otpPOJO {

    @SerializedName("otp")
    @Expose
    private List<Integer> otp = new ArrayList<>();
    @SerializedName("mobile")
    @Expose
    private List<String> mobile = new ArrayList<>();

    public List<Integer> getOtp() {
        return otp;
    }

    public void setOtp(List<Integer> otp) {
        this.otp = otp;
    }

    public List<String> getMobile() {
        return mobile;
    }

    public void setMobile(List<String> mobile) {
        this.mobile = mobile;
    }
}