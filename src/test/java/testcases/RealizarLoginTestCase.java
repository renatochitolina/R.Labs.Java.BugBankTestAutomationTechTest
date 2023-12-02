package testcases;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import framework.browser.DriverManager;
import framework.TestBase;
import framework.utils.FileOperations;
import framework.utils.simplereportbuilder.ReportBuilder;
import framework.utils.simplereportbuilder.TipoRegistro;

import tasks.LoginTask;

public class RealizarLoginTestCase extends TestBase {
    private final LoginTask loginTask;

    public RealizarLoginTestCase() {
        this.loginTask = new LoginTask();
    }

    @Test
    public void realizarLoginBemSucedido() {
        try {
            ReportBuilder.criar("Login-sucesso", "Realizar login bem sucedido com credenciais válidas");

            Properties contasProps = FileOperations.getPropertiesTestes("contas_config");
            String usuario = contasProps.getProperty("conta1_usuario");
            String senha = contasProps.getProperty("conta1_senha");

            this.loginTask.efetuarLoginBemSucedido(usuario, senha);
        } catch (AssertionFailedError e) {
            ReportBuilder.addRegistro(
                    TipoRegistro.FALHA,
                    String.format("Login falhou: %s", e.getMessage()),
                    DriverManager.obterScreenShot());

            throw e;
        }
    }

    @Test
    public void realizarLoginMalSucedido() {
        try {
            ReportBuilder.criar("Login-insucesso", "Impedir realização de login com credenciais inválidas");

            Properties contasProps = FileOperations.getPropertiesTestes("contas_config");
            String usuario = contasProps.getProperty("conta3_usuario");
            String senha = contasProps.getProperty("conta3_senha");

            this.loginTask.impedirLoginCredenciaisInvalidas(usuario, senha);
        } catch (AssertionFailedError e) {
            ReportBuilder.addRegistro(
                    TipoRegistro.FALHA,
                    String.format("Impedimento de login falhou: %s", e.getMessage()),
                    DriverManager.obterScreenShot());

            throw e;
        }
    }
}
