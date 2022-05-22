package Model.Entities;

public class Person {

    private String name;
    private String phone;

    private String Email;
    private String Sex;

    public Person(String name, String phone, String Email, String Sex) {
        this.name = name;
        this.phone = phone;
        this.Email = Email;
        this.Sex = Sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return (Email);
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getSex() {
        return Sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean login(String userName, String password) {
        return false;
    }

}
