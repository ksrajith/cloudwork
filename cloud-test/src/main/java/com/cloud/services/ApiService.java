package com.cloud.services;

import com.cloud.config.ConfigFactory;
import com.cloud.pojo.RequestObj;
import com.cloud.rest.RequestSender;
import io.restassured.response.Response;

public class ApiService {

    RequestObj requestObj = new RequestObj();
    RequestSender sender = new RequestSender();

    /**
     * Prepare create request object
     * @param requestJson
     * @param urlSuffix
     * @return reponse from the server
     */
    public Response setCreateUserRequestObject(String requestJson, String urlSuffix){
        requestObj.setUrl(ConfigFactory.getConfig().apiUrl()+urlSuffix);
        requestObj.setJsonBody(requestJson);
        requestObj.setServiceType("POST");
        return sender.apiRequestSend(requestObj);
    }

        /**
     * Prepare get user details request
     * @param urlSuffix
     * @return reponse from the server
     */
    public Response getUserRequestObject(String urlSuffix){
        requestObj.setUrl(ConfigFactory.getConfig().apiUrl()+urlSuffix);
        requestObj.setServiceType("GET");
        return sender.apiRequestSend(requestObj);
    }


    /**
     * Prepare update user request object
     * @param request
     * @param urlSuffix
     * @return reponse from the server
     */
    public Response setUpdateRequestObject(String request, String urlSuffix){
        requestObj.setUrl(ConfigFactory.getConfig().apiUrl()+urlSuffix);
        requestObj.setJsonBody(request);
        requestObj.setServiceType("PUT");
        return sender.apiRequestSend(requestObj);
    }


    /**
     * Prepare delete user request
     * @param urlSuffix
     * @return reponse from the server
     */
    public Response deleteUserRequestObject(String urlSuffix){
        requestObj.setUrl(ConfigFactory.getConfig().apiUrl()+urlSuffix);
        requestObj.setServiceType("DELETE");
        return sender.apiRequestSend(requestObj);
    }


}
