package src;
import java.util.ArrayList; 
import java.util.concurrent.TimeUnit;

class Recipe{
    public String name;
    public ArrayList<Ingredient> ingredients;
    public ArrayList<Instruction> instructions;
    
    public Recipe(String n, ArrayList<Ingredient> ing, ArrayList<Instruction> ins){
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

    public ArrayList<Instruction> getRecipeInstructions(){
        return instructions;
    }

    public void setRecipeInstructions(ArrayList<Instruction> instructions){
        this.instructions = instructions;
    }
    
    //prints whole recipe
    public void printRecipe(Recipe recipe){
        System.out.println(name + "\nIngredients: ");
        for (Ingredient ingredient: ingredients){
            ingredient.printIngredient();
        }
        System.out.println("\nInstructions: \n");
        for (Instruction s : instructions){
            s.printInstruction();
        }
    }

    //prints formatted ingredients 
    public void printIngredients(){
        for (Ingredient ingredient: ingredients){
            ingredient.printIngredient();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    


}