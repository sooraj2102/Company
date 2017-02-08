package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import android.app.DownloadManager;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.examPostPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sooraj on 29-01-2017.
 */

public interface examStartRequest {
    @GET("Company/exam_start")
        Call<examPostPOJO>request(@Query("eid") String eid);
}
