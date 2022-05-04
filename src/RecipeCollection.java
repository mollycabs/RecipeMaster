package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RecipeCollection {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001b[30m";
    public static final String ANSI_GREEN = "\u001b[32m";
   
    public ArrayList<Recipe> listOfRecipes = new ArrayList<>(); // Recipe collection class has one attribute; an arrayList called listOfRecipes

    //getters and setters

    public ArrayList<Recipe> getListOfRecipes(){
        return listOfRecipes;
    }

    public void setListOfRecipes(ArrayList<Recipe> listOfRecipes){
        this.listOfRecipes = listOfRecipes;
    }

    public void addRecipe(Recipe recipe){
        listOfRecipes.add(recipe);
    }

    //print all recipes
    public void printRecipes(){
        for (Recipe recipe : listOfRecipes){
            System.out.println(ANSI_BLUE + "\nRecipe Name: " + ANSI_RESET + recipe.getRecipeName());
            System.out.println(ANSI_GREEN + "\nIngredients:\n" + ANSI_RESET);
            recipe.printIngredients(); 
            System.out.println(ANSI_GREEN + "\nInstructions:\n" + ANSI_RESET);
            recipe.printInstructions();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    //prints recipes without instructions to keep things clean (used when user views the recipe database)
    public void printRecipeList(){
        for (Recipe recipe : listOfRecipes){
            System.out.println(ANSI_BLUE + "\nRecipe Name: " + ANSI_RESET + recipe.getRecipeName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public RecipeCollection addNewRecipe(RecipeCollection recipeDatabase, Scanner addRecipeScan){
        System.out.println("Enter the name of the recipe you would like to add: ");
        String recipeName = addRecipeScan.nextLine();
        System.out.println("Time to add the ingredients!");
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
        System.out.println("Enter the name of the first ingredient: ");
        String ingredientName = addRecipeScan.nextLine();
        System.out.println("Enter the ingredient measurement: ");
        String ingredientAmount = addRecipeScan.nextLine();
        Ingredient i = new Ingredient(ingredientName,ingredientAmount);
        ingredientList.add(i);
        String loop = "y";
        while (true){
            System.out.println("Enter the name of the next ingredient: ");
            String ingName = addRecipeScan.nextLine();
            System.out.println("Enter the ingredient measurement: ");
            String ingAmount = addRecipeScan.nextLine();
            Ingredient ing = new Ingredient(ingName,ingAmount);
            ingredientList.add(ing);
            System.out.println("Would you like to add another ingredient? (y/n) ");
            loop = addRecipeScan.nextLine();
            loop.strip();
            System.out.println(loop);
            if (loop.equals("n")){
                break;
            }
        }
        System.out.println("Time to add instructions!");
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        System.out.println("Enter the first step: ");
        String stepOne = addRecipeScan.nextLine();
        Instruction ins = new Instruction("1", stepOne);
        instructions.add(ins);
        Boolean c = true;
        int x = 2;
        while (c.equals(true)){
            System.out.println("Enter the next step: ");
            String s = addRecipeScan.nextLine();
            String xx = String.valueOf(x);
            Instruction in = new Instruction(xx,s);
            instructions.add(in);
            x++;
            System.out.println("Would you like to add another step? (y/n) ");
            String con = addRecipeScan.nextLine();
            if (con.equals("y")){
                c = true;
            }else {
                Recipe r = new Recipe(recipeName, ingredientList, instructions);
                recipeDatabase.addRecipe(r);
                System.out.println("Recipe has been added!");
                break;
            }
        }
        return (recipeDatabase);
    }

    public void saveCollection(RecipeCollection recipeDatabase){
        try {
            PrintWriter writer = new PrintWriter(new File ("src/Recipes.csv"));
            writer.println("Recipe Name,Ingredients,Instructions"); // writes header to file
            for (Recipe r : recipeDatabase.listOfRecipes){
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
                StringBuilder instructionsList = new StringBuilder();
                for (Instruction s : r.instructions){
                    instructionsList.append(s.stepNum);
                    instructionsList.append(": ");
                    instructionsList.append(s.instructionText);
                    instructionsList.append(";");
                }
                String il = instructionsList.toString();
                il = il.replaceAll(";$","");
                il = il.replaceAll("\"$","");
                writer.println('"' + r.name + '"' + "," + ingredientsList + "," + '"' + il + '"');
            }
            writer.flush(); //flush stream
            writer.close(); //close stream
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public RecipeCollection () {
        this.listOfRecipes = new ArrayList<>();
    }
    

}
