package affichage;
import java.awt.Font;
import javax.swing.*;
import javax.swing.JTextField;

import listeners.Listener;

public class ConfigServer extends JPanel{
    JTextField port;

    public ConfigServer(Listener listener){
        this.setBounds(0, 0, 600, 400);
        this.setLayout(null);
        JLabel title = new JLabel("Server setting");
        title.setBounds(100,50 , 300, 75);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));


        JLabel portLabel = new JLabel("Port");
        portLabel.setBounds(100, 150, 50, 40);
        portLabel.setFont(new Font("Monospaced", Font.ITALIC, 15));

        this.port = new JTextField();
        port.setBounds(150, 150, 50, 40);

        JButton submit = new JButton("Start");
        submit.setBounds(150, 210, 75, 40);
        submit.addActionListener(listener);
        this.add(submit);
        this.add(title);
        this.add(port);
        this.add(portLabel);
    }
    public JTextField getPort() {
        return port;
    }
    public void setPort(JTextField port) {
        this.port = port;
    }
}
