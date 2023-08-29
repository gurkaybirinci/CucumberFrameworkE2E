package techproed.stepDefinition.ui_step_defs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import techproed.pages.MedunnaHomePage;
import techproed.pages.MedunnaRoomPage;
import techproed.utilities.Driver;

public class MedunnaRoomStepDefs {
    MedunnaHomePage medunnaHomePage = new MedunnaHomePage();
    MedunnaRoomPage medunnaRoomPage = new MedunnaRoomPage();
    public static int odaNo;
    public static String odaId;

    @When("Items&Titles secenegine tiklanir")
    public void ıtems_titles_secenegine_tiklanir() {
        medunnaHomePage.itemsdAndTitles.click();
    }

    @When("Room secenegine tiklanir")
    public void room_secenegine_tiklanir() {
        medunnaHomePage.roomOption.click();
    }

    @When("Create a new Room butonuna tiklanir")
    public void create_a_new_room_butonuna_tiklanir() {
        medunnaRoomPage.createANewRoomButton.click();
    }

    @When("Room Number kutusuna bir oda_no girilir")
    public void room_number_kutusuna_bir_girilir() {
        odaNo = Faker.instance().number().numberBetween(100000, 1000000);
        medunnaRoomPage.roomNumberInput.sendKeys(odaNo + "");
    }

    @When("Room Type menusunden SUITE secenegi secilir")
    public void room_type_menusunden_suıte_secenegi_secilir() {
        new Select(medunnaRoomPage.roomTypeDropDown).selectByIndex(3);
    }

    @When("Status kontrol kutusuna tiklanir")
    public void status_kontrol_kutusuna_tiklanir() {
        medunnaRoomPage.statusCheckbox.click();
    }

    @When("Price kutusuna {string} degeri girilir")
    public void price_kutusuna_degeri_girilir(String fiyat) {
        medunnaRoomPage.priceInput.sendKeys(fiyat);
    }

    @When("Description kutusuna bir {string} girilir")
    public void description_kutusuna_bir_girilir(String aciklama) {
        medunnaRoomPage.descriptionInput.sendKeys(aciklama);
    }

    @When("Save butonuna tiklanir")
    public void save_butonuna_tiklanir() throws InterruptedException {
        medunnaRoomPage.saveSubmitButton.click();
        // 1.YOL
        Thread.sleep(1000);
        odaId = medunnaRoomPage.alert.getText().replaceAll("[^0-9]", "");

        // 2. YOL
        Thread.sleep(1000);
        medunnaRoomPage.createdDate.click();
        odaId = Driver.getDriver().findElement(By.xpath("//tr[td[text()='"+odaNo+"']]")).findElement(By.tagName("a")).getText();
        System.out.println(odaId);
    }

    @When("Uygulama kapatilir")
    public void uygulama_kapatilir() {
        Driver.closeDriver();
    }
}











