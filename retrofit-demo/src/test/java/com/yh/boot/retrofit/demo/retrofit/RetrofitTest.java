package com.yh.boot.retrofit.demo.retrofit;

import com.alibaba.fastjson.JSON;
import com.yh.boot.retrofit.demo.pojo.User;
import com.yh.boot.retrofit.demo.pojo.UserQuery;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RetrofitTest {

    public interface UserGetService {
        @GET("user/{id}")
        Call<ResponseBody> getUserByUserId(@Path("id") Long id);

        @GET("user/all")
        Call<ResponseBody> getAllUser();

        @POST("user/query")
        Call<ResponseBody> findUserByQuery(@Body UserQuery userQuery);
    }

    @Test
    public void testGetUserByUserid() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8093/")
                .build();
        UserGetService service = retrofit.create(UserGetService.class);
        Call<ResponseBody> call = service.getUserByUserId(1L);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(100);
    }

    @Test
    public void testGetAllUser() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8093/")
                .build();
        UserGetService service = retrofit.create(UserGetService.class);
        Call<ResponseBody> allUserCall = service.getAllUser();
        allUserCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    List<User> users = JSON.parseArray(string, User.class);
                    users.forEach(System.out::println);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(100);
    }

    @Test
    public void testFindUserListByQuery() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8093/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserQuery userQuery = new UserQuery();
        userQuery.setName("cc");
        userQuery.setAge(25);
        UserGetService service = retrofit.create(UserGetService.class);
        Call<ResponseBody> userByQueryCall = service.findUserByQuery(userQuery);
        userByQueryCall.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    List<User> users = JSON.parseArray(string, User.class);
                    users.forEach(System.out::println);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(100);
    }

    @Test
    public void testGet() throws Exception {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8093/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        UserGetService service = retrofit.create(UserGetService.class);

        Call<ResponseBody> call = service.getUserByUserId(1L);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(100);

        TimeUnit.SECONDS.sleep(100);
    }

}
