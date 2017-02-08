package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import android.support.annotation.Nullable;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.examPostPOJO;
import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.otpPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static org.json.JSONObject.NULL;

/**
 * Created by sooraj on 30-01-2017.
 */

public interface otpRequest {
    @GET("TestSeries/comp_sent_otp")
    Call<otpPOJO> request(@Query("eid") String eid, @Query("mob") String mob);
}
