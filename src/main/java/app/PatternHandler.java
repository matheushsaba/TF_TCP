package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

public class PatternHandler {
    // Attributes
    private static HashSet<String> acceptedNotes = new HashSet<String>(){
        {
            add("A");
            add("B");
            add("C");
            add("D");
            add("E");
            add("F");
            add("G");
        }
    };

    private ArrayList<String> notes;
    private int volume;
    private int BPM;
    private Instrument instrument;
    private int octave;


    // Constants
    private final String ControllerCode = ":Controller";
    private final String CoarseVolumeCode = "7";

    private final int maximumVolumeValue = 100;
    private final int defaultVolumeValue = 50;
    private final int minimumVolumeValue = 0;

    private final int defaultBPMValue = 120;
    private final int minimumBPMValue = 0;

    private final int maximumOctaveValue = 10;
    private final int defaultOctaveValue = 5;
    private final int minimumOctaveValue = 1;


    // Enumerations
    public enum Instrument{
        PIANO(0),
        XYLOPHONE(13),
        HARMONICA(22),
        GUITAR(24),
        SLAP_BASS_1(36),
        VIOLIN(40),
        CHOIR_AAHS(52),
        TUBA(58),
        CLARINET(71),
        FLUTE(73),
        SQUARE(80),
        CHOIR(91),
        ECHOES(102),
        BANJO(105),
        AGOGO(113),
        TELEPHONE_RING(124);

        private final int id;
        Instrument(int id) { this.id = id; }
        public int getValue() { return id; }
    }

    // Constructors
    public PatternHandler(int volumePercentage, int BPM, Instrument instrument, int octave) {
        this.notes = new ArrayList<String>();
        this.volume = setVolume(volumePercentage);
        this.BPM = setBPM(BPM);
        this.instrument = instrument;
        this.octave = setOctave(octave);
    }


    // Methods
    public void addNoteToPattern(String note) throws Exception{
        if(!acceptedNotes.contains(note))
            throw new Exception("Note not accepted");
            
        notes.add(note);
    }
    private int setVolume(int volumePercentage){
        volumePercentage = setToDefaultIfAboveMaximum(volumePercentage, maximumVolumeValue, defaultVolumeValue);
        volumePercentage = setToZeroIfBelowMinimum(volumePercentage, minimumVolumeValue);

        return volumePercentage;
    }
    private int setBPM(int BPM){
        BPM = setToZeroIfBelowMinimum(BPM, minimumBPMValue);

        return BPM;
    }
    private int setOctave(int octave){
        octave = setToDefaultIfAboveMaximum(octave, maximumOctaveValue, defaultOctaveValue);
        octave = setToZeroIfBelowMinimum(octave, minimumOctaveValue);

        return octave;
    }
    private int setToDefaultIfAboveMaximum(int value, int max, int defaultValue){
        if(value > max)
            return defaultValue;

        return value;
    }
    private int setToZeroIfBelowMinimum(int value, int min){
        if(value < min)
            return 0;

        return value;
    }

    public Pattern generatePattern(){
        String volumeCode = convertFromPercentageToFineVolumeCode(this.volume);
        setOctaveOnNotes();
        Pattern pattern = new Pattern(volumeCode + notes.toString());
        pattern.setTempo(this.BPM);
        pattern.setInstrument(this.instrument.getValue());

        return pattern;
    }
    private String convertFromPercentageToFineVolumeCode(int percentage){
        int fineVolume = (percentage * 127) / 100;
        String volumeCode = ControllerCode + "[" + CoarseVolumeCode + ", " + fineVolume + "] ";

        return volumeCode;
    }
    private void setOctaveOnNotes(){
        for (String note : this.notes) {
            note = note + this.octave;
        }
    }

    public Pattern convertArrayOfPatternsToPattern(ArrayList<Pattern> arrayOfPatterns){
        Pattern fullSound = new Pattern();
        for(int i=0; i<arrayOfPatterns.size(); i++)
            fullSound.add(arrayOfPatterns.get(i), 1);
        return fullSound;       
    }

    public void savePatternToMidiFile(Pattern fullSound){
        final File arquivoMIDI = new File("convertedSound.MIDI");
        try {
            MidiFileManager.savePatternToMidi(fullSound, arquivoMIDI);
        } catch (final IOException err) {
            err.printStackTrace();
        }        
    }
}