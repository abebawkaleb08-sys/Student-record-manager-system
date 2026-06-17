import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {

        StudentRecordManager manager =
                new StudentRecordManager("students.ser",
                        new SerializationStorageStrategy());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(" MENU ");
            System.out.println("1. Add Student");
            System.out.println("2. Search");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Display All");
            System.out.println("6. Report");
            System.out.println("7. Backup");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Dept: ");
                    String dept = sc.nextLine();

                    double gpa;
                    try {
                        System.out.print("GPA: ");
                        gpa = Double.parseDouble(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid GPA");
                        break;
                    }

                    if (manager.addStudent(new Student(id, name, dept, gpa))) {
                        System.out.println("Added successfully");
                    } else {
                        System.out.println("ID already exists");
                    }
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    System.out.println(manager.searchStudent(sc.nextLine()));
                    break;

                case 3:
                    System.out.print("ID: ");
                    String uid = sc.nextLine();

                    Student existing = manager.searchStudent(uid);
                    if (existing == null) {
                        System.out.println("Not found");
                        break;
                    }

                    System.out.print("New Name: ");
                    String nn = sc.nextLine();

                    System.out.print("New Dept: ");
                    String nd = sc.nextLine();

                    double ngpa;
                    try {
                        System.out.print("New GPA: ");
                        ngpa = Double.parseDouble(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid GPA");
                        break;
                    }

                    manager.updateStudent(uid, nn, nd, ngpa);
                    System.out.println("Updated");
                    break;

                case 4:
                    System.out.print("ID: ");
                    System.out.println(manager.deleteStudent(sc.nextLine()) ? "Deleted" : "Not found");
                    break;

                case 5:
                    manager.displayAll();
                    break;

                case 6:
                    manager.report();
                    break;

                case 7:
                    manager.backup();
                    break;

                case 8:
                    System.out.println("Bye!");
                    return;
            }
        }
    }
}
