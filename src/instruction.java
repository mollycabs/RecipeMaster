package src;
class Instruction {
    public String stepNum;
    public String instructionText;

    public Instruction(String n, String it){
        this.stepNum = n;
        this.instructionText = it;
    }
    //getters and setters   
    public String getStepNum(){
        return stepNum;
    }

    public void setStepNum(String n){
        this.stepNum = n;
    }

    public String getInstructionText(){
        return instructionText;
    }

    public void setInstructionText(String it){
        this.instructionText = it;
    }
    //print instruction
    public void printInstruction(){
        System.out.println(stepNum + ": " + instructionText);
    }
}
