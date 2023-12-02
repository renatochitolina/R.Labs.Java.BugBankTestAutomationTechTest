package validations;

import org.junit.jupiter.api.Assertions;

import framework.browser.DriverManager;
import framework.utils.simplereportbuilder.ReportBuilder;
import framework.utils.simplereportbuilder.TipoRegistro;

import pageobjects.LoginPage;

public class LoginValidation {
    private final LoginPage loginPage;

    public LoginValidation() {
        this.loginPage = new LoginPage();
    }

    public void isLoginBemSucedido() {
        String mensagemEsperada = "Usuário conectado com sucesso";
        String mensagemObtida = this.loginPage.getModalFeedback().getText();

        Assertions.assertEquals(mensagemEsperada, mensagemObtida);

        ReportBuilder.addRegistro(
                TipoRegistro.SUCESSO,
                "Login realizado com sucesso",
                DriverManager.obterScreenShot());
    }

    public void isLoginMalSucedido() {
        String mensagemEsperada = "Usuário não encontrado";

        String mensagemObtida = this.loginPage.getModalFeedback().getText();

        Assertions.assertEquals(mensagemEsperada, mensagemObtida);

        ReportBuilder.addRegistro(
                TipoRegistro.SUCESSO,
                "Login com credenciais inválidas impedido com sucesso",
                DriverManager.obterScreenShot());
    }
}
