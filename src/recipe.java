package src;
import java.util.ArrayList;

class Recipe{
    public String name;
    public ArrayList<Ingredient> ingredients;
    public String instructions;
    
    public Recipe(String n, ArrayList<Ingredient> ing, String ins){
        this.name = n;
        this.ingredients = ing;
        this.instructions = ins;
    }
    //getters and setters
    public String getRecipeName(){
        return name;
    }

    public void setRecipeName(String name){
        this.name = name;
    }

    public ArrayList<Ingredient> getRecipeIngredients(){
        return ingredients;
    }

    public void setRecipeIngredients(ArrayList<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    public String getRecipeInstructions(){
        return instructions;
    }

    public void setRecipeInstructions(String instructions){
        this.instructions = instructions;
    }
    
    //prints whole recipe
    public void printRecipe(Recipe recipe){
        System.out.println(name + "\nIngredients: ");
        for (Ingredient ingredient: ingredients){
            ingredient.printIngredient();
        }
        System.out.println("\nInstructions: \n" + instructions);
    }

    //prints formatted ingredients 
    public void printIngredients(Recipe recipe){
        for (Ingredient ingredient: recipe.ingredients){
            ingredient.printIngredient();
        }
        
    }

    


}