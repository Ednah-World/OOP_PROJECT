public class Course {
    private String course_id;
    private String course_name;
    private int credits;
    private String instructor;

    public Course (String course_id,String course_name,int credits, String instructor){
        this.course_id = course_id;
        this.course_name = course_name;
        this.credits = credits;
        this.instructor = instructor;
    }


    public Course() {

    }
}
