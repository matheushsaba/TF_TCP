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
            add("R");
        }
    };

    private ArrayList<String> notes;
    private int volume;
    private int BPM;
    private int instrument;
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

    // Constructors
    public PatternHandler(int volumePercentage, int BPM, int instrumentCode, int octave) {
        this.notes = new ArrayList<String>();
        this.volume = setVolume(volumePercentage);
        this.BPM = setBPM(BPM);
        this.instrument = setInstrument(instrumentCode);
        this.octave = setOctave(octave);
    }
    
    /*
    public PatternHandler() {
        this.notes = new ArrayList<String>();
        this.volume = setVolume(defaultVolumeValue);
        this.BPM = setBPM(BPM);
        this.instrument = setInstrument(instrument);
        this.octave = setOctave(octave);
    }*/


    // Methods
    public void addNoteToPattern(String note) throws Exception{
        if(!acceptedNotes.contains(note))
            throw new Exception("Note not accepted");
            
        notes.add(note);
    }
    public int getVolume(){
        return this.volume;
    }
    private int setVolume(int volumePercentage){
        volumePercentage = setToDefaultIfAboveMaximum(volumePercentage, maximumVolumeValue, defaultVolumeValue);
        volumePercentage = setToZeroIfBelowMinimum(volumePercentage, minimumVolumeValue);

        return volumePercentage;
    }
    public int getBPM(){
        return this.BPM;
    }
    private int setBPM(int BPM){
        BPM = setToZeroIfBelowMinimum(BPM, minimumBPMValue);

        return BPM;
    }
    public int getInstrument(){
        return this.instrument;
    }
    private int setInstrument(int instrumentCode){
        instrument = setToDefaultIfAboveMaximum(instrumentCode, 127, 0);

        return instrument;
    }
    public int getOctave(){
        return  this.octave;
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
        //setSpaceOnNotes();
        Pattern pattern = new Pattern(volumeCode + getNotesString());
        pattern.setTempo(this.BPM);
        pattern.setInstrument(this.instrument);

        return pattern;
    }
    private String convertFromPercentageToFineVolumeCode(int percentage){
        int fineVolume = (percentage * 127) / 100;
        String volumeCode = ControllerCode + "(" + CoarseVolumeCode + ", " + fineVolume + ") ";

        return volumeCode;
    }
    private String getNotesString(){
        String notesString = notes.toString();
        String notesSubstring = notesString.substring(1, notesString.length() - 1);

        return notesSubstring;
    }
    private void setOctaveOnNotes(){
        for (int i = 0; i < this.notes.size(); i++)
            addSymbolToNote(i, Integer.toString(this.octave));
    }
    /*
    private void setSpaceOnNotes(){
        for (int i = 0; i < this.notes.size(); i++)
            addSymbolToNote(i, " ");
    }*/
    private void addSymbolToNote(int noteIndex, String symbolToAdd){
        String note = this.notes.get(noteIndex);
        String newNote = note + symbolToAdd;
        this.notes.set(noteIndex, newNote);
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