package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.examPostPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sooraj on 26-01-2017.
 */

public interface examPostRequest {
    @GET("Company/exam_post")
    Call<examPostPOJO> requestResponse(@Query("city") String city,@Query("state") String state,@Query("cid") String cid, @Query("e_date") String date, @Query("e_time") String time, @Query("venue")String venue,@Query("job_post") String jobPost,@Query("job_desc") String jobDesc );
}
