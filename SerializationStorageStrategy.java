import java.io.*;
import java.util.*;

public class SerializationStorageStrategy implements StorageStrategy {

    public void save(List<Student> students, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(students);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Student> load(File file) throws IOException, ClassNotFoundException {
        if (!file.exists() || file.length() == 0) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Student>) ois.readObject();
        }
    }
}