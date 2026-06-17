import java.io.*;
import java.util.*;

public class TextStorageStrategy implements StorageStrategy {

    public void save(List<Student> students, File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Student s : students) {
                writer.println(s.getId() + "," + s.getName() + "," +
                        s.getDepartment() + "," + s.getGpa());
            }
        }
    }

    public List<Student> load(File file) throws IOException {
        List<Student> list = new ArrayList<>();
        if (!file.exists()) return list;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] p = sc.nextLine().split(",");
                if (p.length == 4) {
                    list.add(new Student(p[0], p[1], p[2], Double.parseDouble(p[3])));
                }
            }
        }
        return list;
    }
}