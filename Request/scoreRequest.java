package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.scorePOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sooraj on 30-01-2017.
 */

public interface scoreRequest {
    @GET("Company/leader")
    Call<scorePOJO> requuest(@Query("cid") String cid,@Query("date") String date,@Query("time") String time,@Query("venue") String venue);
}
