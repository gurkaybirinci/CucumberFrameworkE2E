package techproed.stepDefinition.ui_step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;

public class AmazonStepDefinition {
    @Given("kullanici amazon sayfasina gider")
    public void kullanici_amazon_sayfasina_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Then("kullanici aramakutusunda iphone aratir")
    public void kullanici_aramakutusunda_iphone_aratir() {
        Driver.getDriver().findElement(By.id("twotabsearchtextbox")).sendKeys("iphone", Keys.ENTER);

    }
    @Then("sayfayi kapatir")
    public void sayfayi_kapatir() {
        Driver.closeDriver();
    }
}
