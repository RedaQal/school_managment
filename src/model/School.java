package model;

import java.util.ArrayList;
import java.util.List;

public class School {
    private List<Student> students = new ArrayList<>();
    public List<Student> getStudents(){
        return students;
    }
    public void addStudent(Student student){
        this.students.add(student);
    }
    public void deleteStudent(Student student){
        this.students.remove(student);
    }
}
