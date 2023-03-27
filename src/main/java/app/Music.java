package app;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.util.Date;  
import java.text.SimpleDateFormat;

import org.jfugue.midi.MidiFileManager;

public class Music {
    // Constants
    private final int defaultVolumeValue = 50;
    private final int defaultBPMValue = 120;
    private final int defaultOctaveValue = 5;
    private final int defaultInstrument = 0;
    private final int defaultOctave = 5;


    // Atributtes
    private ArrayList<PatternHandler> sequentialPatternHandlers;
    private PatternHandler actualPatternBuilder;


    // Constructores
    public Music(ArrayList<SegmentAction> musicActions) throws Exception {
        this.sequentialPatternHandlers = initializePatternHandlerList();
        actualPatternBuilder = this.sequentialPatternHandlers.get(0);
        generatePatternHandlers(musicActions);
    }

    public Music() {
        sequentialPatternHandlers =  new ArrayList<>();
        actualPatternBuilder = null;
    }


    // Methods
    private ArrayList<PatternHandler> initializePatternHandlerList(){
        ArrayList<PatternHandler> patternHandlerList = new ArrayList<PatternHandler>();
        patternHandlerList.add(generateFirstPatternHandler());

        return patternHandlerList;
    }
    private PatternHandler generateFirstPatternHandler(){
        return new PatternHandler(defaultVolumeValue, defaultBPMValue, defaultInstrument, defaultOctave);
    }
    private void generatePatternHandlers(ArrayList<SegmentAction> musicSegmentActions) throws Exception {
        for (SegmentAction musicSegmentAction: musicSegmentActions)
            makeActionOnPattern(musicSegmentAction);
    }
    private void makeActionOnPattern(SegmentAction musicSegmentAction) throws Exception {
        if (musicSegmentAction.getType() == SegmentAction.ActionType.PLAY_NOTE)
            addNoteToActualPatternHandler(musicSegmentAction);
        else
            changePropertyOnPatternHandler(musicSegmentAction);
    }
    private void addNoteToActualPatternHandler(SegmentAction musicSegmentAction) throws Exception {
        PlaySoundAction playNoteAction = (PlaySoundAction)musicSegmentAction;
        this.actualPatternBuilder.addNoteToPattern(playNoteAction.getNoteToPlay());
    }
    private void changePropertyOnPatternHandler(SegmentAction musicSegmentAction) throws Exception {
        ChangePropertyAction changePropertyAction = (ChangePropertyAction)musicSegmentAction;
        PatternHandler newPatternHandler = createPatternWithNewProperty(changePropertyAction);
        this.sequentialPatternHandlers.add(newPatternHandler);
        this.actualPatternBuilder = this.sequentialPatternHandlers.get(this.sequentialPatternHandlers.size() - 1);
    }
    private PatternHandler createPatternWithNewProperty(ChangePropertyAction changePropertyAction) throws Exception {
        ChangePropertyAction.ActionOnProperty action = changePropertyAction.getActionOnProperty();
        int actionValue = changePropertyAction.getPropertyNewValue();

        switch (action){
            case SET_VALUE_TO_INSTRUMENT:
                return new PatternHandler(actualPatternBuilder.getVolume(), actualPatternBuilder.getBPM(), actionValue, actualPatternBuilder.getOctave());
            case RAISE_OCTAVE:
                return new PatternHandler(actualPatternBuilder.getVolume(), actualPatternBuilder.getBPM(), actualPatternBuilder.getInstrument(), actualPatternBuilder.getOctave() + actionValue);
            case DOUBLE_VOLUME:
                return new PatternHandler(actualPatternBuilder.getVolume() * 2, actualPatternBuilder.getBPM(), actualPatternBuilder.getInstrument(), actualPatternBuilder.getOctave());
            case SET_VALUE_TO_BPM:
                return new PatternHandler(actualPatternBuilder.getVolume(), actionValue, actualPatternBuilder.getInstrument(), actualPatternBuilder.getOctave());
            case ADD_VALUE_TO_INSTRUMENT:
                return new PatternHandler(actualPatternBuilder.getVolume(), actualPatternBuilder.getBPM(), actualPatternBuilder.getInstrument() + actionValue, actualPatternBuilder.getOctave());
            default:
                throw new Exception("Erro, parâmetros inválidos");
        }
    }

    public static Music generateMusicFromString(String musicString) {
        ArrayList<SegmentAction> actions = new ArrayList<SegmentAction>();
        
        for (int i = 0; i < musicString.length(); i++) {
            SegmentAction action = null;
            try {
                action = TextToActionMapper.checkSegmentAction(musicString, i);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            actions.add(action);
        }
        Music music = null;
        try {
            music = new Music(actions);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return music;
    }

    public void saveMusicToMidiFile() {
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("ddMyyyy-hh-mm-ss");  
        String stringDate = formatter.format(date);  

        Pattern joinedPatterns = combineSequentialPatternsIntoOne();
        final File arquivoMIDI = new File("convertedSound" + stringDate + ".mid");
        try {
            MidiFileManager.savePatternToMidi(joinedPatterns, arquivoMIDI);
        } catch (final IOException err) {
            err.printStackTrace();
        }
    }
    public Pattern combineSequentialPatternsIntoOne(){
        Pattern joinedPatterns = new Pattern();

        for (PatternHandler patternHandler: this.sequentialPatternHandlers) {
            Pattern pattern = patternHandler.generatePattern();
            joinedPatterns.add(pattern, 1);
        }

        return joinedPatterns;
    }
    public void playText() throws Exception {
        Pattern joinedPatterns = combineSequentialPatternsIntoOne();
        new Player().play(joinedPatterns);
    }

}