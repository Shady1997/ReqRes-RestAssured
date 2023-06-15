package org.example.pages;

import io.restassured.response.Response;

public class PageBase {
    // Check Response Status Code
    public static boolean checkResponseStatusCode(Response res,int statusCode){
        if(res.getStatusCode()==statusCode)
            return true;
        else
            return false;
    }
    // check Response time
    public static boolean checkResponseTime(Response res, int responseTime){
        if(res.getTime() == responseTime)
            return true;
        else
            return false;
    }
}
