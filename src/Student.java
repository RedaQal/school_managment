

public class Student {
    private int id;
    private String name;
    private int age;
    public Student(int id,String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String toString(){
        return "Student : "+this.name+" age : "+this.age;
    }
}
