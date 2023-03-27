package test;

import app.*;
import app.SegmentAction;
import org.junit.*;
import static org.junit.Assert.*;
import app.TextToActionMapper;

import app.TextToActionMapper;
public class TextToActionMapperTest {
    private static TextToActionMapper actionMapper;
    @BeforeClass
    public static void start(){
        System.out.println("Testes começando");
    }
    @BeforeClass
    public static void init(){
        actionMapper = new TextToActionMapper();
    }
    @Test
    public void isCharacterLetterTest(){
        assertFalse(TextToActionMapper.isPreviousIndexPositive(0));
        assertTrue(TextToActionMapper.isPreviousIndexPositive(10));
    }

    @Test
    public void checkSegmentActionTest() throws Exception {
        assertEquals(TextToActionMapper.checkSegmentAction("AAABBC", 0).Type, SegmentAction.ActionType.PLAY_NOTE);      // toca nota
        assertEquals(TextToActionMapper.checkSegmentAction("Ac", 1).Type, SegmentAction.ActionType.PLAY_NOTE);          // repete nota
        assertEquals(TextToActionMapper.checkSegmentAction("Kc", 1).Type, SegmentAction.ActionType.PLAY_NOTE);          // silêncio
        assertEquals(TextToActionMapper.checkSegmentAction("a 1", 1).Type, SegmentAction.ActionType.CHANGE_PROPERTY);   // dobra o volume
        assertEquals(TextToActionMapper.checkSegmentAction("!a", 0).Type, SegmentAction.ActionType.CHANGE_PROPERTY);    // muda para Agogo
        assertEquals(TextToActionMapper.checkSegmentAction("oi", 1).Type, SegmentAction.ActionType.CHANGE_PROPERTY);    // muda para harpsichord
        assertEquals(TextToActionMapper.checkSegmentAction("Az", 1).Type, SegmentAction.ActionType.PLAY_NOTE);           // repete nota
        assertEquals(TextToActionMapper.checkSegmentAction("pz", 1).Type, SegmentAction.ActionType.PLAY_NOTE);           // silencio
        assertEquals(TextToActionMapper.checkSegmentAction("29", 1).Type, SegmentAction.ActionType.CHANGE_PROPERTY);     // aumenta oitava
        assertEquals(TextToActionMapper.checkSegmentAction("?", 0).Type, SegmentAction.ActionType.CHANGE_PROPERTY);     // aumenta oitava
        assertEquals(TextToActionMapper.checkSegmentAction("\n", 0).Type, SegmentAction.ActionType.CHANGE_PROPERTY);    // muda instrumento
        assertEquals(TextToActionMapper.checkSegmentAction(";", 0).Type, SegmentAction.ActionType.CHANGE_PROPERTY);     // muda instrumento
        assertEquals(TextToActionMapper.checkSegmentAction(",", 0).Type, SegmentAction.ActionType.CHANGE_PROPERTY);     // muda instrumento
        assertEquals(TextToActionMapper.checkSegmentAction("Aj", 1).Type, SegmentAction.ActionType.PLAY_NOTE);          // repete nota
        assertEquals(TextToActionMapper.checkSegmentAction("qj", 1).Type, SegmentAction.ActionType.PLAY_NOTE);          // silencio
    }
    @AfterClass
    public static void close(){
        System.out.println("Testes terminando");
    }
}