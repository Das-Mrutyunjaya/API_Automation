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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class login_test {
    public static String baseurl = "https://petstore.swagger.io/v2";


    public void test() {
        Users request = new Users();

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
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.post("/user");

        System.out.println(response.getStatusCode());
        JsonPath x = response.jsonPath();
        System.out.println(x.getString("message"));
        UsersResponse validateResponse = response.as(UsersResponse.class);

        Assert.assertEquals(validateResponse.getMessage(), String.valueOf(request.getId()), "id mismatch");
        System.out.println("all ok");
    }

    @Test
    public void test2() {
        Response response = RestAssured.get("https://run.mocky.io/v3/9eb34956-8f80-42b8-b90c-4a18feaffe6c");
        if (response instanceof Map) {
            Map Responseaslist = response.as(Map.class);
            System.out.println(Responseaslist.size());
            System.out.println(Responseaslist);
            if (Responseaslist.get("status").equals("success")) {
                ArrayList<LinkedHashMap<Object, Object>> x = (ArrayList<LinkedHashMap<Object, Object>>) Responseaslist.get("gear");
                Assert.assertEquals(x.get(0).get("year"), "2005", "year miss matched");
                Assert.assertEquals(x.get(1).get("year"), "1983", "year miss matched");
            }
        } else if (response instanceof List) {
            List Responseaslist = response.as(List.class);
        }
    }

}