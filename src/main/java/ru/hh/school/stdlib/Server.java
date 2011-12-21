package ru.hh.school.stdlib;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Executors.;

public class Server {
    private ServerSocket server;
    private Substitutor3000 substitutor;
    private Integer sleepTime;
    public Server(InetSocketAddress addr) {
        try {
            server = new ServerSocket(addr.getPort(),0,addr.getAddress());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        substitutor = new Substitutor3000();
        this.sleepTime = 0;
    }

    public Server(InetSocketAddress addr, Integer sleepTime) {
        try {
            server = new ServerSocket(addr.getPort(),0,addr.getAddress());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        substitutor = new Substitutor3000();
        this.sleepTime = sleepTime;
    }

    public void run() throws IOException {
        Executor executor = new Executors.;
        for (;;){
            Socket clientSocket = server.accept();
            Runnable client = new Runnable() {
                @Override
                public void run() {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
            };

        }
    }

    public int getPort() {
        return server.getLocalPort();
    }
}
