
package com.wmtrucking;

import com.google.gson.JsonObject;

import com.google.gson.JsonArray;

import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;
@Component
public class CommonResponseService {

    public void setResponse(JsonObject response, int status, String message, JsonObject data) {
        response.addProperty("status", status);
        response.addProperty("message", message);
        response.add("data", data);
    }

    public void setResponseArray(JsonObject response, int status, String message, JsonArray data) {
        response.addProperty("status", status);
        response.addProperty("message", message);
        response.add("data", data);
    }

    public void setResponse(JsonObject response, int status, String message, JsonElement data) {
        response.addProperty("status", status);
        response.addProperty("message", message);
        response.add("data", data);
    }
    


}
