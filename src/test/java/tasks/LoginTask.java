package tasks;

import framework.utils.simplereportbuilder.ReportBuilder;
import pageobjects.LoginPage;
import validations.LoginValidation;

public class LoginTask {
    private final LoginPage loginPage;
    private final LoginValidation loginValidation;

    public LoginTask() {
        this.loginPage = new LoginPage();
        this.loginValidation = new LoginValidation();
    }

    public void efetuarLoginBemSucedido(String usuario, String senha) {
        ReportBuilder.addStep("Executando tentativa de acesso com credenciais válidas");

        this.efetuarLogin(usuario, senha);

        this.loginValidation.isLoginBemSucedido();

        this.loginPage.getModalFeedbackButtonFechar().click();
    }

    public void impedirLoginCredenciaisInvalidas(String usuario, String senha) {
        ReportBuilder.addStep("Executando tentativa de acesso com credenciais inválidas");

        this.efetuarLogin(usuario, senha);

        this.loginValidation.isLoginMalSucedido();

        this.loginPage.getModalFeedbackButtonFechar().click();
    }

    private void efetuarLogin(String usuario, String senha) {
        this.loginPage.getInputUsuario().sendKeys(usuario);

        this.loginPage.getInputSenha().sendKeys(senha);

        this.loginPage.getButtonAcessar().click();
    }
}
