package model;

import java.util.ArrayList;
import java.util.List;

public class School {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    public List<Student> getStudents(){
        return students;
    }
    public void addStudent(Student student){
        this.students.add(student);
    }
    public void deleteStudent(Student student){
        this.students.remove(student);
    }
    public List<Teacher> getTeachers(){
        return teachers;
    }
    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
    }
    public void deleteTeacher(Teacher teacher){
        this.teachers.remove(teacher);
    }
}
