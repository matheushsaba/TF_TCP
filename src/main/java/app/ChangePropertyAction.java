package app;

public class ChangePropertyAction extends SegmentAction {
    // Attributes
    public ActionOnProperty ActionOnProperty;
    public int PropertyNewValue;

    // Constructors
    public ChangePropertyAction(ActionOnProperty propertyToChange, int propertyNewValue) {
        super(ActionType.CHANGE_PROPERTY);
        this.ActionOnProperty = propertyToChange;
        this.PropertyNewValue = propertyNewValue;
    }

    // Enumerations
    public enum ActionOnProperty {
        ADD_VALUE_TO_INSTRUMENT,
        SET_VALUE_TO_INSTRUMENT, 
        RAISE_OCTAVE, 
        SET_VALUE_TO_BPM, 
        DOUBLE_VOLUME
    }
    
}
