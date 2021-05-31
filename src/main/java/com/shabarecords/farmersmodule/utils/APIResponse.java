package com.shabarecords.farmersmodule.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class APIResponse implements Serializable {


    private int status;


    private String message;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private String timestamp;


    public APIResponse() {
        this.timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(ZonedDateTime.now());
    }

    public static APIResponse ofError(String message) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(1);
        apiResponse.setMessage(message);

        return apiResponse;
    }


    public static APIResponse ofSuccess() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(0);
        apiResponse.setMessage("Success");

        return apiResponse;
    }


    public static APIResponse ofResponse(String message) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(2);
        apiResponse.setMessage(message);

        return apiResponse;
    }



    public static APIResponse ofInternalServerError() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(5);
        apiResponse.setMessage("Internal Server error occurred while processing your request");

        return apiResponse;
    }




}
