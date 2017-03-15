package ch.unibe.iwiqa.util;

/**
 *
 * @author Marc Jost
 */
public enum QA_Status {
    QA_ABORTED(0), PROPOSAL_IN_PROGRESS(1), PROPOSAL_ACCEPTED(2), QA_HANDED_IN(3), QA_GRADED(4), QA_COMPLETED(5);
    
    private final int value;
    
    QA_Status(int value){
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
    }
}
