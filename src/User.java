public class User {
    private final int id;
    private String name;
    private String surname;
    private int age;
    private int role;

    public User(int i){
        id = i;
    }

    public User(int id, String name, String surname, int age, int role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.role = role;
    }

    public int getId(){
        return id;
    }

    public int getAge(){
        return age;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

