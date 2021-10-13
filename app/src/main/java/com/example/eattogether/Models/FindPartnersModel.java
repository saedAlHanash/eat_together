package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class FindPartnersModel {


    /*    {
            "result": [],
            "targetUrl": null,
                "success": true,
                "error": null,
                "unAuthorizedRequest": false,
                "__abp": true
        }
        */
    @SerializedName("result")
    ArrayList<ResultFindPartnersModel> result;
    @SerializedName("success")
    boolean success;


    class ResultFindPartnersModel {


            /*    [
        {
            "id": 0,
                "userId": 0,
                "cityId": 0,
                "minAge": 0,
                "maxAge": 0,
                "gender": 0,
                "date": "2021-10-06T13:28:24.653Z",
                "userName": "string",
                "cityName": "string"
        }
    ]
        */


        @SerializedName("id")
        int id;
        @SerializedName("userId")
        int userId;
        @SerializedName("cityId")
        int cityId;
        @SerializedName("minAge")
        int minAge;
        @SerializedName("maxAge")
        int maxAge;
        @SerializedName("gender")
        int gender;
        @SerializedName("date")
        String  date;
        @SerializedName("userName")
        String userName;
        @SerializedName("cityName")
        String cityName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getMinAge() {
            return minAge;
        }

        public void setMinAge(int minAge) {
            this.minAge = minAge;
        }

        public int getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(int maxAge) {
            this.maxAge = maxAge;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String  getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
    }

}
