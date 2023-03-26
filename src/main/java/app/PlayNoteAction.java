package app;

public class PlayNoteAction extends SegmentAction{
    // Attributes
    public String NoteToPlay;


    // Constructors
    public PlayNoteAction(int characterAsciiCodeToPlay){
        super(SegmentAction.ActionType.PLAY_NOTE);
        this.NoteToPlay = new Character((char) characterAsciiCodeToPlay).toString();
    }
}
