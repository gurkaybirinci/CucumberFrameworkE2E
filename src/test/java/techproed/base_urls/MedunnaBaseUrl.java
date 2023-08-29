package techproed.base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static techproed.utilities.Authentication.generateToken;

public class MedunnaBaseUrl {
    public static RequestSpecification spec;
    public static void setup(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://medunna.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + generateToken())
                .build();
    }
}
