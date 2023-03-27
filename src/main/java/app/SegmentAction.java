package app;

public class SegmentAction{
    // Attributes
    private ActionType type;

    
    // Constructors
    public SegmentAction(ActionType type){
        this.type = type;
    }


    // Enumerations
    public enum ActionType {
        PLAY_NOTE, 
        CHANGE_PROPERTY
    }


    // Methods
    public ActionType getType(){
        return this.type;
    }
}