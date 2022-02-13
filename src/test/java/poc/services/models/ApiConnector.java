package poc.services.models;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class ApiConnector {

    public static final String BASE_URL = "http://qainterview.pythonanywhere.com";
    public static final String ENDPOINT = "/factorial";


    public RequestSpecification requestSpecification(String number) {
        return new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded;charset=UTF-8")
                .setBody("number="+number)
                .setBaseUri(BASE_URL)
                .build();
    }

    public Response calculateFactorial(RequestSpecification requestSpecification) {
        return SerenityRest.given()
                .spec(requestSpecification)
                .when()
                .post(ENDPOINT)
                .thenReturn();
    }

}
