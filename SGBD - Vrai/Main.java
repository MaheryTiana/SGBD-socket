package affichage;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.Vector;

import affichage.Fenetre;
import base.Client;
import base.Server;
import jdbc.MyOwnJDBC;
import tools.Function;

public class Main {
    public static void main(String[] args) {
        try {
            Function fun = new Function();
            // Server sr = new Server();
            // sr.startServer();

            // Client cl = new Client();

        // String sql = "manamboara_datatase ex";
        // MyOwnJDBC own = new MyOwnJDBC("");
        // Vector<String> answer = own.execute(sql);
        // for (int i = 0; i < answer.size(); i++) {
        //     System.out.println("ans:  "+answer.get(i));
        // }
        new Fenetre();
        // new Request();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("error occured: "+e);
        }
    }    
}
