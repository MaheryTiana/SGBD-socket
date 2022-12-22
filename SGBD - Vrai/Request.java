package affichage;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listeners.Listener;

public class Request extends JPanel{

    JTextField value;


    public Request(Listener listener){
        this.setBounds(0, 0, 600, 400);
        this.setLayout(null);

        JLabel title = new JLabel("Welcome in my SGBD!");
        title.setBounds(100,10 , 300, 30);
        title.setFont(new Font("Monospaced", Font.BOLD, 25));
        
        JLabel head = new JLabel("Write your request");
        head.setBounds(100,75 , 300, 30);
        head.setFont(new Font("Monospaced", Font.ITALIC, 15));
        
        value = new JTextField();
        value.setBounds(100,150 , 300, 75);
        
        JButton submit = new JButton("Submit");
        submit.setBounds(100,230 , 80, 50);
        submit.addActionListener(listener);
        this.add(head);
        this.add(title);
        this.add(value);
        this.add(submit);
    }
    public JTextField getValue() {
        return value;
    }

    public void setValue(JTextField value) {
        this.value = value;
    }
}
