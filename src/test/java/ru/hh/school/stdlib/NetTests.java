package ru.hh.school.stdlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import org.junit.Assert;
import org.junit.Test;

public class NetTests extends BaseFunctionalTest {
  @Test
  public void simpleGet() throws IOException {
    Socket s = connect();

    Writer out = new PrintWriter(s.getOutputStream());
    out.append("GET k1\n").flush();
    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    
    Assert.assertEquals("VALUE", in.readLine());
    Assert.assertEquals("", in.readLine());
    
    s.close();
  }

    @Test
    public void netTest1() throws IOException {
        Socket s = connect();

        Writer out = new PrintWriter(s.getOutputStream());
        out.append("PUT k2 v1\n").flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        Assert.assertEquals("OK", in.readLine());
        s.close();

        s = connect();

        out = new PrintWriter(s.getOutputStream());
        out.append("GET k2\n").flush();
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        Assert.assertEquals("VALUE", in.readLine());
        Assert.assertEquals("v1", in.readLine());
        s.close();
    }

    @Test
    public void netTest2() throws IOException {
        Socket s = connect();

        Writer out = new PrintWriter(s.getOutputStream());
        out.append("PUT k2 v1\n").flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        Assert.assertEquals("OK", in.readLine());
        s.close();

        s = connect();

        out = new PrintWriter(s.getOutputStream());
        out.append("PUT k3 ${k2}\n").flush();
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        Assert.assertEquals("OK", in.readLine());
        s.close();

        s = connect();

        out = new PrintWriter(s.getOutputStream());
        out.append("GET k3\n").flush();
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        Assert.assertEquals("VALUE", in.readLine());
        Assert.assertEquals("v1", in.readLine());
        s.close();
    }

    @Test
    public void netTest3() throws IOException {
        Socket s = connect();
        Writer out = new PrintWriter(s.getOutputStream());
        out.append("PUT k2 v1\n").flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Assert.assertEquals("OK", in.readLine());
        s.close();

        s = connect();
        out = new PrintWriter(s.getOutputStream());
        out.append("PUT k3 ${k2}${k4}\n").flush();
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Assert.assertEquals("OK", in.readLine());
        s.close();

        s = connect();

        out = new PrintWriter(s.getOutputStream());
        out.append("GET k3\n").flush();
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        Assert.assertEquals("VALUE", in.readLine());
        Assert.assertEquals("v1", in.readLine());
        s.close();
    }
}
