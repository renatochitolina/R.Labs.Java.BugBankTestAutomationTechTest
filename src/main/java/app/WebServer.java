package app;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import framework.utils.FileOperations;

public class WebServer {
    public static final int Porta = Integer.parseInt(
            FileOperations.getPropertiesAplicacao("app_config").getProperty("porta"));

    public static void main(String[] args) {
        try {

            HttpServer servidor = HttpServer.create(new InetSocketAddress(WebServer.Porta), 0);

            servidor.createContext("/", new WebServerHandler());

            servidor.setExecutor(null);

            System.out.printf("Servidor web inicializado na porta %d%n", WebServer.Porta);

            servidor.start();
        } catch (IOException e) {
            System.out.printf("Ocorreu uma falha no servidor web na porta %d%n", WebServer.Porta);

            e.printStackTrace();
        }
    }
}
