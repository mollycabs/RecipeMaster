package src;
class Ingredient{
    public String name;
    public String measurement;

    public Ingredient(String n, String m){
        this.name = n;
        this.measurement = m; 
    }
    //getters and setters
    public String getIngredientName(){
        return name;
    }

    public void setIngredientName(String name){
        this.name = name;
    }

    public String getIngredientMeasurement(){
        return measurement;
    }

    public void setIngredientMeasurement(String measurement){
        this.measurement = measurement;
    }

    public void printIngredient(){
        System.out.println(name + ": " + measurement +"\n");
    }
}