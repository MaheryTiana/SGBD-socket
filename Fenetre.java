package affichage;
import javax.swing.*;

import affichage.ConfigServer;
import affichage.Request;
import base.Client;
import listeners.Listener;
public class Fenetre extends JFrame{
    Choix choix;
    Listener listener;
    ConfigServer configServer;
    ConfigClient configClient;
    Request request;
    boolean serverStart;
    RequestValue requestValue;
    Client client;





    Fenetre()
    {
        this.setSize(600, 400);
        this.setLayout(null);
        this.setTitle("MyOwnJDBC");
        this.listener = new Listener(this);
//choice
        this.choix = new Choix(listener);
        this.add(this.choix);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }


    public RequestValue getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(RequestValue requestValue) {
        this.requestValue = requestValue;
    }
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    public ConfigClient getConfigClient() {
        return configClient;
    }


    public void setConfigClient(ConfigClient configClient) {
        this.configClient = configClient;
    }
    public boolean isServerStart() {
        return serverStart;
    }

    public void setServerStart(boolean serverStart) {
        this.serverStart = serverStart;
    }

    public Choix getChoix() {
        return choix;
    }
    public void setChoix(Choix choix) {
        this.choix = choix;
    }
    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public ConfigServer getConfigServer() {
        return configServer;
    }

    public void setConfigServer(ConfigServer configServer) {
        this.configServer = configServer;
    }

}
