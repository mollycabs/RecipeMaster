package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 

//CSVReader reads in a csv file and stores each line as a Recipe object and adds each recipe to the recipeDatabase arrayList

public class CSVReader{

    public RecipeCollection readFile(RecipeCollection recipeDatabase){
        //String path = "src\\Recipes.csv";
        String path = "src/Recipes.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path)); //create a buffered reader object 
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
                recipeDatabase.addRecipe(recipe); // adds the recipe to the recipe collection
            }
            br.close();
            return(recipeDatabase);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return(recipeDatabase); //returns the csv file info in the form of a recipeCollection object
    }
    
    public CSVReader() {
        
    }
    }