package src;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RecipeCollection {
   
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
            System.out.println("Recipe Name: " + recipe.getRecipeName() + "\nIngredients:\n" + recipe.getRecipeIngredients() + "\nInstructions:\n" + recipe.getRecipeInstructions()+ "\n");
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
            System.out.println("Recipe Name: " + recipe.getRecipeName() + "\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public RecipeCollection () {
        this.listOfRecipes = new ArrayList<>();
    }

}
