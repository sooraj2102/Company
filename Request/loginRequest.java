package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import mbatestseries.com.technologies.gyanjula.gtavantage.company.POJO.loginPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sooraj on 26-01-2017.
 */

public interface loginRequest {
    @GET("Company/login")
    Call<loginPOJO> requestResponse(@Query("username") String username,@Query("password") String password);
}
