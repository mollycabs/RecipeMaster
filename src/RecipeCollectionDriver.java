package src;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class RecipeCollectionDriver {
    public static void main(String[] args) {
        RecipeCollection myCookbook = new RecipeCollection(); //instantiate a personal cookbook
        RecipeCollection recipeDatabase = new RecipeCollection(); //read in the recipe database from the recipes.csv file
        CSVReader reader = new CSVReader(); //create a CSV reader object
        recipeDatabase = reader.readFile(recipeDatabase); //read in file, sending in the empty collection to be filled and returning the collection with recipes added

        Scanner scan = new Scanner(System.in);
        Scanner optionsScan = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);


        System.out.println("Welcome to the Cookbook Creator!");
        //while loop for menu selection

        // CREATE ACCOUNT OR LOGIN
        System.out.println("1: Create Account\n2: Login\n");
        System.out.println("Make Selection: ");
        Account a = new Account(); // new account
        
        int option = optionsScan.nextInt();
        switch(option){
            case 1: // creating an account
                a.accountMethod();
                break;
            case 2:
                // login validation goes here
                System.out.println("HELLOOOOOO");
        }

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1); //delayed print statements
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            System.out.println("1: View Existing Recipes\n2: View your cookbook\n3: Add a recipe to your cookbook\n4: Exit\n");
            System.out.println("Make your selection: ");
            int menuItem = scan.nextInt(); // number of function to run

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
                    recipe.printIngredients(recipe);
                }
                System.out.println("Make your selection: ");
                int selectedRecipe = scan3.nextInt(); //number of recipe corresponds to its index in the listOfRecipes
                int recipeIndex = selectedRecipe - 1; //return recipe number to actual index
                myCookbook.addRecipe(recipeDatabase.listOfRecipes.get(recipeIndex)); //adds recipe to user's cookbook
                break;
            case 4:
                break;

            } 
        }    
    }
}
