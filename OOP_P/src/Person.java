public abstract class Person {

    private String name;
    private String email;
    private int PhoneNumber;

    //Constructor to initialize name and email
    public Person(String name, String email, int PhoneNumber) {
        this.name = name;
        this.email = email;
        this.PhoneNumber = PhoneNumber;
    }


    // Abstract method for displaying details
    public abstract void displayDetails();
}
