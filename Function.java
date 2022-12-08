package tools;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;
public class Function 
{

//ovao nom = tix ao emp rehefa condition
    public void update(String DBname, String tablename, String sentence)throws Exception
    {
        String path = DBname+"/"+tablename+".myDB";
        File file = new File(path);

        String[] words = splitSentence(sentence);
        String cond = getCondition(sentence);
        String[] condition  = splitSentence(cond);
//treatment : create temporary file which play the role of container
        String pseudoPath = "pseudoText";
        File pseudoFile = new File(pseudoPath);

        String[] head = getTableHeader(DBname, tablename);
        try(FileWriter out = new FileWriter(pseudoFile,true);PrintWriter writer = new PrintWriter(out);) {

            FileInputStream fl = new FileInputStream(path);
            Scanner sc = new Scanner(fl);
            while (sc.hasNextLine()) {
                String val = sc.nextLine();
                String[] value = splitLine(val);
    // System.out.println("length: "+condition.length+"  "+condition[3]);
                if (condition.length>4) {
                    if (condition[4].equalsIgnoreCase("na")) {
                        boolean check = checkConditionLineOr(head, cond, value);
                        if (check==true) {
                            int nbr_upD = 1;
                            for (int i = 0; i < words.length; i++) {
                                if (words[i].equals(",")) {
                                    nbr_upD++;
                                }
                            }
                            System.out.println("nbr_upD : "+nbr_upD);
                            int ind = 3;                            //indice pour la valeur à changer
    
                            for (int i = 0; i < value.length; i++) {
                                    
                                        int col = getIndiceColumn(head,words[1]);
                                        if (i==col) {
                                            writer.write(words[ind]);
                                            writer.write("/");
                                            ind = ind + 4;
                                        }else{
                                            writer.write(value[i]);
                                            writer.write("/");
                                        }
                                                     
                                }
    
                            writer.println();                      
    
                        }
                        else
                        {
                            writer.write(val);
                            writer.println();
                        }
                    }else if (condition[4].equalsIgnoreCase("sy")) {
                        boolean check = checkConditionLineAnd(head, cond, value);
                        if (check==true) {
                            int nbr_upD = 1;
                            for (int i = 0; i < words.length; i++) {
                                if (words[i].equals(",")) {
                                    nbr_upD++;
                                }
                            }
                            System.out.println("nbr_upD : "+nbr_upD);
                            int ind = 3;                            //indice pour la valeur à changer
    
                            for (int i = 0; i < value.length; i++) {
                                    
                                        int col = getIndiceColumn(head,words[1]);
                                        if (i==col) {
                                            writer.write(words[ind]);
                                            writer.write("/");
                                            ind = ind + 4;
                                        }else{
                                            writer.write(value[i]);
                                            writer.write("/");
                                        }
                                                     
                                }
    
                            writer.println();                      
    
                        }
                        else
                        {
                            writer.write(val);
                            writer.println();
                        }

                    }
                }else{
                    boolean check = checkConditionLineOr(head, cond, value);
                    if (check==true) {
                        int nbr_upD = 1;
                        for (int i = 0; i < words.length; i++) {
                            if (words[i].equals(",")) {
                                nbr_upD++;
                            }
                        }
                        System.out.println("nbr_upD : "+nbr_upD);
                        int ind = 3;                            //indice pour la valeur à changer

                        for (int i = 0; i < value.length; i++) {
                                
                                    int col = getIndiceColumn(head,words[1]);
                                    if (i==col) {
                                        writer.write(words[ind]);
                                        writer.write("/");
                                        ind = ind + 4;
                                    }else{
                                        writer.write(value[i]);
                                        writer.write("/");
                                    }
                                                 
                            }

                        writer.println();                      

                    }
                    else
                    {
                        writer.write(val);
                        writer.println();
                    }
                }

            }
            sc.close();
            out.close();writer.close();
            file.delete();
            try(FileWriter pseudo_out = new FileWriter(file,true);PrintWriter pseudo_writer = new PrintWriter(pseudo_out);){
                FileInputStream pseudo_fl = new FileInputStream(pseudoPath);
                Scanner pseudo_sc = new Scanner(pseudo_fl);
                while (pseudo_sc.hasNextLine()) {
                    String pseudo_value = pseudo_sc.nextLine();
                    pseudo_writer.write(pseudo_value);
                    pseudo_writer.println();
                }
                pseudo_sc.close();
                pseudo_out.close();pseudo_writer.close();
            }
    //delete the temporary file
            pseudoFile.delete();
        } 

    System.out.println("update row done");         

    }

    public void delete(String DBname, String tablename, String cond)throws Exception
    {
        String path = DBname+"/"+tablename+".myDB";
        File file = new File(path);
        String[] condition  = splitSentence(cond);
//treatment : create temporary file which play the role of container
        String pseudoPath = "pseudoText";
        File pseudoFile = new File(pseudoPath);

        String[] head = getTableHeader(DBname, tablename);
        try(FileWriter out = new FileWriter(pseudoFile,true);PrintWriter writer = new PrintWriter(out);) {

            FileInputStream fl = new FileInputStream(path);
            Scanner sc = new Scanner(fl);
            while (sc.hasNextLine()) {
                String val = sc.nextLine();
                String[] value = splitLine(val);
    // System.out.println("length: "+condition.length+"  "+condition[3]);
                if (condition.length>4) {
                    if (condition[4].equalsIgnoreCase("na")) {
                        boolean check = checkConditionLineOr(head, cond, value);
                        if (check==false) {
                            writer.write(val);
                            writer.println();
                        }
                    }else if (condition[4].equalsIgnoreCase("sy")) {
                        boolean check = checkConditionLineAnd(head, cond, value);
                        if (check==false) {
                            writer.write(val);
                            writer.println();
                        }
                    }
                }else{
                    boolean check = checkConditionLineOr(head, cond, value);
                    if (check==false) {
                        writer.write(val);
                        writer.println();
                    }
                }

            }
            sc.close();
            out.close();writer.close();
            file.delete();
            try(FileWriter pseudo_out = new FileWriter(file,true);PrintWriter pseudo_writer = new PrintWriter(pseudo_out);){
                FileInputStream pseudo_fl = new FileInputStream(pseudoPath);
                Scanner pseudo_sc = new Scanner(pseudo_fl);
                while (pseudo_sc.hasNextLine()) {
                    String pseudo_value = pseudo_sc.nextLine();
                    pseudo_writer.write(pseudo_value);
                    pseudo_writer.println();
                }
                pseudo_sc.close();
                pseudo_out.close();pseudo_writer.close();
            }
    //delete the temporary file
            pseudoFile.delete();
        } 

         System.out.println("delete row done");

    }

    public Vector<String> select(String DBname, String tablename, Vector<String> selected )throws Exception
    {
        Vector<String> ans = new Vector<String>();
        String[] head = getTableHeader(DBname, tablename);
        int ind = 0;

        String path = DBname+"/"+tablename+".myDB";
        FileInputStream fl = new FileInputStream(path);
        Scanner sc = new Scanner(fl);



        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (ind!=0 && line.equalsIgnoreCase("")==false) {
                String[] value = splitLine(line);
                if (selected.get(0).equalsIgnoreCase("*")) {
                    for (int i = 0; i < value.length; i++) {
                        System.out.println(head[i]+" : "+value[i]);
                        ans.add(value[i]);
                    }
                }else
                {
                    for (int i = 0; i < selected.size(); i++) {
                        int col = getIndiceColumn(head, selected.get(i));
                        System.out.println(head[col]+" : "+value[col]);
                        ans.add(value[col]);

                    }
                }

            }
            System.out.println("________________");
            ind++;
        }


        sc.close();
        return ans;
    }



    public String getCondition(String sentence)
    {
        String[] para = sentence.split("rehefa");
        
        return para[1];
    }

    public int checkCondition(String sentence)
    {
        int ind = 0;
        String[] words = splitSentence(sentence);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("rehefa")) {
                ind = i;
            }
        }
        return ind;
    }

    public boolean checkConditionLineOr(String[] head, String condition, String[] value)
    {
        boolean check = false;
        int[] colCondition = getIndiceColumnCondition(head, condition);
        String[] cond = splitSentence(condition);
        int indCol = 2;
        for (int i = 0; i < colCondition.length; i++) {
            for (int j = 3; j < cond.length; j=j+4) {
    //  System.out.println("check condtion OR : "+cond[j]);
                if (value[colCondition[i]].equalsIgnoreCase(cond[j]) ) {
                    check = true;
                    
                }
            }
        }
        return check;
    }


    public boolean checkConditionLineAnd(String[] head, String condition, String[] value)throws Exception
    {
        boolean check = false;
        int[] colCondition = getIndiceColumnCondition(head, condition);
        String[] cond = splitSentence(condition);
        int indCol = 0;
        for (int j = 3; j < cond.length; j=j+4) {
            for (int i = 0; i<colCondition.length; i++) {
                if (colCondition.length>1 && colCondition[i]==colCondition[i+1]) {//meme nom de colonne pour la condition: ex: empno = 1 sy empno = 2
                    throw new Exception("invalide condition");
                }
System.out.println(value[colCondition[i]]+"  "+cond[j]);
                if (value[colCondition[i]].equalsIgnoreCase(cond[j]) ) {
                    indCol++;
                }
            }
        }
        
System.out.println(indCol+"  "+colCondition.length);
        if (indCol==colCondition.length) {
            check = true;
        }
        return check;
    }

    public int[] getIndiceColumnCondition(String[] head, String cond)
    {
        String[] condition = splitSentence(cond);
        int nbr = 0;
        for (int i = 0; i < condition.length; i++) {
            if (condition[i].equalsIgnoreCase("na") || condition[i].equalsIgnoreCase("sy")  ) {
                nbr++;
            }
        }

        int ind = 0;
        int[] col = new int[nbr+1];

        for (int i = 1; i < condition.length; i=i+4) {
            for (int j = 0; j < head.length; j++) {
                if (condition[i].equalsIgnoreCase(head[j])) {
        
                    col[ind]=j;
                    ind++;
                }
            }
        }
        return col;
    }

    public Vector<String> selectWithCondition(String DBname, String tablename, Vector<String> selected ,String cond)throws Exception
    {

        Vector<String> ans = new Vector<String>();
        String[] head = getTableHeader(DBname, tablename);
        int ind = 0;

        String path = DBname+"/"+tablename+".myDB";
        FileInputStream fl = new FileInputStream(path);
        Scanner sc = new Scanner(fl);

        String[] condition = splitSentence(cond);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (ind!=0) {

                String[] value = splitLine(line);
                if (condition.length>4) {
    //  System.out.println("condtion 4 :"+condition[4]);
                    if (condition[4].equalsIgnoreCase("na")) {
                        boolean check = checkConditionLineOr(head, cond, value);
    // System.out.println("check :"+check);
                        if (check == true) {
                            if (selected.get(0).equalsIgnoreCase("*")) {
    
                                for (int i = 0; i < value.length; i++) {
                                    System.out.println(head[i]+" : "+value[i]);
                                    ans.add(value[i]);
                                }
                            }else
                            {
                                for (int i = 0; i < selected.size(); i++) {
                                    int col = getIndiceColumn(head, selected.get(i));
                                    System.out.println(head[col]+" : "+value[col]);
                                    ans.add(value[col]);

                                }
    
                            }
                        }else
                        {
                            System.out.println("empty");
                        }

                    }else if (condition[4].equalsIgnoreCase("sy")) {
                        boolean check = checkConditionLineAnd(head, cond, value);
    //  System.out.println("check :"+check);
                        if (check == true) {
                            if (selected.get(0).equalsIgnoreCase("*")) {
    
                                for (int i = 0; i < value.length; i++) {
                                    System.out.println(head[i]+" : "+value[i]);
                                    ans.add(value[i]);

                                }
                            }else
                            {
                                for (int i = 0; i < selected.size(); i++) {
                                    int col = getIndiceColumn(head, selected.get(i));
                                    System.out.println(head[col]+" : "+value[col]);
                                    ans.add(value[col]);

                                }
    
                            }
                        }else
                        {
                            System.out.println("empty");
                        }
                    }
    
                }else{
                    boolean check = checkConditionLineOr(head, cond, value);
                    if (check == true) {
                        if (selected.get(0).equalsIgnoreCase("*")) {

                            for (int i = 0; i < value.length; i++) {
                                System.out.println(head[i]+" : "+value[i]);
                                ans.add(value[i]);

                            }
                        }else
                        {
                            for (int i = 0; i < selected.size(); i++) {
                                int col = getIndiceColumn(head, selected.get(i));
                                System.out.println(head[col]+" : "+value[col]);
                                ans.add(value[col]);

                            }

                        }
                    }else
                    {
                        System.out.println("empty");
                    }

                }
            }
            System.out.println("________________");
            ind++;
        }


        sc.close();
        return ans;
    }


    public void insert(String DBname, String tablename, Vector<String> value)throws Exception
    {
        String[] head = getTableHeader(DBname, tablename);
        String path = DBname+"/"+tablename+".myDB";
        File fl = new File(path);
        try(FileWriter out = new FileWriter(fl,true);PrintWriter writer = new PrintWriter(out);) {
            writer.println();
            if (head.length!=value.size()) {
                throw new Exception("Impossible to insert these values.Their number don't match to the table column number");
            }
            for (int i = 0; i < value.size(); i++) {
                
                writer.write(value.get(i)+"/");
            }
            out.close();writer.close();
        System.out.println("Insert done");
        } 
    }

    public void deleteTable(String DBname, String tabname)throws Exception
    {
        String path = DBname+"/"+tabname+".myDB";
        File fl = new File(path);

        boolean check = fl.delete();
        if (check==true) {
            System.out.println("Table deleted");
        }
        else
        {
            System.out.println("impossible to delete the Table");
            throw new Exception("impossible to delete the Table/Table doesn't exist");
        }

    }

    public void createTable(String DBname, String filename, Vector<String> columnname)throws Exception
    {

        String path = DBname+"/"+filename+".myDB";
        File fl = new File(path);
        if (fl.isFile()) {
            String msg ="A table has a same name.";
            throw new Exception(msg);
        }
        boolean check = fl.createNewFile();


        try(FileWriter out = new FileWriter(fl);PrintWriter writer = new PrintWriter(out);) {
                       
            for (int i = 0; i < columnname.size(); i++) {
                writer.write(columnname.get(i)+"/");
            }
            out.close();writer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        if(check==true)
        {
            System.out.println("Table created");
        }
        else
        {
            System.out.println("Impossible to create the Table");
        }
    }

    public void dropDataBase(String DBname)throws Exception
    {
        File fl = new File(DBname);
        // boolean check = deleteFolder();
        if(fl.isDirectory()==false)
        {
            String mes = "It's not a database";
            throw new Exception(mes);
        }
        File[] list = fl.listFiles();
        for (int i = 0; i < list.length; i++) {
            list[i].delete();
        }
        boolean check = fl.delete();
        if (check==true) {
            System.out.println("database deleted");
        }
        else
        {
            System.out.println("impossible to delete the database");
        }

    }

    public void createDataBase(String DBname)throws Exception
    {
        File fl = new File(DBname);
        boolean check = fl.mkdir();
        if(check==true)
        {
            System.out.println("database created");
        }
        else
        {
            System.out.println("Impossible to create the database");
        }
    }


    public String[] splitSentence(String sentence)
    {
        String[] res =sentence.split(" ");
        return res;
    }


    public int getIndiceColumn(String[] header, String selected)throws Exception
    {
        int ind = 0;
        boolean error = true;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equalsIgnoreCase(selected)) {
                ind = i;
                error = false;
            }
        }
        if (error == true) {
            throw new Exception("column name invalide");
        }
        return ind;
    }

    public String[] getTableHeader(String DBname, String filename)throws Exception
    {
        String path = DBname+"/"+filename+".myDB";
        int ind = 0;
        String[] head = null ;
        FileInputStream fl = new FileInputStream(path);
        Scanner sc = new Scanner(fl);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (ind==0) {
                head = splitLine(line);
            }
            ind++;
        }
        sc.close();
        return head;

    }
    
    public String[] splitLine(String sentence)
    {
        String[] res =sentence.split("/");
        return res;
    }
    
    public boolean verefTab(String DBname, String tab1, String tab2)throws Exception
    {
        boolean check = true;
        String[] head1 = getTableHeader(DBname, tab1);
        String[] head2 = getTableHeader(DBname, tab2);
    // System.out.println(head1.length+"=="+head2.length);
        if (head1.length!=head2.length) {

            return false;
        }
        for (int i = 0; i < head2.length; i++) {
            if (head1[i].equalsIgnoreCase(head2[i])==false) {
    // System.out.println(head1[i]+"=="+head2[i]);
                return false;
            }
        }

        return check;
    }

    public boolean verefLine(String[] obj1,String[] obj2)throws Exception
    {
        boolean ver = false;

        int nbr = 0;
        for (int i = 0; i < obj1.length; i++) {

            if (obj1[i].equalsIgnoreCase(obj2[i])) {
                nbr++;
            }
        }

        if (nbr==obj1.length) {
            ver = true;
        }

        return ver;
    }

    public Vector<String> difference(String DBname, String tab1, String tab2)throws Exception
    {
        Vector<String> ans = new Vector<String>();
        boolean check = verefTab(DBname, tab1, tab2);
    // System.out.println("check :"+check);
        if (check==false) {
            throw new Exception("Impossible to execute your request");
        }

        String path1 = DBname+"/"+tab1+".myDB";
        FileInputStream fl1 = new FileInputStream(path1);
        Scanner sc1 = new Scanner(fl1);


        int ind1 = 0;
        Vector<String> v1 = new Vector<String>();
        while (sc1.hasNextLine()) {
            String line1 = sc1.nextLine();
            if (ind1!=0 && line1.equalsIgnoreCase("")==false) {            
                v1.add(line1);
            }
            ind1++;
        }
        sc1.close();
    

        String path2 = DBname+"/"+tab2+".myDB";
        FileInputStream fl2 = new FileInputStream(path2);
        Scanner sc2 = new Scanner(fl2);

boolean check1 = false;
        int c = 0;
        int ind2 = 0;
        Vector<String> v2 = new Vector<String>();
        while (sc2.hasNextLine()) {
            String line2 = sc2.nextLine();
            if (ind2!=0 && line2.equalsIgnoreCase("")==false) {            
                for (int i = 0; i < v1.size(); i++) {
                    // System.out.println(v1.get(i)+" === "+line2);

                    if (v1.get(i).equalsIgnoreCase(line2)) {
                        c = i;
                        // System.out.println("c : "+c);

                        check1 = true;
                    }

                }

                if (check1==true) {

                    v1.remove(c);
                    check1 = false;
                }
                else{
                    v1.add(line2);
                }
            }
            ind2++;
        }
        sc2.close();
    // System.out.println("eto : "+v1.size());
        String[] head = getTableHeader(DBname, tab1);
        System.out.println("____________________");

        for (int i = 0; i < v1.size(); i++) {
            String[] value = splitLine(v1.get(i));

            for (int j = 0; j < head.length; j++) {

                System.out.println(head[j]+" : "+value[j]);
                ans.add(value[j]);
            }
            System.out.println("____________________");
        }

        return ans;
    }

    public Vector<String> division(String DBname, String tab1, String tab2 )throws Exception
    {
        Vector<String> ansDiv = new Vector<String>();

        Vector<String> ans = new Vector<String>();
        int n = 0;
        int m = 0;
        String[] h1 = getTableHeader(DBname,tab1 );
        String[] h2 = getTableHeader(DBname,tab2 );
        Vector<String> tete = new Vector<String>();

        for (int i = 0; i < h1.length; i++) {
            for (int j = 0; j < h2.length; j++) {
                if (h1[i].equalsIgnoreCase(h2[j])) {
                    n++;
                    m++;
                }
            }
            if (m==0) 
            {
                tete.add(h1[i]);//prendre la colonne different des 2
            }
            m = 0;
        }
        if (n==0) {
            throw new Exception("Impossible to execute your request 'division'");
        }

        
        Vector<String> otherdt = AllElement(DBname, tab1, tete);//element du colonne different entre tab1 et  tab2 

        Vector<String> tp = new Vector<String>();
        tp.add("*");
        Vector<String> dt = AllElement(DBname, tab2, tp);
        

        Vector<String> alldt = AllElement(DBname, tab1, tp);
        
        int ao = 0;
        
        
        
        Vector<String> vectDiv = new Vector<String>();
        for (int i = 0; i < otherdt.size(); i = i + tete.size()) {
            vectDiv.clear();
            for (int j = i; j < i+tete.size(); j++) {
                vectDiv.add(otherdt.get(j));
            }

            for (int j = 0; j < dt.size(); j = j + h2.length) {
                // temp = vectDiv;
                // System.out.println(" j : "+j);
                for (int j2 = j; j2 < j+h2.length; j2++) {
                    vectDiv.add(dt.get(j2));
                }
                
                // System.out.println("------------------");

                        
                        // for (int a = 0; a < vectDiv.size(); a++) {
                        //     System.out.println("vectDiv : "+vectDiv.get(a));
                        // }

                        // System.out.println("------------------");
                        int ifSame = sameElement(vectDiv, alldt);
                        // System.out.println("same "+ifSame);

                if (ifSame>0) {
                    ao ++;
                }
                
                int rowDt = dt.size()/h2.length;
                if (ao==rowDt) {
                    for (int k = i; k < i+tete.size(); k++) {
                        ans.add(otherdt.get(k));
                    }
                }

                vectDiv.clear();

                for (int k = i; k < i+tete.size(); k++) {
                        vectDiv.add(otherdt.get(k));
                    }
                }
                
                ao = 0;
            
            // System.out.println("vec"+tete.size());
        }
        
        for (int i = 0; i < ans.size(); i++) {
            System.out.println("diviseur : "+ans.get(i));
        }

        return ans;
    }
    
    public boolean verefDivision(int t1, int t2, Vector<String> vect, Vector<String> word)
    {
        for (int i = 0; i < word.size(); i++) {
            // System.out.print("/ "+word.get(i));
        }
        int n = 0;
        int q = 0;
        for (int i = 0; i < vect.size(); i = i +t1) {
            Vector<String> allTemp = new Vector<String>();
            for (int k = i; k <i+ t1; k++) {
                allTemp.add(vect.get(k));
                
            }
            // for (int j = 0; j < allTemp.size(); j++) {
            //     System.out.println(" /"+allTemp.get(j));
            // }
            // System.out.println();
            // System.out.println("---------------------");
            for (int j2 = 0; j2 < word.size(); j2++) {
                for (int j = 0; j < allTemp.size(); j++) {
                    // System.out.println(word.get(j2)+" ::: "+allTemp.get(j));
                    if (word.get(j2).equalsIgnoreCase(allTemp.get(j))) {

                        q++;
                    }
    //System.out.print("/"+allTemp.get(j));
                }
            }
            // System.out.println("q: "+q+" word.size() : "+word.size());
            if (q==word.size()) {
                n++;
                return true;
            }
            

            q = 0;
        }

        if (n==0) {
            return false;
        }
        return true;
    }
        
    public Vector<String> AllElement(String DBname, String tablename, Vector<String> selected )throws Exception
    {
        String[] head = getTableHeader(DBname, tablename);
        int ind = 0;

        String path = DBname+"/"+tablename+".myDB";
        FileInputStream fl = new FileInputStream(path);
        Scanner sc = new Scanner(fl);

        Vector<String> ans = new Vector<String>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (ind!=0 && line.equalsIgnoreCase("")==false) {
                String[] value = splitLine(line);
                if (selected.get(0).equalsIgnoreCase("*")) {
                //     Vector<String> temp = new Vector<String>();
                    for (int i = 0; i < value.length; i++) {
                        ans.add(value[i]);
                    }
                //     int same = sameElement(temp, ans);
                //     if (same<1) {
                        
                //         for (int i = 0; i < value.length; i++) {
                // // System.out.println(head[i]+" : "+value[i]);
                //             ans.add(value[i]);
                //         }
                //     }
                //     temp.clear();
                }else
                {
                    Vector<String> temp = new Vector<String>();

                    for (int i = 0; i < selected.size(); i++) {
                        int col = getIndiceColumn(head, selected.get(i));
            //  System.out.println(head[col]+" : "+value[col]);
                        temp.add(value[col]);
                        
                        int same = sameElement(temp, ans);
                        if (same<1) {
                            
                            ans.add(value[col]);
                        }
                        temp.clear();

                    }

                }

            }
            ind++;
        }


        sc.close();
        return ans;
    }

    public void multiplication(String DBname, String tab1, String tab2 )throws Exception
    {
        String[] h1 = getTableHeader(DBname,tab1 );
        String[] h2 = getTableHeader(DBname,tab2 );
    }

    public int sameElement(Vector<String> row, Vector<String> all)
    {
        int nbr = 0;
        // System.out.println("row size : "+row.size());
        for (int i = 0; i < all.size(); i=i+row.size()) {
            int check = 0;
            for (int j = 0; j < row.size(); j++) {
                for (int k = i; k < i+row.size(); k++) {
                    if (row.get(j).equalsIgnoreCase(all.get(k))) {
                        check++;
                    }
                }
            }
            if (check==row.size()) {
                nbr++;
            }
        }
        return nbr;
    }

    public boolean checkElement(Vector<String> row, Vector<String> all)
    {
        for (int i = 0; i < row.size(); i++) {
            for (int j = 0; j < all.size(); j++) {
                // if (row.get(i)) {
                    
                // }   
            }
        }
        return false;
    }
    public Function() {
    }
}
// for (int i = 0; i < otherdt.size(); i = i + h1.length-h2.length) {
            
//     for (int q = 0; q < dt.size(); q = q + h2.length) {
//         Vector<String> vectDiv = new Vector<String>();
        
//         for (int k = i; k <i+ h1.length-h2.length; k++) {
//             vectDiv.add(otherdt.get(k));
//         }
//         for (int j = q; j < q+ h2.length; j = j + h2.length) {

//             vectDiv.add(dt.get(j));
//         }
        
        
        
//         if (verefDivision(h1.length, h2.length, alldt, vectDiv)) {
//             ao++;
//         }


//         // System.out.println("aooo : "+ao);
//         if (ao==dt.size()) {
//             for (int j = i; j <i+ h1.length-h2.length; j++) {
//                 temp.add(otherdt.get(j));
//             }
            
//         }
//         for (int j = 0; j < vectDiv.size(); j++) {
//             System.out.println(vectDiv.get(j));
//         }
//         System.out.println("--------------------");
//         // System.out.println("same : "+vectDiv.size());

//         ao = 0;
//         int ifSame = sameElement(vectDiv, alldt);
        
//         // System.out.println("ifSame  :  "+ifSame);
//     }
// }