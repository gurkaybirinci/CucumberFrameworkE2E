package techproed.stepDefinition.db_step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import techproed.pojos.RoomPojo;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static techproed.stepDefinition.ui_step_defs.MedunnaRoomStepDefs.odaNo;

public class DataBaseRoomStepDefs {
    Connection connection;
    Statement statement;

    @Given("Database baglantisi kur")
    public void database_baglantisi_kur() throws SQLException {
        // 1.Adım: Conncetion oluştur
        connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");

        // 2.Adım: Statement oluştur
        statement = connection.createStatement();
    }

    @Then("Oda bilgilerini dogrula")
    public void oda_bilgilerini_dogrula() throws SQLException {
        // 3.Adım: Query (sorgu) çalıştır
        ResultSet resultSet = statement.executeQuery("SELECT * FROM room WHERE room_number = " + odaNo);
        resultSet.next();

        RoomPojo expectedData = new RoomPojo(odaNo, "SUITE", true, 123, "End To End Test için oluşturulmuştur");

        assertEquals(expectedData.getRoomNumber(), resultSet.getInt("room_number"));
        assertEquals(expectedData.getRoomType(), resultSet.getString("room_type"));
        assertEquals(expectedData.isStatus(), resultSet.getBoolean("status"));
        assertEquals(expectedData.getPrice(), resultSet.getInt("price"));
        assertEquals(expectedData.getDescription(), resultSet.getString("description"));
    }
}













