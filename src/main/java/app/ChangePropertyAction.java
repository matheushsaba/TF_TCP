package app;

public class ChangePropertyAction extends SegmentAction {
    // Attributes
    private ActionOnProperty actionOnProperty;
    private int propertyNewValue;


    // Constructors
    public ChangePropertyAction(ActionOnProperty propertyToChange, int propertyNewValue) {
        super(ActionType.CHANGE_PROPERTY);
        this.actionOnProperty = propertyToChange;
        this.propertyNewValue = propertyNewValue;
    }


    // Enumerations
    public enum ActionOnProperty {
        ADD_VALUE_TO_INSTRUMENT,
        SET_VALUE_TO_INSTRUMENT, 
        RAISE_OCTAVE, 
        SET_VALUE_TO_BPM, 
        DOUBLE_VOLUME
    }


    // Methods
    public ChangePropertyAction.ActionOnProperty getActionOnProperty() {
        return this.actionOnProperty;
    }
    public int getPropertyNewValue(){
        return this.propertyNewValue;
    }
}
