package base;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

import jdbc.MyOwnJDBC;


import jdbc.MyOwnJDBC;
public class Client {
    Socket clientSocket;
    BufferedWriter writer;
    MyOwnJDBC jdbc;
    public MyOwnJDBC getJdbc(){
        return this.jdbc;
    }
    public void setJdbc(MyOwnJDBC j){
        this.jdbc = j;
    }

    public Client(String IP, int port, MyOwnJDBC jdbc)throws Exception
    {
        try {
            clientSocket = new Socket(IP,port);
            this.setJdbc(jdbc);
        } catch (Exception e) {
            //TODO: handle exception
            throw e;
        }
    }

    public Vector<String> executeQuery( String query) throws Exception
    {
        Vector<String> ans = new Vector<String>();
        try {
            if(getClientSocket().isConnected()==true) 
            {
                ans = jdbc.execute(query);
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
        return ans;
    }
    

    public BufferedWriter getWriter() {
        return writer;
    }
    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }
    public Socket getClientSocket() {
        return clientSocket;
    }
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
