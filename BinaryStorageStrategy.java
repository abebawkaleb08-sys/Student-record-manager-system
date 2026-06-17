import java.io.*;
import java.util.*;

public class BinaryStorageStrategy implements StorageStrategy {

    public void save(List<Student> students, File file) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(students.size());
            for (Student s : students) {
                dos.writeUTF(s.getId());
                dos.writeUTF(s.getName());
                dos.writeUTF(s.getDepartment());
                dos.writeDouble(s.getGpa());
            }
        }
    }

    public List<Student> load(File file) throws IOException {
        List<Student> list = new ArrayList<>();
        if (!file.exists() || file.length() == 0) return list;

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            int count = dis.readInt();
            for (int i = 0; i < count; i++) {
                list.add(new Student(
                        dis.readUTF(),
                        dis.readUTF(),
                        dis.readUTF(),
                        dis.readDouble()
                ));
            }
        }
        return list;
    }
}