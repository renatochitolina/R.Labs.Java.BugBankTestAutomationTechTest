package pageobjects;

import framework.browser.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebElement getInputUsuario() {
        return DriverManager.obterDriver().findElement(By.id("usuario"));
    }

    public WebElement getInputSenha() {
        return DriverManager.obterDriver().findElement(By.id("senha"));
    }

    public WebElement getButtonAcessar() {
        return DriverManager.obterDriver().findElement(By.xpath("//form[@id='login-form']//button"));
    }

    public WebElement getModalFeedback() {
        return DriverManager.obterDriver().findElement(By.id("modal-text"));
    }

    public WebElement getModalFeedbackButtonFechar() {
        return DriverManager.obterDriver().findElement(By.xpath("//div[@id='modal']//button"));
    }
}
