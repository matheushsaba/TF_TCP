package test;
import org.junit.*;
import static org.junit.Assert.*;
import app.PatternHandler;

public class PatternHandlerTest {
    private static PatternHandler handler;

    @Before
    public static void init(){
        handler = new PatternHandler();
    }
    @Test
    public void addNoteToPatternTest(){

    }
    public void addNoteToPattern(String note) throws Exception{
        if(!acceptedNotes.contains(note))
            throw new Exception("Note not accepted");

        notes.add(note);
    }
}

