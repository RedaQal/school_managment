package model;

import java.util.ArrayList;

public class School {
    private ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Student> gStudents(){
        return students;
    }
    public void addStudent(Student student){
        this.students.add(student);
    }
    public void deleteStudent(Student student){
        this.students.remove(student);
    }
}
