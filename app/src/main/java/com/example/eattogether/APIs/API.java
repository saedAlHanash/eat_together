package com.example.eattogether.APIs;

import com.example.eattogether.Models.AllCountriesModel;
import com.example.eattogether.Models.LoginModel;
import com.example.eattogether.Models.SingUpModel;
import com.example.eattogether.Models.SingUpResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @POST("api/services/app/Account/Register")
    Call<SingUpResponseModel> registerNewUser(@Body SingUpModel user);

    @GET("api/services/app/Country/GetAllCountries")
    Call<AllCountriesModel> getAllCountries();

    @POST("api/TokenAuth/Authenticate")
    Call<LoginResponse> login(@Body LoginModel login);



//    @POST("tms/register.php")
//    Call<ApiResponse> insertUser(@Body User user);
//
//    @POST("tms/insert_task.php")
//    Call<ApiResponse> insertTask(@Body ItemTask task);
//
//    @GET("tms/tojson.php")
//    Call<ArrayList<User>> getAllUsers();
//
//    @GET("tms/login.php")
//    Call<User> getUser(@Query("user_name") String userName, @Query("password") String password);
//
//    @FormUrlEncoded
//    @POST("tms/insert_group.php")
//    Call<ApiResponse> insertGroup(@Field("group_name") String  groupName, @Field("user_id") int userID);
//
//    @GET("tms/user_group.php")
//    Call<ArrayList<Group>> getGroups(@Query("user_id") int userID);
//
//    @GET("tms/return_tasks.php")
//    Call<ArrayList<ItemTask>> getAllTasksForGroup(@Query("group_id") int groupID);


}
