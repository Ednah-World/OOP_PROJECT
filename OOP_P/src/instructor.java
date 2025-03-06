public class instructor extends Person {
    private String name;
    private String email;
    private int staff_id;

    //Constructor to initialize instructor details
    public instructor (String name, String email, int staff_id) {
        super(name, email);
        this.staff_id = staff_id;
    }

    //override the display details method

    @Override
    public void displayDetails() {
        System.out.println("Instructor Name: "+ name + "Email: "+ email + "Staff ID: " + staff_id);
    }
}
