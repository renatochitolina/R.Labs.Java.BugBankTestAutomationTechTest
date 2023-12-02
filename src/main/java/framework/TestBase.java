package framework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import framework.browser.DriverManager;
import framework.utils.FileOperations;
import framework.utils.simplereportbuilder.ReportBuilder;

public class TestBase {
    private static final String UrlWebsiteAlvo = FileOperations
            .getPropertiesTestes("website_config")
            .getProperty("url");

    @BeforeEach
    public void setup() {
        DriverManager.obterDriver().get(TestBase.UrlWebsiteAlvo);
    }

    @AfterEach
    public void finalizar() {
        DriverManager.finalizarDriver();

        ReportBuilder.concluir();
    }
}
