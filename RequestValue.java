package affichage;
import java.awt.Color;
import java.text.BreakIterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.*;
import listeners.Listener;
import base.*;
public class RequestValue extends JFrame{
    public RequestValue(Client client , String query)throws Exception
    {
        String[] words = query.split(" ");
        Vector<String> col = new Vector<String>();
        Vector<String> ans = new Vector<String>();

        try {
            col = client.getJdbc().getHeader(query);

            ans = client.executeQuery(query);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
        this.setSize(600, 400);
        this.setTitle("Request's answers");
        
        String[][] data ;
        
        // Column Names
        String[] columnNames;
        
        System.out.println("tonga ilay requete");
        if (words[1].equals("*")) {
            columnNames = client.getJdbc().getTableHeader(client.getJdbc().getDBused(), words[3]);
            System.out.println("mandalo");



            data = new String[ans.size()/columnNames.length][];
            int ind = 0;
            for (int i = 0; i < data.length; i++) {
    
                data[i] = new String[columnNames.length];
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = ans.get(ind);
                    ind++;
                }
            }


        }else{
            columnNames = new String[col.size()];
            
            for (int i = 0; i < columnNames.length; i++) {
                columnNames[i] = col.get(i);
            }
            data = new String[ans.size()/col.size()][];
            int ind = 0;
            for (int i = 0; i < data.length; i++) {
    
                data[i] = new String[col.size()];
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = ans.get(ind);
                    ind++;
                }
            }
        }
        
        
        // Data to be displayed in the JTable




        for (int index = 0; index < columnNames.length; index++) {
            System.out.println("col: "+columnNames[index]);
        }

        JTable table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);
        

    
        JScrollPane sp = new JScrollPane(table);
        this.getContentPane().add(sp);
        System.out.println("taillee eto  " +columnNames.length);

        this.setVisible(true);
    }
}
