package view;

import java.util.List;

import model.Teacher;

public class TeacherView {
    public void displayTeacherMenu(){
        System.out.println("|-------Teacher Menu-------|");
        System.out.println("1- add a teacher");
        System.out.println("2- update teacher");
        System.out.println("3- delete teacher");
        System.out.println("4- show teachers");
        System.out.println("5- quite");
    }
    public void displayTeachers(List<Teacher> teachers){
        for(Teacher t : teachers){
            System.out.println(t);
        }
    }
}
