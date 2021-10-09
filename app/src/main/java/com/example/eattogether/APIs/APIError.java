package com.example.eattogether.APIs;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class APIError {
    @SerializedName("success")
    boolean success;
    @SerializedName("error")
    Error error;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

   public class Error {
        @SerializedName("message")
        String message;
        @SerializedName("details")
        String details;
       @SerializedName("validationErrors")
      ArrayList<ValidationErrors> validationErrors;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

       public ArrayList<ValidationErrors> getValidationErrors() {
           return validationErrors;
       }

       public void setValidationErrors(ArrayList<ValidationErrors> validationErrors) {
           this.validationErrors = validationErrors;
       }

       public class ValidationErrors{
            @SerializedName("message")
            String message;
            @SerializedName("members")
            String [] members;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String[] getMembers() {
                return members;
            }

            public void setMembers(String[] members) {
                this.members = members;
            }

        }
    }

}
