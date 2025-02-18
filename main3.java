import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int maxStudents;
    private List<String> prerequisites;
    private List<String> enrolledStudents;

    public Course(String name, int maxStudents, List<String> prerequisites) {
        this.name = name;
        this.maxStudents = maxStudents;
        this.prerequisites = prerequisites;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void enrollStudent(String studentName, List<String> completedCourses) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents.size() >= maxStudents) {
            throw new CourseFullException("Course " + name + " is full.");
        }
        for (String prereq : prerequisites) {
            if (!completedCourses.contains(prereq)) {
                throw new PrerequisiteNotMetException("Student has not completed prerequisite: " + prereq);
            }
        }
        enrolledStudents.add(studentName);
        System.out.println("Student " + studentName + " successfully enrolled in " + name);
    }
}

public class main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Math 101", 2, Arrays.asList("None")));
        courses.add(new Course("Physics 201", 2, Arrays.asList("Math 101")));

        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter completed courses (comma-separated): ");
        String[] completedCoursesArr = scanner.nextLine().split(",");
        List<String> completedCourses = Arrays.asList(completedCoursesArr);

        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getName());
        }

        System.out.print("Enter the number of the course you want to enroll in: ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine();

        if (courseChoice < 1 || courseChoice > courses.size()) {
            System.out.println("Invalid course selection.");
        } else {
            Course selectedCourse = courses.get(courseChoice - 1);
            try {
                selectedCourse.enrollStudent(studentName, completedCourses);
            } catch (CourseFullException | PrerequisiteNotMetException e) {
                System.out.println("Enrollment failed: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
