import java.io.File;
import java.io.IOException;
import java.util.List;

public interface StorageStrategy {
    void save(List<Student> students, File file) throws IOException;
    List<Student> load(File file) throws IOException, ClassNotFoundException;
}