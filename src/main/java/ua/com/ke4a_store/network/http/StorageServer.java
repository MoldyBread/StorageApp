package ua.com.ke4a_store.network.http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StorageServer {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8089), 0);
        LoginHandler loginHandler = new LoginHandler();
                                                                                                                                            //loginHandler.createConnection("jdbc:mysql://localhost/storagedb?serverTimezone=UTC",USER_NAME,PASSWORD);
        HttpContext goodContext = server.createContext("/goods", new StorageHandler());
        goodContext.setAuthenticator(new Auth());

        server.setExecutor(executor);
                                                                                                                                                                        server.setExecutor(null);
        server.start();
        System.out.println("Storage server has been started");

        try {
            executor.submit((Runnable) server::getExecutor);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        finally {
            executor.shutdown();
        }
    }
}