package mbatestseries.com.technologies.gyanjula.gtavantage.company.Util;

/**
 * Created by sooraj on 11-01-2017.
 */

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {
public static String API_BASE_URL="http://gyanjulatechnologies.com/MbaTestSeries/index.php/";
private static OkHttpClient.Builder httpClient=new OkHttpClient.Builder()
        .readTimeout(60,TimeUnit.SECONDS)
        .connectTimeout(60,TimeUnit.SECONDS);
    private static Retrofit.Builder builder=new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }




}
