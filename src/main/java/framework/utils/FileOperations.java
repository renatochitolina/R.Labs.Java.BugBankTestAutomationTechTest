package framework.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileOperations {
    private static final String DiretorioBaseAplicacao = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "main";

    private static final String DiretorioBaseTestes = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "test";

    public static final String DiretorioResourcesAplicacao = FileOperations.DiretorioBaseAplicacao
            + File.separator + "resources";

    public static final String DiretorioResourcesTestes = FileOperations.DiretorioBaseTestes
            + File.separator + "resources";

    private static Properties getProperties(String diretorioResources, String nomeArquivo) {
        Properties properties = new Properties();

        String diretorioArquivoProperties = diretorioResources
                + File.separator + "properties"
                + File.separator + String.format("%s.properties", nomeArquivo);

        try (FileReader leitor = new FileReader(diretorioArquivoProperties)) {
            properties.load(leitor);
        } catch (IOException e) {
            System.out.printf("Ocorreu uma falha durante a leitura do arquivo de propriedades %s%n", nomeArquivo);

            e.printStackTrace();
        }

        return properties;
    }

    public static Properties getPropertiesAplicacao(String nomeArquivo) {
        return getProperties(FileOperations.DiretorioResourcesAplicacao, nomeArquivo);
    }

    public static Properties getPropertiesTestes(String nomeArquivo) {
        return getProperties(FileOperations.DiretorioResourcesTestes, nomeArquivo);
    }

    public static void criarPasta(String nomeDiretorio) {
        File diretorio = new File(nomeDiretorio);

        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
    }
}
