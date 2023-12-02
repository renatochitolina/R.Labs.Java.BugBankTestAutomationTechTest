package framework.utils.simplereportbuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import framework.utils.FileOperations;

public class ReportBuilder {
    private static final String DiretorioTemplate = FileOperations.DiretorioResourcesAplicacao
            + File.separator + "simplereportbuilder"
            + File.separator + "template.html";
    private static final String DiretorioRelatorios = FileOperations.DiretorioResourcesTestes
            + File.separator + "relatorios";
    private static final String PlaceholderTitulo = "{title}";
    private static final String PlaceholderConteudo = "{report}";

    public static Report relatorio;

    public static void criar(String nome, String descricao) {
        ReportBuilder.relatorio = new Report(nome, descricao);
    }

    public static void addStep(String descricao) {
        ReportBuilder.relatorio.addStep(descricao);
    }

    public static void addRegistro(TipoRegistro tipo, String descricao) {
        ReportBuilder.relatorio.getSteps().getLast().addRegistro(tipo, descricao);
    }

    public static void addRegistro(TipoRegistro tipo, String descricao, byte[] arquivo) {
        ReportBuilder.relatorio.getSteps().getLast().addRegistro(tipo, descricao, arquivo);
    }

    public static void concluir() {
        String diretorioRelatorio = ReportBuilder.DiretorioRelatorios
                + File.separator + ReportBuilder.relatorio.getNome();

        FileOperations.criarPasta(ReportBuilder.DiretorioRelatorios);

        FileOperations.criarPasta(diretorioRelatorio);

        String templateHtml = lerTemplateHtml(ReportBuilder.DiretorioTemplate);

        if (templateHtml == null)
            return;

        StringBuilder conteudoRelatorioHtml = new StringBuilder();

        conteudoRelatorioHtml
                .append(String.format("<h1>%s</h1>%n", ReportBuilder.relatorio.getNome()))
                .append(String.format("<p>%s</p>%n%n", ReportBuilder.relatorio.getDescricao()));

        for (Step step : relatorio.getSteps()) {
            conteudoRelatorioHtml
                    .append(String.format("<div class=\"step\">%n"))
                    .append(String.format("<h2>%s</h2>%n", step.getDescricao()));

            for (Registro registro : step.getRegistros()) {
                conteudoRelatorioHtml
                        .append(String.format("<div class=\"record\">%n"));

                String tipoRegistroClasse = registro.getTipo() == TipoRegistro.SUCESSO
                        ? "success"
                        : "fail";

                String tipoRegistroCapitalizado = registro.getTipo().toString().substring(0, 1)
                        .concat(registro.getTipo().toString().substring(1).toLowerCase());

                conteudoRelatorioHtml
                        .append(String.format("<div class=\"record-details\">%n"))
                        .append(String.format("<p>%s</p>%n", registro.getDescricao()))
                        .append(String.format("<span class='status label %s'>%s</span>%n",
                                tipoRegistroClasse,
                                tipoRegistroCapitalizado))
                        .append(String.format("</div>%n"));

                if (registro.getArquivo() != null) {
                    String diretorioImagem = processarImagemRegistroRelatorioHtml(diretorioRelatorio, registro);

                    conteudoRelatorioHtml
                            .append(String.format("<a href=\"%s\" target=\"_blank\">%n", diretorioImagem))
                            .append(String.format("<img src=\"%s\" alt=\"%s\">%n",
                                    diretorioImagem,
                                    String.format("Evidência de %s", registro.getTipo().toString().toLowerCase())))
                            .append(String.format("</a>%n"));
                }

                conteudoRelatorioHtml.append(String.format("</div>%n"));
            }

            conteudoRelatorioHtml.append(String.format("</div>%n%n"));
        }

        StringBuilder relatorioHtml = new StringBuilder(templateHtml);

        ReportBuilder.interpolarRelatorioHtml(
                relatorioHtml,
                ReportBuilder.PlaceholderTitulo,
                ReportBuilder.relatorio.getNome());

        ReportBuilder.interpolarRelatorioHtml(
                relatorioHtml,
                ReportBuilder.PlaceholderConteudo,
                conteudoRelatorioHtml.toString());

        ReportBuilder.gravarRelatorioHtml(
                diretorioRelatorio + File.separator + "Relatório.html",
                relatorioHtml);

        relatorio = null;
    }

    private static String lerTemplateHtml(String templatePath) {
        String templateHtml = null;

        try {
            byte[] conteudo = Files.readAllBytes(Paths.get(templatePath));

            templateHtml = new String(conteudo, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.printf(
                    "Ocorreu uma falha durante a leitura do arquivo de template para geração do relatório %s%n",
                    ReportBuilder.relatorio.getNome());

            e.printStackTrace();
        }

        return templateHtml;
    }

    private static void interpolarRelatorioHtml(StringBuilder relatorioHtml, String placeholder, String conteudo) {
        int placeholderIndex = relatorioHtml.indexOf(placeholder);

        if (placeholderIndex != -1) {
            relatorioHtml.replace(
                    placeholderIndex,
                    placeholderIndex + placeholder.length(),
                    conteudo);
        }
    }

    private static String processarImagemRegistroRelatorioHtml(String diretorioRelatorio, Registro registro) {
        String nomeArquivo = UUID.randomUUID().toString().substring(0, 8) + ".jpg";

        String diretorioImagem = diretorioRelatorio
                + File.separator + nomeArquivo;

        try (FileOutputStream stream = new FileOutputStream(diretorioImagem)) {
            stream.write(registro.getArquivo());
        } catch (IOException e) {
            System.out.printf(
                    "Ocorreu uma falha durante a criação da imagem %n",
                    ReportBuilder.relatorio.getNome());

            e.printStackTrace();
        }

        return nomeArquivo;
    }

    private static void gravarRelatorioHtml(String diretorioRelatorio, StringBuilder relatorioHtml) {
        try (FileWriter fileWriter = new FileWriter(diretorioRelatorio)) {
            fileWriter.write(relatorioHtml.toString());
        } catch (IOException e) {
            System.out.printf(
                    "Ocorreu uma falha durante a geração do relatório %s%n",
                    ReportBuilder.relatorio.getNome());

            e.printStackTrace();
        }
    }
}
