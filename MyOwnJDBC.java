package jdbc;
import java.util.Vector;

import tools.Function;

public class MyOwnJDBC extends Function {
    // Vector<String> grammar;

    String DBused;
    public String getDBused() {
        return DBused;
    }

    public void setDBused(String dBused) {
        DBused = dBused;
    }

    public MyOwnJDBC(String DB) {
        // Vector<String> gr = new Vector<String>();
        // gr.add("omeo");
        // gr.add("fafao_ny_DataBase");
        // gr.add("fafao_ny_Table");
        // gr.add("manamboara_DataBase");
        // gr.add("manamboara_Table");
        // gr.add("apio");
        // grammar = gr;
        this.setDBused(DB);
    }
    
    public Vector<String> getHeader(String sentence)throws Exception
    {
        Vector<String> ans = new Vector<String>();
        String[] words = splitSentence(sentence);
        String requete = words[0].toLowerCase();
        String table = getSelectedTable(sentence);


    //grammaire
        switch (requete) {
            // omeo nom ao dept
            case "omeo":
                Vector<String> selectedCol = getSelectedColumn(sentence);
            ans = selectedCol;
                break;

            case "apifandraiso":
        //apifandraiso emp sy dept raha mitovy ny idDept sy id ka omeo emp.nom,dept.localisation
                String tab1 = words[1];
                String tab2 = words[3];
                Vector<String> selCol1 = new Vector<String>();
                Vector<String> selCol2 = new Vector<String>();

                if (words[12].equals("*")) {
                    selCol1.add("*");
                   ans = select(this.DBused, tab1, selCol1);
                    Vector<String> sel2 = select(this.DBused, tab2, selCol1);
                    for (int i = 0; i < sel2.size(); i++) {
                        ans.add(sel2.get(i));
                    }
                }else{
                    String valueJoint = words[12];
        // System.out.println("val : "+valueJoint);
                    selCol1 = getSelectedColumnJoint(valueJoint, tab1);
                    selCol2 = getSelectedColumnJoint(valueJoint, tab2);

                    for (int i = 0; i < selCol1.size(); i++) {
        System.out.println("selCol : "+ selCol1.get(i));
                }                    
                    if (selCol1.size()>0) {
                        ans = select(this.DBused, tab1, selCol1);
                        ans = selCol1;
                    }
                    if (selCol2.size()>0) {
                        Vector<String> sel2 = select(this.DBused, tab2, selCol2);
                        for (int i = 0; i < sel2.size(); i++) {
                            ans.add(sel2.get(i));

                        }              
                    }  
                }
                
                break;

            default:
                break;
        }
        return ans;
    }
    public Vector<String> execute(String sentence)throws Exception
    {
        Vector<String> ans = new Vector<String>();
        String[] words = splitSentence(sentence);
        String requete = words[0].toLowerCase();
        String table = getSelectedTable(sentence);


    //grammaire
        switch (requete) {
            // omeo nom ao dept
            case "omeo":
                Vector<String> selectedCol = getSelectedColumn(sentence);
                int indCondition = checkCondition(sentence);
                if (indCondition==0) {
                    ans = select(this.DBused, table, selectedCol);
                    
                }
                else{
        // System.out.println("select with condition");
                    String condition = getCondition(sentence);
                    ans = selectWithCondition(this.DBused, table, selectedCol, condition);

                }
                break;

            case "apifandraiso":
        //apifandraiso emp sy dept raha mitovy ny idDept sy id ka omeo emp.nom,dept.localisation
                String tab1 = words[1];
                String tab2 = words[3];
                Vector<String> selCol1 = new Vector<String>();
                Vector<String> selCol2 = new Vector<String>();

                if (words[12].equals("*")) {
                    selCol1.add("*");
                   ans = select(this.DBused, tab1, selCol1);
                    Vector<String> sel2 = select(this.DBused, tab2, selCol1);
                    for (int i = 0; i < sel2.size(); i++) {
                        ans.add(sel2.get(i));
                    }

                }else{
                    String valueJoint = words[12];
        // System.out.println("val : "+valueJoint);
                    selCol1 = getSelectedColumnJoint(valueJoint, tab1);
                    selCol2 = getSelectedColumnJoint(valueJoint, tab2);

                    for (int i = 0; i < selCol1.size(); i++) {
        // System.out.println("selCol : "+ selCol1.get(i));
                }                    
                    if (selCol1.size()>0) {
                        ans = select(this.DBused, tab1, selCol1);
                    }
                    if (selCol2.size()>0) {
                        Vector<String> sel2 = select(this.DBused, tab2, selCol2);
                        for (int i = 0; i < sel2.size(); i++) {
                            ans.add(sel2.get(i));
                        }              
                    }  
                }
                
                break;

            case "fafao_ny_database":
                dropDataBase(words[1]);
                break;

            case "fafao_ny_table":
        // fafao_ny_table ex
                deleteTable(this.DBused, words[1]);
                break;

                
            case "manamboara_table":
        // manamboara_table emp izay ahitana nom,idDept,age
                Vector<String> tableValue = getHeadTableValue(words[4]);
                createTable(this.DBused, words[1],tableValue );
                break;
                
            case "manamboara_datatase":
                createDataBase(words[1]);
                break;

            case "apio":
        // apio 1,Max,Andria ao emp
                Vector<String> insertValue = getInsertValue(words[1]);
                insert(this.DBused, table, insertValue);
                break;

            case "fafao":
        // fafao ao emp rehefa nom = max na nom = tix
                String condition = getCondition(sentence);

                delete(this.DBused, table,condition);
                break;

    // ovao nom = tix ao emp rehefa condition
            case "ovao":
                update(this.DBused, table, sentence);
                break;

            case "apifanalao":
                ans = difference(this.DBused, words[1], words[3]);
                break;
            case "diviseo":
    //diviseo dept sy div
               ans =  division(this.DBused, words[1], words[3]);
                break;
            default:
                break;
        }
        return ans;
    }




    public String getSelectedTable(String sentence)throws Exception
    {
        String ans = new String();
        String[] words = splitSentence(sentence);
       
        for (int i = 1; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("ao")) {
                ans = words[i+1];
            }
            
        }
        return ans;
    }

    public String[] splitValue(String sentence)
    {
        String[] value = sentence.split(",");
        return value;
    }
    public Vector<String> getHeadTableValue(String sentence)throws Exception
    {
        Vector<String> ans = new Vector<String>();
        // String[] words = splitSentence(sentence);
        // String split = words[0];
        String[] value = splitValue(sentence);
        for (int i = 0; i < value.length ; i++) {
            ans.add(value[i]);
        }
        return ans;
    }
    public Vector<String> getInsertValue(String sentence)throws Exception
    {
        Vector<String> ans = new Vector<String>();
        // String[] words = splitSentence(sentence);
        // String split = words[0];
        String[] value = splitValue(sentence);
        for (int i = 0; i < value.length ; i++) {
            ans.add(value[i]);
        }
        return ans;
    }

    public Vector<String> getSelectedColumn(String sentence)throws Exception
    {
        Vector<String> ans = new Vector<String>();
        String[] words = splitSentence(sentence);
        int ind = 0;
        for (int i = 1; i < words.length && (words[i].equalsIgnoreCase("ao")==false); i++) {
            ans.add(words[i]);
        }
        return ans;
    }

    public Vector<String> getSelectedColumnJoint(String value, String tab)throws Exception
    {
        Vector<String> ans = new Vector<String>();
        String[] words = splitValue(value);
        int ind = 0;
        String[] spl;
        for (int i = 0; i < words.length ; i++) {
            spl = words[i].split("\\.");  
            if (spl[0].equalsIgnoreCase(tab)) {
                ans.add(spl[1]);
                System.out.println("getSelectedColumnJoint:: col: "+spl[1]+"/ tab : "+tab); 

            }
        }
        return ans;
    }
}
