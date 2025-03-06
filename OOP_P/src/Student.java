public class Student extends Person{

    private String name;
    private String email;
    private int student_id;

    public Student(String name, String email, int student_id){
        super(name, email);
        this.student_id = student_id;
    }

    public void displayDetails() {
        System.out.println("Student Name: "+ name + "Email: "+ email + "Student ID: " + student_id);
    }

}
