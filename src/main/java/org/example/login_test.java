package org.example;

import convertedPojo.Users;
import convertedPojo.UsersResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.IResultMap;
import org.testng.annotations.Test;


public class login_test {
    public static String baseurl = "https://petstore.swagger.io/v2";

    @Test
    public void test() {
        Users request=new Users();

        request.setEmail("juu@gmail.com");
        request.setId(1331);
        request.setFirstName("Q");
        request.setLastName("SAD");
        request.setUsername("WWW");
        request.setPassword("1254");
        request.setPhone("7894561230");
        request.setUserStatus(0);

//        JSONObject request = new JSONObject();
//        request.put("id", "122211");
//        request.put("username", "jujujuju");
//        request.put("firstName", "P");
//        request.put("lastName", "Das");
//        request.put("email", "j@gmail.com");
//        request.put("phone", "8457047958");
//        request.put("userStatus", "0");
//        request.put("password", "1234");


        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body(request);
        httpRequest.header("Content-Type","application/json");
        Response response = httpRequest.post("/user");

        System.out.println(response.getStatusCode());
        JsonPath x = response.jsonPath();
        System.out.println(x.getString("message"));
        UsersResponse validateResponse = response.as(UsersResponse.class);

        Assert.assertEquals(validateResponse.getMessage(),String.valueOf(request.getId()),"id mismatch");
        System.out.println("all ok");
    }
}
