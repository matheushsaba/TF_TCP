package app;

public class SegmentAction{
    // Attributes
    public ActionType Type;

    
    // Constructors
    public SegmentAction(ActionType type){
        this.Type = type;
    }


    // Enumerations
    public enum ActionType {
        PLAY_NOTE, 
        CHANGE_PROPERTY
    }
}