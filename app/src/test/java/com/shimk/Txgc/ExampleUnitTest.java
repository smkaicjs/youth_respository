package com.shimk.Txgc;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.baseUrl("https://www.baidu.com")
                .addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit1 = retrofit.build();
        test api = retrofit1.create(test.class);
        Call<String> call = api.getbaidu();
        System.out.println("start");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                System.out.println("error");
            }
        });
    }
    interface test{
        @GET("/")
        Call<String> getbaidu();
    }
}