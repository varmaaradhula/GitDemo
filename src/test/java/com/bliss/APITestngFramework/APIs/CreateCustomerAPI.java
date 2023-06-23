package com.bliss.APITestngFramework.APIs;

import com.bliss.APITestingFramework.Setup.BaseTest;
import com.bliss.APITestingFramework.utilities.DataUtil;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class CreateCustomerAPI extends BaseTest {

@Test(dataProviderClass= DataUtil.class,dataProvider = "TestData")
    public static Response sendPostRequestWithValidKaey(Hashtable<String,String> data){
        Response response = given().auth().basic(prop.getProperty("validSecretKey"), "").formParam("name", data.get("name"))
                .formParam("email",data.get("email")).formParam("description", data.get("description"))
                .post(prop.getProperty("customerAPIEndpoint"));

        return response;
    }


    @Test(dataProviderClass= DataUtil.class,dataProvider = "TestData")
    public static Response sendPostRequestWithInValidKaey(Hashtable<String,String> data){
        Response response = given().auth().basic(prop.getProperty("invalidSecretKey"), "").formParam("name", data.get("name"))
                .formParam("email",data.get("email")).formParam("description", data.get("description"))
                .post(prop.getProperty("customerAPIEndpoint"));

        return response;

        // This is my comment by varma.
    }
}
