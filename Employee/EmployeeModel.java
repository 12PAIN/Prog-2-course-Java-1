package Employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EmployeeModel {
    
    public ArrayList<Employee> EmployeeList;

    public void initiateEmployeeList(){
        if(EmployeeList == null) EmployeeList = new ArrayList<>();
    }

    public Employee Hire(String firstname, String name, String patronym, String birthdate, String sex, String dept, Float salary, String position){
        initiateEmployeeList();
        Employee newEmployee = new Employee(firstname, name, patronym, birthdate, sex, dept, salary, position);
        EmployeeList.add(newEmployee);
        return newEmployee;
    }

    public Employee selectEmployee(String firstname, String name, String patronym, String birthdate, String sex){

        Employee employee = null;

        for(Employee emp: EmployeeList){
            if(emp.getFirstname().equals(firstname) && emp.getName().equals(name) && emp.getPatronym().equals(patronym) && emp.getBirthdate().equals(birthdate) && emp.getSex().equals(sex)){
                employee = emp;
                break;
            }
        }

        return employee;
    }

    public void Fire(Employee employee){
        if(EmployeeList.contains(employee)) EmployeeList.remove(employee);
    }

    public void writeEmployeeList(){
        
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {

            fout = new FileOutputStream("./EmployeeList.ser", false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(EmployeeList);

        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            if(fout != null){
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void readEmployeeList(){
        
        FileInputStream fin = null;
        ObjectInputStream ios = null;

        try{
            initiateEmployeeList();
            fin = new FileInputStream("./EmployeeList.ser");
            ios = new ObjectInputStream(fin);

            Object obj =  ios.readObject();
            
            @SuppressWarnings("unchecked") ArrayList<Employee> readCase = (ArrayList<Employee>)obj;

            
            EmployeeList = readCase;

        } catch(Exception ex){
            ex.printStackTrace();
        } finally{
            if(fin != null){
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(ios != null){
                try {
                    ios.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
