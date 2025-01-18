package org.andersen_project.util;

import java.io.*;
import java.util.List;

public class ProgramState {
    public static  <T> void saveState(List<T> listToSave, String fileName) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listToSave);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  <T> List<T> loadState(String fileName) {
        List<T> loadedList = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            loadedList = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loadedList;
    }
}
