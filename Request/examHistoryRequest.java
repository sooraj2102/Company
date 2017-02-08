package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.examHistoryPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sooraj on 26-01-2017.
 */

public interface examHistoryRequest {
    @GET("Company/exam_history")
    Call<examHistoryPOJO> requestResponse(@Query("cid") String cid);
}
