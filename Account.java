package src;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Account
{ 
    /*
    private String username;
    private String password;
    private String[] accounts = {};

    public Account(String u, String p){
        this.username = u;
        this.password = p; 
    }*/
    //public static void main(String[] args){
    public void accountMethod(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Create Username");

        String username = userInput.nextLine();
        

        Scanner passInput = new Scanner(System.in);
        System.out.println("Create Password");

        String password = userInput.nextLine();
        

        //System.out.println(account);

        String loc = "accounts.csv"; // location of file we will be writing into

        try {
            FileWriter fw = new FileWriter(loc, true); // FileWriter function parameters: location of file, true if writing into end of file instead of biginning
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
    
            pw.println(username + "," + password); // uses print writer function to print username, password into CSV file
            pw.flush(); // flushes stream
            pw.close(); // closes stream
    
        } catch (FileNotFoundException e) { // file not found exception
            System.out.println(e.getMessage());
        } catch (IOException e) { // exception type IOException
            e.printStackTrace();
        }

        userInput.close();
        passInput.close();
       
    }
}