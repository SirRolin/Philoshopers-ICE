package ICE.util;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class FileIO {
    public static String defaultPath = "Data/";
    public static List<String> readTextFile(String path){
        try {
            return Files.readAllLines(Paths.get(defaultPath + path));
        } catch (IOException e) {
            ErrorHandler.handleError(e);
        }
        return null;
    }
    public static <T> @Nullable T readSerialised(String path){

        try {
            FileInputStream fis = new FileInputStream(defaultPath + path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            final T t = (T) ois.readObject();
            return t;
        } catch (IOException | ClassNotFoundException e) {
            ErrorHandler.handleError(e);
        }
        return null;
    }
    public static boolean writeSerialised(Object obj, String path){
        try {
            File file = new File(defaultPath + path);
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            return true;
        } catch (IOException e) {
            ErrorHandler.handleError(e);
            return false;
        }
    }
}
