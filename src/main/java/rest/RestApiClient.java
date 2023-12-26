package rest;

import static java.lang.String.format;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestApiClient {

    private final static  String baseUri = "https://jsonplaceholder.typicode.com/";
    private final static  String ENDPOINT = baseUri + "%s";

    private String getEndPoint(String uri) {
        return format(ENDPOINT, uri);
    }

    public Response getResponse(String endpoint) {
        return given().log().all()
            .spec(getRequestSpec())
            .get(getEndPoint(endpoint))
            .then().extract().response();
    }

    private RequestSpecification getRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setAccept(ContentType.JSON);
        builder.setBaseUri(baseUri);
        return builder.build();
    }
}
