package app;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import framework.utils.FileOperations;

public class WebServerHandler implements HttpHandler {
    private static final String DiretorioResourcesApp = FileOperations.DiretorioResourcesAplicacao
            + File.separator + "app";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ArrayList<String> recursos = obterRecursos(exchange.getRequestURI().getPath());

        exchange.getResponseHeaders()
                .set("Content-Type", String.format("%s; charset=UTF-8", recursos.get(1)));

        byte[] conteudo = Files.readAllBytes(Paths.get(recursos.get(0)));

        exchange.sendResponseHeaders(200, conteudo.length);

        try (OutputStream stream = exchange.getResponseBody()) {
            stream.write(conteudo);

            stream.flush();
        }
    }

    private ArrayList<String> obterRecursos(String path) {
        String diretorioRecursos = DiretorioResourcesApp + File.separator;

        ArrayList<String> recursos = new ArrayList<>();

        if (path.equals("/")) {
            recursos.add(diretorioRecursos.concat("index.html"));
            recursos.add("text/html");

            return recursos;
        }

        recursos.add(diretorioRecursos.concat(path.replace("/", "")));

        switch (path.substring(path.lastIndexOf(".") + 1)) {
            case "html":
                recursos.add("text/html");
                break;
            case "js":
                recursos.add("application/javascript");
                break;
            case "css":
                recursos.add("text/css");
                break;
            case "ico":
                recursos.add("image/x-icon");
                break;
            default:
                recursos.add("text/html");
        }

        return recursos;
    }
}
