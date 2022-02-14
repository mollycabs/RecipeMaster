class Recipe{
    public String name;
    public String ingredients;
    public String instructions;
    
    public Recipe(String n, String ing, String ins){
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

    public String getRecipeIngredients(){
        return ingredients;
    }

    public void setRecipeIngredients(String ingredients){
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
        String [] arrayOfIngredients = ingredients.split(",");
        for (String ingredient: arrayOfIngredients){
            System.out.println(ingredient);
        }
        System.out.println("\nInstructions: \n" + instructions);
    }

    //prints formatted ingredients 
    public String printIngredients(Recipe recipe){
        String ingredients = recipe.ingredients;
        String [] arrayOfIngredients = ingredients.split(",");
        String formattedIngredients = "";

        for (String ingredient: arrayOfIngredients){
            formattedIngredients = formattedIngredients + (ingredient + "\n");
        }
        return(formattedIngredients);
    }

}