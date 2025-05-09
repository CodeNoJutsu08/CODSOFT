package practice.com;

import java.util.*;

// Class to represent a course
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getRemainingSlots() {
        return capacity - enrolledStudents.size();
    }

    // Register a student for the course
    public boolean registerStudent(Student student) {
        if (enrolledStudents.size() < capacity && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    // Remove a student from the course
    public boolean removeStudent(Student student) {
        return enrolledStudents.remove(student);
    }

    // Display course details
    public void displayCourseInfo() {
        System.out.println("Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Capacity: " + capacity + ", Enrolled: " + enrolledStudents.size() +
                ", Available: " + getRemainingSlots());
        System.out.println("-------------------------------------");
    }
}

// Class to represent a student
class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    // Register the student in a course
    public boolean registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            boolean success = course.registerStudent(this);
            if (success) {
                registeredCourses.add(course);
                return true;
            }
        }
        return false;
    }

    // Drop a registered course
    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.removeStudent(this);
            registeredCourses.remove(course);
            return true;
        }
        return false;
    }

    // Show all registered courses
    public void showRegisteredCourses() {
        System.out.println("\nRegistered Courses for " + name + ":");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered yet.");
        } else {
            for (Course c : registeredCourses) {
                System.out.println(c.getCourseCode() + " - " + c.getTitle());
            }
        }
    }
}

// Main class to manage course registration
public class CourseRegistrationSystem {
    private static Map<String, Course> courses = new HashMap<>();
    private static Map<String, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        preloadCourses(); // Load default courses

        System.out.println("===== Welcome to the Course Registration System =====");

        // Input student info
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(studentId, name);
        students.put(studentId, student);

        int choice;
        do {
            System.out.println("\n---- Menu ----");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = getIntInput();

            switch (choice) {
                case 1:
                    viewCourses();
                    break;
                case 2:
                    registerCourse(student);
                    break;
                case 3:
                    dropCourse(student);
                    break;
                case 4:
                    student.showRegisteredCourses();
                    break;
                case 0:
                    System.out.println("Exiting. Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

            // Short pause for readability
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                // Ignore interruption
            }

        } while (choice != 0);
    }

    // Load predefined courses
    private static void preloadCourses() {
        courses.put("CS101", new Course("CS101", "Intro to Programming", "Basic programming concepts.", 3, "Mon 10-12"));
        courses.put("MATH201", new Course("JAVA201", "Intro to Java", "Data types and Variables.", 2, "Wed 11-1"));
        courses.put("PHY150", new Course("SQL301", "SQL Fundamentals", "SQL advance.", 2, "Fri 2-4"));
    }

    // Display all courses
    private static void viewCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course c : courses.values()) {
            c.displayCourseInfo();
        }
    }

    // Allow student to register for a course
    private static void registerCourse(Student student) {
        System.out.print("Enter Course Code to Register: ");
        String code = scanner.nextLine().toUpperCase();
        Course course = courses.get(code);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        boolean success = student.registerCourse(course);
        if (success) {
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Registration failed (course full or already registered).");
        }
    }

    // Allow student to drop a course
    private static void dropCourse(Student student) {
        System.out.print("Enter Course Code to Drop: ");
        String code = scanner.nextLine().toUpperCase();
        Course course = courses.get(code);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        boolean success = student.dropCourse(course);
        if (success) {
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("You are not registered in this course.");
        }
    }

    // Custom method to safely get integer input
    private static int getIntInput() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
