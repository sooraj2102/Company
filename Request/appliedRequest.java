package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.appliedPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sooraj on 29-01-2017.
 */

public interface appliedRequest {
    @GET("Company/app")
    Call<appliedPOJO> request(@Query("cid") String cid,@Query("date") String date,@Query("time") String time,@Query("venue") String venue);
}
