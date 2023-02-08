package Human;

import java.io.Serializable;

public class Human implements Serializable {
    private String name;

    private static final long serialVersionUID = 1L;

    public Human(String firstname, String name, String patronym, String birthdate, String sex){
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.name = name;
        this.patronym = patronym;
        this.sex = sex;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String firstname;
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String patronym;

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    private String birthdate;

    
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
