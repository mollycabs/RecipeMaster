package src; 
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
    
public class RecipeCollectionDriver {

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_RED = "\u001b[30m";
        public static final String ANSI_GREEN = "\u001b[32m";
    public static void main(String[] args) throws IOException {
        RecipeCollection myCookbook = new RecipeCollection(); //instantiate a personal cookbook
        RecipeCollection recipeDatabase = new RecipeCollection(); //instantiate a recipe collection for the database
        CSVReader reader = new CSVReader(); //create a CSV reader object
        recipeDatabase = reader.readFile(recipeDatabase); //read in file, sending in the empty collection to be filled and returning the collection with recipes added

        Scanner menuItemScan = new Scanner(System.in);
        Scanner selectRecipeScan = new Scanner(System.in);
        Scanner addRecipeScan = new Scanner(System.in);
        
        System.out.println(ANSI_BLUE + " \n Welcome to the Cookbook Creator! \n " + ANSI_RESET);        
        //creatingAccount(menuItemScan);
        Account a = new Account(null, null, null);
        a.creatingAccount(menuItemScan);
        String fileName = a.CSVFilename;
        File f = new File (fileName);
        if (f.exists()){
            myCookbook = a.readUserCookbook(myCookbook);
        } else {
            System.out.println(ANSI_GREEN + "\nAccount Created\n" + ANSI_RESET);
        }
        Boolean cont = true;
        //while loop for menu selection
        while (cont) {
            try {
                TimeUnit.SECONDS.sleep(1); //delayed print statements
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            System.out.println("1: View Existing Recipes\n2: View your cookbook\n3: Add existing recipe to your cookbook\n4: Add your own recipe to the database\n5: Save and Exit\n");
            System.out.println("Make your selection:");

            int menuItem = menuItemScan.nextInt(); // number of function to run​

            //switch statement for function selection
            switch (menuItem){
            case 1: //Prints recipes from recipe database
                recipeDatabase.printRecipeList();
                break;
            case 2: //Prints recipes from user's cookbook
                myCookbook.printRecipes();
                break;
            case 3:
                System.out.println("Which recipe would you like to add?");
                // Prints out recipes with numbers based on their indices 
                for (Recipe recipe : recipeDatabase.listOfRecipes){
                    int recipeNum = recipeDatabase.listOfRecipes.indexOf(recipe);
                    recipeNum = recipeNum + 1; //increase index by 1 (so that recipe numbers start at 1)
                    System.out.println(recipeNum + ": " + recipe.name);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println("Make your selection: ");
                int selectedRecipe = selectRecipeScan.nextInt(); //number of recipe corresponds to its index in the listOfRecipes
                int recipeIndex = selectedRecipe - 1; //return recipe number to actual index
                myCookbook.addRecipe(recipeDatabase.listOfRecipes.get(recipeIndex)); //adds recipe to user's cookbook
                break;
            case 4:
                recipeDatabase.addNewRecipe(recipeDatabase, addRecipeScan);
                break;
                   
            case 5:
                a.saveToAccount(myCookbook);
                recipeDatabase.saveCollection(recipeDatabase);
                cont = false;
                System.out.println("Save successful!");
                break;
            }
          
        } 
        menuItemScan.close();
        selectRecipeScan.close();
        addRecipeScan.close();
    }
}