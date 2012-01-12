package ru.hh.school.stdlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class Server {
    private ServerSocket server;
    private final Substitutor3000 substitutor;
    private AtomicInteger sleepTime;
    public Server(InetSocketAddress addr) {
        try {
            server = new ServerSocket(addr.getPort(),0,addr.getAddress());
            System.out.println("Server created");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        substitutor = new Substitutor3000();
        this.sleepTime.set(0);
    }

    public Server(InetSocketAddress addr, Integer sleepTime) {
        try {
            server = new ServerSocket(addr.getPort(),0,addr.getAddress());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        substitutor = new Substitutor3000();
        this.sleepTime.set(sleepTime);
    }

    public void run() throws IOException {
        Executor executor = newCachedThreadPool();
        while (true) {
            final Socket clientSocket = server.accept();
            Runnable client = new Runnable() {
                @Override
                public void run() {
                    BufferedReader inp = null;
                    OutputStream outp = null;
                    String[] command = {"NONE"};
                    try {
                        inp = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        outp = clientSocket.getOutputStream();
                        command = inp.readLine().split(" ",3);
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    try {
                        Thread.sleep(sleepTime.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    String result = "Not Correct command\n";
                    synchronized (substitutor) {
                        if (command[0].equals("PUT")){
                            if (command.length == 3){
                                substitutor.put(command[1],command[2]);
                                result = "OK\n";
                            }
                        }
                        if (command[0].equals("GET")){
                            if (command.length == 2)
                                result = "VALUE\n"+substitutor.get(command[1])+"\n";
                        }
                        if (command[0].equals("SET")){
                            if (command[1].equals("SLEEP") && command.length == 3){
                                sleepTime.set(new Integer(command[2]));
                                System.out.println("Sleep Time setted to"+command[2]);
                                result = "Ok\n";
                            }
                        }
                    }
                    try {
                        if (outp != null) {
                            outp.write(result.getBytes());
                            outp.flush();
                        }
                        if (inp != null) {
                            inp.close();
                        }
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            };
            executor.execute(client);
        }
    }

    public int getPort() {
        return server.getLocalPort();
    }
}
