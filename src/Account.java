package src;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;


public class Account{ 

    public String username;
    public String password;
    public String CSVFilename;
    public Boolean loginCorrect = false;
    

    public Account(String u, String p, String cfn){
        this.username = u;
        this.password = p; 
        this.CSVFilename = cfn;
        this.loginCorrect = false;
    }

    //getters and setters
    public void setUsername(String user){
        this.username = user;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public String getPassword(){
        return password;
    }

    public void setCSVFile(String filename){
        this.CSVFilename = filename;
    }

    public String getCSVFileName(){
        return CSVFilename;
    }

    public void setUsername(Boolean loginCorrect){
        this.loginCorrect = loginCorrect;
    }

    public Boolean getLoginCorrect(){
        return loginCorrect;
    }

// Validate Login
    public void creatingAccount(Scanner optionsScan){
        // Login or Create Account

        System.out.println("1: Create Account\n2: Login\n");
        System.out.println("Make Selection: ");
        
        int option = optionsScan.nextInt();
        switch(option){
            case 1: // creating an account
                this.accountMethod();
                break;
            case 2:
            while(this.loginCorrect==false){ // while the login input does not match, keep getting inputs
            // get user inputs for unsername and password to login
            Scanner userInput = new Scanner(System.in);
            System.out.println("Enter Username");
            String username = userInput.nextLine();
            System.out.println("Enter Password");
            Scanner passInput = new Scanner(System.in);
            String password = passInput.nextLine();

            // Go through accounts in the accounts.csv file and put them in an array list
            //https://www.baeldung.com/java-csv-file-array
            List<List<String>> accounts = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("src/accounts.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    accounts.add(Arrays.asList(values));
                }
            } catch (FileNotFoundException e) { // file not found exception
                System.out.println(e.getMessage());
            } catch (IOException e) { // exception type IOException
                e.printStackTrace();
            }

            // Check to see is accountUser and accountPass matches with the user input
            for(int count = 0; count < accounts.size(); count++){
                String accountUser = (accounts.get(count).get(0)); // 0th index is the accountUser
                String accountPass = (accounts.get(count).get(1)); // 1st index is the accountPass
                if(accountUser.equals(username)){ // check to see if account username matches with user input
                    if(accountPass.equals(password)){ // check to see if account password matches with user input
                        System.out.println(" \n WELCOME BACK \n ");
                        this.loginCorrect = true; // set loginCorrect to true if match
                    }
                    else{
                        System.out.println(" \n INCORRECT USER OR PASSWORD. Try Again \n");
                        this.loginCorrect = false; // loginCorrect set to false if does not match
                    }
                }
                
            }
        }
    }
}

    // Creating Recipe Book Account 
    public void accountMethod(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Create Username");

        String username = userInput.nextLine();
        setUsername(username);
        createCSVFile(username); //creates a CSV file for the user

        Scanner passInput = new Scanner(System.in);
        System.out.println("Create Password");

        String password = userInput.nextLine();
        setPassword(password);

        //System.out.println(account);

        String loc = "src/accounts.csv"; // location of file we will be writing into

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
        
    }

    public void createCSVFile(String username){
        StringBuilder accFile = new StringBuilder(); 
        accFile.append("src/UserCookBooks/");
        accFile.append(username);
        accFile.append(".csv");
        String accountFileName = accFile.toString();
        setCSVFile(accountFileName);
    }
/*
    public RecipeCollection readUserCookbook(myCookbook){
        String f = CSVFilename;
        BufferedReader br = new BufferedReader(new FileReader(f)); //create a buffered reader object 
        String line = null; //initialize line variable

        br.readLine(); //reading the first line and ignoring it (headers)

        while((line = br.readLine()) != null){ //reads each line and records those that are not empty
            String[] attributes = line.split("\",\""); //splits the line by non-embedded commas (to get each attribute of the recipe object) and stores them in an ArrayList
            String recipeName = attributes[0]; //recipe name is the first attribute in each line
            //strips leading double quotes from recipe names
            if (recipeName.startsWith("\"")) {
                recipeName = recipeName.substring(1, recipeName.length());
             }
            String recipeIngredients = attributes[1]; //recipe ingredients are second in each line
            String[] parsedIngredients = recipeIngredients.split(","); //separate ingredients 
            ArrayList<Ingredient> listOfIngredients = new ArrayList<Ingredient>(); //new array list
            for (String i : parsedIngredients){
                String[] ingredientAttributes = i.split(":"); //separate ingredient names from amounts
                String ingredientName = ingredientAttributes[0];
                String ingredientMeasurement = ingredientAttributes[1]; 
                Ingredient ingredient = new Ingredient (ingredientName, ingredientMeasurement); //create new ingredient object using attributes
                listOfIngredients.add(ingredient); //add new ingredient to the array list
            }
            String recipeInstructions = attributes[2]; //recipe instructions are third in each line
            Recipe recipe = new Recipe (recipeName, listOfIngredients, recipeInstructions); //creates a new recipe using the attributes
            myCookbook.addRecipe(recipe); // adds the recipe to the recipe collection
        }
        br.close();
        return(myCookbook);
    }
*/
    public void saveToAccount(RecipeCollection collection){
        try {
            PrintWriter writer = new PrintWriter(new File (CSVFilename));
            writer.println("Recipe Name,Ingredients,Instructions"); // writes header to file
            for (Recipe r : collection.listOfRecipes){
                StringBuilder ingredientsList = new StringBuilder(); //new string containing the list of ingredients
                ingredientsList.append('"'); //starts with quote in csv file
                for (Ingredient i : r.ingredients){
                    StringBuilder ingredient = new StringBuilder(); 
                    ingredient.append(i.name);
                    ingredient.append(":");
                    ingredient.append(i.measurement);
                    ingredient.append(","); //need to change this: will add a comma after the last ingredient which won't work
                    ingredientsList.append(ingredient);
                }
                ingredientsList.append('"');
                writer.println('"' + r.name + '"' + "," + ingredientsList + "," + '"' + r.instructions + '"');
            }
            writer.flush(); //flush stream
            writer.close(); //close stream
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
