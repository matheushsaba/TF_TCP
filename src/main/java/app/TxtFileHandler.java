package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;

import java.io.File;

public class TxtFileHandler {
    private String getFileNameViaFileExplorer() throws Exception{
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
        String fileName = "";
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file == null) {
                throw new Exception();
            }
            fileName = chooser.getSelectedFile().getAbsolutePath();
        }
        return fileName;
    }

    public String getStringFromTxtFile() throws IOException{
        String fileName = "";
        try {
            fileName = getFileNameViaFileExplorer();
        }catch(Exception e){
            e.printStackTrace();
        }
        Path filePath = Path.of(fileName);
        String fileContent = Files.readString(filePath);
        return fileContent;
    }
}
