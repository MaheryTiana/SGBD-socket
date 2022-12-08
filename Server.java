package base;
import java.io.*;
import java.net.*;
import java.util.Vector;
public class Server {
    ServerSocket serverSocket ;
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Server(int port)
    {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void startServer()throws Exception
    {

        new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    System.out.println("server : run");
                    try {
                        while(serverSocket.isClosed()==false){
                            Socket s = serverSocket.accept();
                            System.out.println("New guy connected");
                        }
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
            }
            ).start();
        
    }


    public void closeServer() throws Exception
    {
        try 
        {
            if (serverSocket != null) 
            {
                serverSocket.close();
            }
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }

    

}
