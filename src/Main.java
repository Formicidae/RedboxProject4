/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Eddie
 */
import BSTree.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BSTree tree = new BSTree();
        try{
            File inventoryF = new File("inventory.dat");
            Scanner inventory = new Scanner(inventoryF);
            while(inventory.hasNextLine()){
                addMovie(tree,inventory.nextLine());
            }
            inventory.close();
        } catch (IOException e) {
             // complain to user
             System.out.println("File not found" + e);
        }
        
        try{
            File transactionsF = new File("transaction.log");
            Scanner transactions = new Scanner(transactionsF);
            PrintWriter error = new PrintWriter(new File("error.log"));
            while(transactions.hasNextLine()){
                lineRead(transactions.nextLine(),tree,error);
            }
            transactions.close();
        } catch (IOException e) {
             // complain to user
             System.out.println("File not found" + e);
        }
        Scanner error = new Scanner("error.log");
        File err = new File("error.log");
        if(!error.hasNext()){
            err.delete();
        }
        error.close();
        
        
        PrintWriter output = null;
        try{
            output = new PrintWriter(new File("redbox_kiosk.txt"));
        } catch (IOException e) {
             // complain to user
             System.out.println("File not found" + e);
        }
        print(tree.getRoot(),output);
        output.close();
        
    }
    
    public static void addMovie(BSTree t,String line){
        Scanner lineS = new Scanner(line).useDelimiter(",");
        String Title = lineS.next();
        Title = Title.substring(1,Title.length()-1);
        t.addNode(new Node(Title,lineS.nextInt(),lineS.nextInt()),t.getRoot(),null);
        System.out.println(Title);
    }
    
    public static void lineRead(String line,BSTree tree, PrintWriter errorW){
        Scanner sc = new Scanner(line);
        Scanner error = new Scanner(line);
        String op = sc.next();
        error.next();
        sc.useDelimiter(",");
        error.useDelimiter(",");
        if(op.equals("add")){
            if(!error.next().equals("")){
                if(error.nextInt() > -1){
                    if(error.hasNext()){
                        errorW.print(line);
                        return;
                    }
                    else{
                        String title = sc.nextLine();
                        //System.out.println(title.substring(1,2));
                        if(!title.substring(1,2).equals("\"")){
                            errorW.print(line);
                            return;
                        }
                        addMovie(tree,title.substring(1,title.length())+",0");
                        return;
                    }
                       
                }
            }
            errorW.print(line);
            return;
        }
        else if(op.equals("remove")){
                        if(!error.next().equals("")){
                if(error.nextInt() > -1){
                    if(error.hasNext()){
                        errorW.print(line);
                        return;
                    }
                    else{
                        String title = sc.next();
                        if(!title.substring(1,2).equals("\"")){
                            errorW.print(line);
                            return;
                        }
                        int num = sc.nextInt();
                        addMovie(tree,title.substring(1,title.length()) + ",-" + num + ",0");
                        return;
                    }
                       
                }
            }
            errorW.print(line);
            return;     
        }
        else if(op.equals("rent")){
            if(!error.next().equals("")){
                    if(error.hasNext()){
                        errorW.print(line);
                        return;
                    }
                    else{
                        String title = sc.next();
                        if(!title.substring(1,2).equals("\"")){
                            errorW.print(line);
                            return;
                        }
                        title = title.substring(1,title.length());
                        addMovie(tree,title + ",-1,1");
                        return;
                    }     
            }
            errorW.print(line);
            return;  
        }
        else if(op.equals("return")){
            if(!error.next().equals("")){
                    if(error.hasNext()){
                        errorW.print(line);
                        return;
                    }
                    else{
                        String title = sc.next();
                        if(!title.substring(1,2).equals("\"")){
                            errorW.print(line);
                            return;
                        }
                        addMovie(tree,title.substring(1,title.length()) + ",1,-1");
                        return;
                    }     
            }
            errorW.print(line);
            return; 
        }
        errorW.print(line);
    }
    /*
    public static void print(Node n,PrintWriter out){
        if(n.getLeft() == null){
            out.printf("%32s%5d%5d",n.getTitle(),n.getAvalible(),n.getRented());
            out.println();
        }
        else{
            if(n.getLeft() != null){
                print(n.getLeft(),out);
            }
            out.printf("%32s%5d%5d",n.getTitle(),n.getAvalible(),n.getRented());
            out.println();
            if(n.getLeft() != null){
                print(n.getRight(),out);
            }
        }
        
    }
*/
    public static void print(Node n,PrintWriter out){
        if(n != null){
            print(n.getLeft(),out);

            System.out.printf("%32s%5d%5d",n.getTitle(),n.getAvalible(),n.getRented());
            out.printf("%32s%5d%5d",n.getTitle(),n.getAvalible(),n.getRented());
            out.println();

            print(n.getRight(),out);
        }
    }
    
    
}
