package com.example.eattogether.APIs.ResponseModle;

import com.google.gson.annotations.SerializedName;

public class
LoginResponse {

    /*      "accessToken": "string",
                  "encryptedAccessToken": "string",
                  "expireInSeconds": 0,
                  "userId": 0*/

    @SerializedName("result")
    logRes result;

    public logRes getResult() {
        return result;
    }

    public void setResult(logRes result) {
        this.result = result;
    }

    public  class  logRes{
        @SerializedName("accessToken")
        String accessToken;
        @SerializedName("encryptedAccessToken")
        String encryptedAccessToken;
        @SerializedName("expireInSeconds")
        int expireInSeconds;
        @SerializedName("userId")
        int userId;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getEncryptedAccessToken() {
            return encryptedAccessToken;
        }

        public void setEncryptedAccessToken(String encryptedAccessToken) {
            this.encryptedAccessToken = encryptedAccessToken;
        }

        public int getExpireInSeconds() {
            return expireInSeconds;
        }

        public void setExpireInSeconds(int expireInSeconds) {
            this.expireInSeconds = expireInSeconds;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

}
