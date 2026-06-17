import java.io.*;
import java.util.*;

public class StudentRecordManager {

    private static final String DIR = "data";
    private static final String BACKUP = "data/backup.dat";

    private File activeFile;
    private StorageStrategy storage;
    private List<Student> students;

    public StudentRecordManager(String fileName, StorageStrategy storage) {
        this.storage = storage;
        init(fileName);
        load();
    }

    private void init(String fileName) {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) dir.mkdirs();

            activeFile = new File(dir, fileName);
            if (!activeFile.exists()) activeFile.createNewFile();

        } catch (IOException e) {
            System.out.println("Init error: " + e.getMessage());
        }
    }

    private void load() {
        try {
            students = storage.load(activeFile);
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }

    private void save() {
        try {
            storage.save(students, activeFile);
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public boolean addStudent(Student s) {
        if (searchStudent(s.getId()) != null) return false;
        students.add(s);
        save();
        return true;
    }

    public Student searchStudent(String id) {
        return students.stream()
                .filter(s -> s.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public boolean updateStudent(String id, String name, String dept, double gpa) {
        Student s = searchStudent(id);
        if (s == null) return false;

        s.setName(name);
        s.setDepartment(dept);
        s.setGpa(gpa);
        save();
        return true;
    }

    public boolean deleteStudent(String id) {
        Student s = searchStudent(id);
        if (s == null) return false;

        students.remove(s);
        save();
        return true;
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        students.forEach(System.out::println);
    }

    public void report() {
        if (students.isEmpty()) {
            System.out.println("Empty database.");
            return;
        }

        double max = students.stream().mapToDouble(Student::getGpa).max().orElse(0);
        double min = students.stream().mapToDouble(Student::getGpa).min().orElse(0);
        double avg = students.stream().mapToDouble(Student::getGpa).average().orElse(0);

        System.out.println("\n===== REPORT =====");
        System.out.println("Total: " + students.size());
        System.out.println("Max GPA: " + max);
        System.out.println("Min GPA: " + min);
        System.out.println("Avg GPA: " + avg);
    }

    public void backup() {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(activeFile));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(BACKUP))) {

            byte[] buffer = new byte[1024];
            int read;

            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            System.out.println("Backup created.");
        } catch (IOException e) {
            System.out.println("Backup error: " + e.getMessage());
        }
    }
}