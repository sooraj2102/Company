package mbatestseries.com.technologies.gyanjula.gtavantage.company.Request;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by sooraj on 07-02-2017.
 */

public interface cvDownloadRequest {
    @GET
    Call<ResponseBody> download(@Url String link);
}
