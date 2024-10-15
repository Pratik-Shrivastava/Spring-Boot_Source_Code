package JDBC;

public class Student {
    private int id;
    private String name;
    private int age;
    private double marks;


    public int getId() {
        return id;
    }

    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "{"  + this.id + ", " + this.name  + ", "
                + this.age  + ", " + this.marks + "}";
    }

}
