package affichage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.Listener;

import javax.swing.JButton;
import java.awt.Font;

public class Choix extends JPanel{
    Choix(Listener listener){
        this.setBounds(0, 0, 600, 400);
        this.setLayout(null);
        
        JLabel title = new JLabel("What do you want to be ?");
        title.setBounds(100,50 , 300, 75);
        title.setFont(new Font("Monospaced", Font.BOLD, 20));
        JButton server = new JButton("Server");
        server.setBounds(100,200,75,50);
        server.addActionListener(listener);


        JButton client = new JButton("Client");
        client.setBounds(250,200,75,50);
        client.addActionListener(listener);

        this.add(title);
        this.add(server);
        this.add(client);
    }
}
