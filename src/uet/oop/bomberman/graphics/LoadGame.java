package uet.oop.bomberman.graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException; 
public class LoadGame {
    private File file = new File("res/save/save.txt");
    

    public File getFile() {
        return this.file;
    }

    public void writeFile(LoadGame load,FileOutputStream fout, String a) throws IOException{
        String s = a + "\n";
        fout.write(s.getBytes());
        //fileOutputStream.close();
    }
    
    public void writeFile(LoadGame load, FileOutputStream fout, int a) throws IOException{
        String s = Integer.toString(a);
        s += "\n";
        fout.write(s.getBytes());
        //fileOutputStream.close();
    }

    public void writeFile(LoadGame load, FileOutputStream fout, int a, int b) throws IOException{
        String s = Integer.toString(a) + " " + Integer.toString(b);
        s += "\n";
        fout.write(s.getBytes());
        //fileOutputStream.close();
    }
    
}
