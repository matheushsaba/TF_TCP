package tests;
import org.jfugue.pattern.Pattern;
import org.junit.*;
import static org.junit.Assert.*;
import app.PatternHandler;

public class PatternHandlerTest {
    private static PatternHandler handler;

    @BeforeClass
    public static void init(){
        handler = new PatternHandler(50, 120, 0, 5);
    }
    @Test
    public void addNoteToPatternTest() throws Exception {
        handler.addNoteToPattern("C");
        handler.addNoteToPattern("D");
        handler.addNoteToPattern("E");

        Pattern pattern = handler.generatePattern();
        assertEquals(pattern.toString(), "T120 I[Piano] :Controller(7, 63) C5, D5, E5");
    }
    @Test
    public void attributesTest(){
        assertEquals(handler.getInstrument(), 0);
        assertEquals(handler.getVolume(), 50);
        assertEquals(handler.getBPM(), 120);
        assertEquals(handler.getOctave(), 5);
    }
    @AfterClass
    public static void close(){
        System.out.println("Testes terminando");
    }
}