package com.example.eattogether.APIs;

import com.example.eattogether.APIs.ResponseModle.LoginResponse;
import com.example.eattogether.APIs.ResponseModle.PostMessageResponse;
import com.example.eattogether.Models.ChatDialogsModel;
import com.example.eattogether.Models.ChatListModel;
import com.example.eattogether.Models.Countries;
import com.example.eattogether.Models.FindPartnersModel;
import com.example.eattogether.Models.LoginModel;
import com.example.eattogether.Models.PostMessageModel;
import com.example.eattogether.Models.SingUpModel;
import com.example.eattogether.Models.SingUpResponseModel;
import com.example.eattogether.Models.SingleCountry;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {


    @POST("api/services/app/Account/Register")
    Call<SingUpResponseModel> registerNewUser(@Body SingUpModel user);


    @GET("api/services/app/Country/GetAllCountries")
    Call<Countries> getAllCountries();


    @POST("api/TokenAuth/Authenticate")
    Call<LoginResponse> login(@Body LoginModel login);


    @GET("api/services/app/Country/Get")
    Call<SingleCountry> getCountry(@Query("id") int id);

    @GET("api/services/app/CityPartner/GetAllCityPartners")
    Call<FindPartnersModel> findPartnersModel(@Query("CityId") int CityId,
                                              @Query("Gender") int Gender,
                                              @Query("MinAge") int MinAge,
                                              @Query("MaxAge") int MaxAge,
                                              @Query("SkipCount") int SkipCount,
                                              @Query("Date") String  Date,
                                              @Query("MaxResultCount") int MaxResultCount);

    @GET("api/services/app/UserInformation/DownloadImage/{id}/")
    Call<ResponseBody> downloadImage(@Path("id") int idUser);

    @GET("api/services/app/Chat/GetAllChatList")
    Call<ChatListModel> getAllChatList();

    @GET("api/services/app/Chat/GetDialogByChatId")
    Call<ChatDialogsModel> getDialogs(@Header("Id") String ChatID);
    @POST("api/services/app/Chat/PostChat")
    Call<PostMessageResponse> postMessage(@Body PostMessageModel model);

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
