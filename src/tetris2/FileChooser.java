/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author minjikim
 */
public class FileChooser extends JFrame{
    
    //private final JTextArea outputArea;
    
    public Path savedFile;
    
    public FileChooser() throws IOException{
        //super("Select Save File");
        //outputArea = new JTextArea();
        //add(new JScrollPane(outputArea));
        analyzePath();
    }
    
    public void analyzePath() throws IOException{
        Path path = getFileOrDirectoryPath();
        
        if(path!=null&&Files.exists(path)){
            //Path s = path.getFileName();
            //System.out.println(String.format("%s", path));
            savedFile=path;
            
            
            
            /*
            //Where FileChooser works
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("%s:%n", path.getFileName()));
            builder.append(String.format("%s a directory%n", Files.isDirectory(path) ? "Is" : "Is not"));
            builder.append(String.format("%s an absolute path%n", path.isAbsolute() ? "Is" : "Is not"));
            builder.append(String.format("Last modified: %s%n", Files.getLastModifiedTime(path)));
            builder.append(String.format("Size: %s%n", Files.size(path)));
            builder.append(String.format("Path: %s%n", path));
            builder.append(String.format("Absolute path: %s%n", path.toAbsolutePath()));
            if(Files.isDirectory(path)){
                builder.append(String.format("%nDirectory contents:%n"));
                
                DirectoryStream<Path> directoryStream= Files.newDirectoryStream(path);
                
                for(Path p: directoryStream){
                    builder.append(String.format("%s%n", p));
                }
            }
            
            outputArea.setText(builder.toString());
            */
            
            
        }else{
            JOptionPane.showMessageDialog(this, path.getFileName()+" does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
            
    }
    
    private Path getFileOrDirectoryPath(){
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        
        if(result==JFileChooser.CANCEL_OPTION)System.exit(1);
        
        return fileChooser.getSelectedFile().toPath();
    }
}
