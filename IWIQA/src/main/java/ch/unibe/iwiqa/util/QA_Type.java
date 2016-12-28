package ch.unibe.iwiqa.util;
/**
 * Identifies the type of QA_Type this is. 
 * 
 * @author Marc Jost
 */
public enum QA_Type {
    BA("Bachelorarbeit", "BA"),
    MA("Masterarbeit", "MA");
    
    String unabbreviated;
    String abbreviated;
    
    QA_Type(String unabbreviated, String abbreviated){
        this.unabbreviated = unabbreviated;
        this.abbreviated = abbreviated;
    }
    
    public String getUnabbreviated(){
        return unabbreviated;
    }
    
    public String getAbbreviated(){
        return abbreviated;
    }
}
