public abstract class Person {

    private String name;
    private String email;

    //Constructor to initialize name and email
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }


    // Abstract method for displaying details
    public abstract void displayDetails();
}
