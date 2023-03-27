package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import app.ChangePropertyAction.ActionOnProperty;

public class TextToActionMapper {
    // Mapping definitions
    // Musical notes
    private static final HashSet<Character> NOTES_LIST = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G'));
    private static final HashSet<Character> NON_NOTE_VOWELS = new HashSet<>(Arrays.asList('I', 'O', 'U', 'i', 'o', 'u'));
    // Change instruments
    private static final char COMMAND_AGOGO = '!';
    private static final char COMMAND_TUBULAR_BELLS = '\n';
    private static final char COMMAND_PAN_FLUTE = ';';
    private static final char COMMAND_CHURCH_ORGAN = ',';
    // Sound commands
    private static final ArrayList<Character> COMMANDS_RAISE_OCTAVE = new ArrayList<>(Arrays.asList('?', '.'));
    private static final char COMMAND_DOUBLE_VOLUME = ' ';
    private static final char JFUGUE_SILENCE = 'R';

    // General instruments
    private static final int JFUGUE_HARPSICHORD_VALUE = 7;
    private static final int JFUGUE_AGOGO_VALUE = 114;
    private static final int JFUGUE_TUBULAR_BELLS_VALUE = 15;
    private static final int JFUGUE_PAN_FLUTE_VALUE = 76;
    private static final int JFUGUE_CHURCH_ORGAN_VALUE = 20;


    // Methods
    // Verifica a ação do segmento
    public static SegmentAction checkSegmentAction(String rawText, int characterIndex) throws Exception {
        Character character = rawText.charAt(characterIndex);

        if (isCharacterLetter(character))
            return getLetterAction(character, rawText, characterIndex);
        
        else if (isCharacterNumber(character))
            return new ChangePropertyAction(ActionOnProperty.ADD_VALUE_TO_INSTRUMENT, Character.getNumericValue(character));

        else
            return getSymbolAction(rawText, characterIndex);
    }


    // Conjunto de verificações caso o caractere seja uma letra
    private static boolean isCharacterLetter(Character character){
        boolean isUpperCaseLetter = (getCharacterAsciiCode(character) >= getCharacterAsciiCode('A') && getCharacterAsciiCode(character) <= getCharacterAsciiCode('Z'));
        boolean isLowerCaseLetter = (getCharacterAsciiCode(character) >= getCharacterAsciiCode('a') && getCharacterAsciiCode(character) <= getCharacterAsciiCode('z'));
        return  (isUpperCaseLetter || isLowerCaseLetter);
    }
    private static int getCharacterAsciiCode(Character character){
        int characterAsciiCode = (int)character;
        return characterAsciiCode;
    }
    private static SegmentAction getLetterAction(Character character, String rawText, int characterIndex) throws Exception {
        if (isCharacterUpperCaseNote(character))
            return new PlaySoundAction(getCharacterAsciiCode(character));

        else if (isCharacterNonNoteVowel(character))
            return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, JFUGUE_HARPSICHORD_VALUE);
        
        else
            return checkIfLastCharacterIsNote(rawText, characterIndex);
    }
    private static boolean isCharacterUpperCaseNote(Character character) {
        return NOTES_LIST.contains(character);
    }
    private static boolean isCharacterNonNoteVowel(Character character) {
        return NON_NOTE_VOWELS.contains(character);
    }
    private static SegmentAction checkIfLastCharacterIsNote(String rawText, int characterIndex) throws Exception {
        if (!isPreviousIndexPositive(characterIndex))
            throw new Exception("Erro, não há caractere anterior");

        return repeatLastNoteOrStaySilent(rawText,characterIndex);
    }
    public static boolean isPreviousIndexPositive(int characterIndex){
        return characterIndex - 1 >= 0;
    }
    private static SegmentAction repeatLastNoteOrStaySilent(String rawText, int characterIndex){
        Character previousCharacter = rawText.charAt(characterIndex -1);

        if (isCharacterUpperCaseNote(previousCharacter))
            return new PlaySoundAction(getCharacterAsciiCode(previousCharacter)); // Retorna a nota anterior
        else
            return new PlaySoundAction(JFUGUE_SILENCE); // Retorna silêncio
    }


    // Conjunto de verificações caso o caractere seja um número
    private static boolean isCharacterNumber(Character character) {
        return getCharacterAsciiCode(character) >= getCharacterAsciiCode('0') && getCharacterAsciiCode(character) <= getCharacterAsciiCode('9');
    }


    // Conjunto de verificações caso o caractere seja um símbolo
    private static SegmentAction getSymbolAction(String rawText, int characterIndex) throws Exception {
        Character character = rawText.charAt(characterIndex);

        switch (character){
            case COMMAND_AGOGO:
                return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, JFUGUE_AGOGO_VALUE);
            case COMMAND_TUBULAR_BELLS:
                return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, JFUGUE_TUBULAR_BELLS_VALUE);
            case COMMAND_PAN_FLUTE:
                return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, JFUGUE_PAN_FLUTE_VALUE);
            case COMMAND_CHURCH_ORGAN:
                return new ChangePropertyAction(ActionOnProperty.SET_VALUE_TO_INSTRUMENT, JFUGUE_CHURCH_ORGAN_VALUE);
            case COMMAND_DOUBLE_VOLUME:
                return new ChangePropertyAction(ActionOnProperty.DOUBLE_VOLUME, 1);
            default:
                if(isCharacterRaiseOctave(character))
                    return new ChangePropertyAction(ActionOnProperty.RAISE_OCTAVE, 1);
                else
                    return checkIfLastCharacterIsNote(rawText, characterIndex);
        }
    }
    private static boolean isCharacterRaiseOctave(Character character) {
        return COMMANDS_RAISE_OCTAVE.contains(character);
    }
}
