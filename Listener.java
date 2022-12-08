package listeners;
import base.*;
import jdbc.MyOwnJDBC;

import java.io.*;
import java.net.*;
import java.util.Vector;
import java.awt.*;  
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import affichage.*;
public class Listener implements ActionListener{
    Fenetre fen;
    public Listener(Fenetre f) {
        fen = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton checking = (JButton)e.getSource();

            try {
//server
            if (checking.getText().equalsIgnoreCase("server")) {
                System.out.println("server will be runned");
                fen.getChoix().setVisible(false);
                fen.setConfigServer(new ConfigServer(fen.getListener()));
                fen.add(fen.getConfigServer());
                
            }else if (checking.getText().equalsIgnoreCase("Start")) {
                fen.getConfigServer().setVisible(false);
                int port = Integer.valueOf(fen.getConfigServer().getPort().getText());
                System.out.println("port : "+fen.getConfigServer().getPort().getText());
                fen.getChoix().setVisible(true);
                Server server = new Server(port);
                try {
                    server.startServer();
                } catch (Exception ex) {
                    // TODO: handle exception
                }
    //client
            }else if (checking.getText().equalsIgnoreCase("client")) {

                fen.getChoix().setVisible(false);
                fen.setConfigClient(new ConfigClient(fen.getListener()));
                fen.add(fen.getConfigClient());

            }else if (checking.getText().equalsIgnoreCase("Login")) {

                String IP = fen.getConfigClient().getIP().getText();
                int port = Integer.valueOf(fen.getConfigClient().getPort().getText());
                String database = fen.getConfigClient().getDatabase().getText();
                MyOwnJDBC jdbc = new MyOwnJDBC(database);
                Client cl = new Client(IP, port, jdbc);
                fen.setClient(cl);
                System.out.println("Welcome");
                fen.getConfigClient().setVisible(false);
                fen.setRequest(new Request(fen.getListener()));
                fen.add(fen.getRequest());
    //request
            }else if (checking.getText().equalsIgnoreCase("Submit")) {
                String query = fen.getRequest().getValue().getText();

                String[] words = query.split(" ");
                if (words[0].equalsIgnoreCase("omeo") || words[0].equalsIgnoreCase("apifandraiso") || words[0].equalsIgnoreCase("diviseo")  || words[0].equalsIgnoreCase("apifanalao") ) {
                    
                    new RequestValue(fen.getClient(), query);
                }
                else{
                    fen.getClient().executeQuery(query);
                    JOptionPane.showMessageDialog(fen, words[0]+" done succefully!");
                }
            }
            
        } catch (Exception ex) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(fen, ex.getMessage());
        }

    }
    

}
