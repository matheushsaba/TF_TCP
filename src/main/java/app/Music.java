package app;

import java.util.ArrayList;
import org.jfugue.pattern.Pattern;

public class Music {
    // Constants
    private final int defaultVolumeValue = 50;
    private final int defaultBPMValue = 120;
    private final int defaultOctaveValue = 5;

    // Properties
    private ArrayList<Pattern> sequentialPatterns;
    private PatternHandler actualPatternBuilder;


    // Constructor
    public Music(){
        this.sequentialPatterns = new ArrayList<Pattern>();
        this.actualPatternBuilder = new PatternHandler(defaultVolumeValue, defaultBPMValue, PatternHandler.Instrument.GUITAR, defaultOctaveValue);
    }


    // Methods
    public void playText(String rawText){

        for (int i = 0; i < rawText.length(); i++) {
            SegmentAction segmentAction = TextSegmentToActionMapper.checkSegmentAction(rawText, i);
        }
    }

   
    
    
}