package app;

public class PlaySoundAction extends SegmentAction{
    // Attributes
    private String noteToPlay;


    // Constructors
    public PlaySoundAction(int characterAsciiCodeToPlay){
        super(SegmentAction.ActionType.PLAY_NOTE);
        this.noteToPlay = new Character((char) characterAsciiCodeToPlay).toString();
    }


    // Methods
    public String getNoteToPlay(){
        return this.noteToPlay;
    }
}
