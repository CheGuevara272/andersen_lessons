package andersen_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {
    private final String directory;

    public CustomClassLoader(String directory) {
        this.directory = directory;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            try {
                loadedClass = super.loadClass(name, false);
            } catch (ClassNotFoundException e) {
                loadedClass = load(name);
            }
        }
        if (resolve) {
            resolveClass(loadedClass);
        }
        return loadedClass;
    }

    private Class<?> load(String name) throws ClassNotFoundException {
        String filePath = directory + File.separator + name.replace('.', File.separatorChar) + ".class";

        byte[] classBytes;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            classBytes = fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new ClassNotFoundException("Unable to load class " + name + " from " + directory);
        }

        return defineClass(name, classBytes, 0, classBytes.length);
    }
}
