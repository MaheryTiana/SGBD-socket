package affichage;
import java.awt.Font;
import javax.swing.*;
import javax.swing.JTextField;

import listeners.Listener;

public class ConfigClient extends JPanel{
    JTextField port;
    JTextField IP;
    JTextField database;


    public ConfigClient(Listener listener){
        this.setBounds(0, 0, 600, 400);
        this.setLayout(null);
        JLabel title = new JLabel("Client conguration");
        title.setBounds(100,25 , 300, 50);
        title.setFont(new Font("Monospaced", Font.BOLD, 25));

        JLabel portLabel = new JLabel("Port");
        portLabel.setBounds(100, 100, 50, 40);
        portLabel.setFont(new Font("Monospaced", Font.ITALIC, 15));

        this.port = new JTextField();
        port.setBounds(150, 100, 50, 40);

        JLabel IPLabel = new JLabel("IP");
        IPLabel.setBounds(100, 150, 75, 40);
        IPLabel.setFont(new Font("Monospaced", Font.ITALIC, 15));

        this.IP = new JTextField();
        IP.setBounds(150, 150, 75, 40);

        
        JLabel databaseLabel = new JLabel("database");
        databaseLabel.setBounds(70, 210, 75, 40);
        databaseLabel.setFont(new Font("Monospaced", Font.ITALIC, 15));

        this.database = new JTextField();
        database.setBounds(150, 210, 75, 40);


        JButton submit = new JButton("Login");
        submit.setBounds(200, 275, 75, 40);
        submit.addActionListener(listener);
        this.add(databaseLabel);
        this.add(database);
        this.add(submit);
        this.add(title);
        this.add(port);
        this.add(portLabel);
        this.add(IP);
        this.add(IPLabel);
    }
    public JTextField getDatabase() {
        return database;
    }
    public void setDatabase(JTextField database) {
        this.database = database;
    }
    public JTextField getPort() {
        return port;
    }
    public void setPort(JTextField port) {
        this.port = port;
    }
    public JTextField getIP() {
        return IP;
    }
    public void setIP(JTextField iP) {
        IP = iP;
    }
}

