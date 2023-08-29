package techproed.stepDefinition.api_step_defs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import techproed.pojos.RoomPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static techproed.base_urls.MedunnaBaseUrl.spec;
import static techproed.stepDefinition.ui_step_defs.MedunnaRoomStepDefs.odaNo;

public class ApiRoomStepDefs {
    Response response;
    RoomPojo expectedData;

    @Given("GET request gonderilir")
    public void get_request_gonderilir() {
        // Set the URL --> https://medunna.com/api/rooms?sort=createdDate,desc
        spec.pathParams("first", "api", "second", "rooms")
                .queryParam("sort", "createdDate,desc");

        // Set the ecpected data

        // Send the request and get the response
        response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

    }

    @When("Body dogrulanir")
    public void body_dogrulanir() {
        Object actualRoomType = response.jsonPath().getList("findAll{it.roomNumber==" + odaNo + "}.roomType").get(0);
        Object actualStatus = response.jsonPath().getList("findAll{it.roomNumber==" + odaNo + "}.status").get(0);
        Object actualPrice = response.jsonPath().getList("findAll{it.roomNumber==" + odaNo + "}.price").get(0);
        Object actualDescription = response.jsonPath().getList("findAll{it.roomNumber==" + odaNo + "}.description").get(0);
        Object actualRoomNumber = response.jsonPath().getList("findAll{it.roomNumber==" + odaNo + "}.roomNumber").get(0);

        assertEquals("SUITE", actualRoomType);
        assertEquals(true, actualStatus);
        assertEquals(123.00f, actualPrice);
        assertEquals("End To End Test için oluşturulmuştur", actualDescription);
        assertEquals(odaNo, actualRoomNumber);
    }

    @Given("ID ile GET request gonderilir")
    public void id_ile_get_request_gonderilir() {
        // Oda ID'sini alma --> https://medunna.com/api/rooms?sort=createdDate%2Cdesc
        spec.pathParams("first", "api", "second", "rooms").queryParam("sort", "createdDate,desc");
        response = given(spec).when().get("{first}/{second}");
        Object odaId = response.jsonPath().getList("findAll{it.roomNumber==" + odaNo + "}.id").get(0);

        // Set the URL
        spec.pathParams("first", "api", "second", "rooms", "third", odaId);

        // Set the expected data
        expectedData = new RoomPojo(odaNo, "SUITE", true, 123.00, "End To End Test için oluşturulmuştur");
        System.out.println(expectedData);

        // Send the request and get the response
        response = given(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();
    }

    @When("Response body dogrulanir")
    public void response_body_dogrulanir() throws JsonProcessingException {
        RoomPojo actualData = new ObjectMapper().readValue(response.asString(), RoomPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getRoomNumber(), actualData.getRoomNumber());
        assertEquals(expectedData.getRoomType(), actualData.getRoomType());
        assertEquals(expectedData.isStatus(), actualData.isStatus());
        assertEquals(expectedData.getPrice(), actualData.getPrice());
        assertEquals(expectedData.getDescription(), actualData.getDescription());

    }

}











