package Employee;

import java.io.Serializable;

import Human.Human;

public class Employee extends Human implements Serializable, Comparable<Employee> {

    private static final long serialVersionUID = 1L;
    
    public Employee(String firstname, String name, String patronym, String birthdate, String sex, String dept, Float salary, String position){
        super(firstname, name, patronym, birthdate, sex);
        this.dept = dept;
        this.salary = salary;
        this.position = position;
    }

    public Employee(Human human, String dept, Float salary, String position){
        super(human.getFirstname(), human.getName(), human.getPatronym(), human.getBirthdate(), human.getSex());
        this.dept = dept;
        this.salary = salary;
        this.position = position;
    }

    private String dept;
    private Float salary;
    private String position;

    @Override
    public int compareTo(Employee o){
        return this.salary.compareTo(o.getSalary());
    };

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }


}
