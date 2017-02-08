package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.examPostPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sooraj on 30-01-2017.
 */

public interface examDoneRequest {
    @GET("Company/exam_done")
    Call<examPostPOJO> request(@Query("eid") String eid);
}
