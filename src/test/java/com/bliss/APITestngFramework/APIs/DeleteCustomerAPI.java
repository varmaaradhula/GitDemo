package com.bliss.APITestngFramework.APIs;

import com.bliss.APITestingFramework.Setup.BaseTest;
import com.bliss.APITestingFramework.utilities.DataUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class DeleteCustomerAPI extends BaseTest {

    @Test(dataProviderClass = DataUtil.class, dataProvider = "TestData")
    public static Response sendDeleteRequestWithValidID(Hashtable<String, String> data) {
        Response response = given().auth().basic(prop.getProperty("validSecretKey"), "")
                .delete(prop.getProperty("customerAPIEndpoint")+"/"+data.get("customerId"));

        return response;
    }
}
