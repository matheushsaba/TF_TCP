/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package app;
import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;

import java.util.ArrayList;

public class TextToSoundConverter {

    public static void main(String[] args) throws Exception {
        // interface

        GraphicalInterface janela = new GraphicalInterface();
        janela.show();

        
        // leitor de arquivo
        /*
        TxtFileHandler fileHandler = new TxtFileHandler();
        String fileContent = "";
        try {
            fileContent = fileHandler.getStringFromTxtFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileContent);
        Pattern p4 = new Pattern(fileContent).setTempo(200);
        */

/*
        ArrayList<SegmentAction> actions = new ArrayList<>();
        String textToTest = "C cC!cCxcC3cC?cC;cC,cC";
        //String textToTest = "ABCD";
        //String textToTest = "A" + generateStringWithAllSymbols();

        for (int i = 0; i < textToTest.length(); i++) {
            SegmentAction action = TextToActionMapper.checkSegmentAction(textToTest, i);
            actions.add(action);
        }
        Music music = new Music(actions);

        // tocar música
        music.playText();*/

        /*
        Player player = new Player();
        //Pattern p1 = new Pattern("T120 I[Guitar_Fret_Noise] :Controller[7, 63] C").setTempo(130);
        Pattern p1 = new Pattern("T120 I[Guitar_Fret_Noise] :Controller(7, 63) C").setTempo(130);


        // exportar o arquivo pra midi

        // Cria um PatternHandler
        PatternHandler fullSound = new PatternHandler(100, 80, 0, 5);
        //Pattern musica = new Pattern();
        // A música é o resultado de todos os patterns juntos
        //musica = fullSound.convertArrayOfPatternsToPattern(patternsList);
        player.play(p1);
        // Salva o arquivo em .midi
        //fullSound.savePatternToMidiFile(musica);

        */


        //PatternHandler fullSound = new PatternHandler(100, 80, 0, 0);


        /*
        Player player = new Player();
        // Cria um array de patterns 
        
        //Pattern p2 = new Pattern("I[TROMBONE] X[Volume]=20 A Cq Cq Cq B A").setTempo(60);
        Pattern p1 = new Pattern("I[TRUMPET] C D# D C").setTempo(130);
        Pattern p2 = new Pattern("I[TRUMPET] C D#q D#q D#q D C").setTempo(130);
        Pattern p3 = new Pattern("I[TRUMPET] C D#q D#q D#q F F").setTempo(130);
        Pattern p4 = new Pattern("I[TRUMPET] F D# F D# F F").setTempo(130);
        Pattern p5 = new Pattern("I[TRUMPET] F F D# D# G G D# D# D D# C C").setTempo(130);
        
        ArrayList<Pattern> patternsList = new ArrayList<Pattern>();
        patternsList.add(p1);
        patternsList.add(p2);
        patternsList.add(p1);
        patternsList.add(p3);
        patternsList.add(p4);
        patternsList.add(p4);
        patternsList.add(p5);
        */


    }

    public static String generateStringWithAllNotes(){
        String allNotesString = "";
        for (int i = 65; i < 72; i++) {
            allNotesString = allNotesString + (char)i;
        }
        for (int i = 97; i < 104; i++) {
            allNotesString = allNotesString + (char)i;
        }

        return allNotesString;
    }
    public static String generateStringWithAllLetters(){
        String allLettersString = "";
        for (int i = 65; i < 91; i++) {
            allLettersString = allLettersString + (char)i;
        }
        for (int i = 97; i < 123; i++) {
            allLettersString = allLettersString + (char)i;
        }

        return allLettersString;
    }
    public static String generateStringWithAllNumbers(){
        String allNumbersString = "";
        for (int i = 48; i < 58; i++) {
            allNumbersString = allNumbersString + (char) i;
        }
        return allNumbersString;
    }
    public static String generateStringWithAllSymbols(){
        String allSymbolsString = "";
        // Primeiros caracteres da tabela ASCII sem o New Line
        for (int i = 0; i < 10 && i != 10; i++)
            allSymbolsString = allSymbolsString + (char) i;
/*
        for (int i = 11; i < 32; i++)
            allSymbolsString = allSymbolsString + (char) i;
*/
        // Símbolos sem o ! e o Space
        for (int i = 34; i < 44; i++)
            allSymbolsString = allSymbolsString + (char) i;

        allSymbolsString = allSymbolsString + (char) 45; // -
        allSymbolsString = allSymbolsString + (char) 47; // /
        allSymbolsString = allSymbolsString + (char) 58; // :

        // < = >
        for (int i = 60; i < 63; i++)
            allSymbolsString = allSymbolsString + (char) i;

        allSymbolsString = allSymbolsString + (char) 64; // @

        for (int i = 91; i < 97; i++)
            allSymbolsString = allSymbolsString + (char) i;

        for (int i = 123; i < 128; i++)
            allSymbolsString = allSymbolsString + (char) i;

            return allSymbolsString;
    }

}
