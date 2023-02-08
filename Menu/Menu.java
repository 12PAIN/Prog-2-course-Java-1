package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import Client.*;
import Item.*;
import Order.Order;
import Employee.*;

public class Menu {
    
    ItemModel ItemModel;
    ClientModel ClientModel;
    EmployeeModel EmployeeModel;

    Client currentClient = null;

    public void Start(){
        initiateModels();

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        userMenu();
        initiateWrite();
    }

    public void userMenu(){
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Hi user! Here's your menu:");
            if(currentClient == null) System.out.println("1. Sign In or Register");
            if(currentClient != null) System.out.println("1. Buy items");
            if(currentClient != null) System.out.println("2. Sign Out");
            System.out.println("0. Exit the Program");

            int choice = 0;
            try {
                choice = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if(choice == 0) break;

            if(choice == 1 && currentClient == null){
                userSignIn();
                continue;
            }

            if(choice == 1 && currentClient != null){
                userShop();
                continue;
            }

            if(choice == 2 && currentClient != null) currentClient = null;

            if(choice == 8891){ 
                adminPanel();
                continue;
            }

        }
        

    };

    public void userSignIn(){

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("1. Sign In");
            System.out.println("2. Register");
            System.out.println("0. Back to main menu");

            int choice = 0;
            try {
                choice = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if(choice == 0) break;

            if(choice == 2){ 
                registerClient();
                break;
            }

            if(choice == 1){
                selectClient();
                break;
            }
        }
    

    };

    public void selectClient(){

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String firstName = null, name  = null, birthdate = null;

        System.out.print("Enter your first name ->");
        try {
            firstName = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.print("Enter your name ->");
        try {
            name = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.print("Enter your birthdate ->");
        try {
            birthdate = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        currentClient = ClientModel.selectClient(firstName, name, birthdate);
    

    }

    public void registerClient(){

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String firstName = null, name = null, patronym = null, birthdate = null, sex = null;

        try{

        System.out.print("Enter your first name ->");
        firstName = br.readLine();

        System.out.print("Enter your name ->");
        name = br.readLine();

        System.out.print("Enter your patronymic ->");
        patronym = br.readLine();

        System.out.print("Enter your birthdate ->");
        birthdate = br.readLine();

        System.out.print("Enter your sex ->");
        sex = br.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        currentClient = ClientModel.addClient(firstName, name, patronym, birthdate, sex, null);
        

    };

    public void userShop(){

        ArrayList<Item> cart = new ArrayList<>();
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            while(true){

                
                Float totalCost = (float) 0.0;

                for(Item it: cart){
                    totalCost += it.getCost();
                }

                System.out.println("Cart total cost: " + totalCost.toString() + "$");
                System.out.println("Items in your cart:");

                for(Item it: cart){
                    System.out.print("Item name: " + it.getName() + " | Item cost: " + it.getCost().toString() + "$ | Item production date: " + it.getProductionDate());
                    System.out.println();
                    System.out.println();
                }

                System.out.println("0. Back to main menu");
                System.out.println("Enter -1 to complete order");
                System.out.println();
                System.out.println("Just type ID of item to add it to cart. Items list:");

                for(Item it: ItemModel.ItemList){
                    System.out.print("ID: " + it.getId().toString() + " | Item name: " + it.getName() + " | Item cost: " + it.getCost().toString() + "$ | Item production date: " + it.getProductionDate());
                    System.out.println();
                }

                int choice = 0;
                try {
                    choice = Integer.parseInt(br.readLine());
                } catch (NumberFormatException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if(choice == 0) break;
                if(choice == -1 && cart.size() != 0){ 
                    createOrder(cart);
                    break;
                }

                cart.add(ItemModel.selectItem(choice));

            }
        

    };

    public void createOrder(ArrayList<Item> items){
        ClientModel.newOrder(items, currentClient);
    };

    public void adminPanel(){

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.println("Hi, Admin! Here's your panel:");
                System.out.println("1. Hire employee");
                System.out.println("2. Fire employee");
                System.out.println("3. Add item");
                System.out.println("4. Statistics");
                System.out.println("5. Orders");

                System.out.println("0. Back to main menu");

                int choice = 0;
                try {
                    choice = Integer.parseInt(br.readLine());
                } catch (NumberFormatException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if(choice == 0) break;
                if(choice == 1){ 
                    hireEmploy();
                    continue;
                }
                if(choice == 2){ 
                    fireEmploy();
                    continue;
                }
                if(choice == 3){ 
                    addNewItem();
                    continue;
                }
                if(choice == 4){ 
                    statistics();
                    continue;
                }
                if(choice == 5){ 
                    ShowClientOrders();
                    continue;
                }

            }

    };

    public void hireEmploy(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String firstName, name, patronym, birthdate, sex, dept, position;
            Float salary;

            System.out.print("Enter employee first name ->");
            firstName = br.readLine();

            System.out.print("Enter employee name ->");
            name = br.readLine();

            System.out.print("Enter employee patronymic ->");
            patronym = br.readLine();

            System.out.print("Enter employee birthdate ->");
            birthdate = br.readLine();

            System.out.print("Enter employee sex ->");
            sex = br.readLine();

            System.out.print("Enter employee department ->");
            dept = br.readLine();

            System.out.print("Enter employee position ->");
            position = br.readLine();

            System.out.print("Enter employee salary ->");
            salary = Float.parseFloat(br.readLine());

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            EmployeeModel.Hire(firstName, name, patronym, birthdate, sex, dept, salary, position);
        } catch(Exception e){ e.printStackTrace();}

    };
    

    public void fireEmploy(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String firstName, name, birthdate, patronym, sex;

            System.out.print("Enter employee first name ->");
            firstName = br.readLine();

            System.out.print("Enter employee name ->");
            name = br.readLine();

            System.out.print("Enter employee patronymic ->");
            patronym = br.readLine();

            System.out.print("Enter employee birthdate ->");
            birthdate = br.readLine();

            System.out.print("Enter employee sex ->");
            sex = br.readLine();

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            EmployeeModel.Fire(EmployeeModel.selectEmployee(firstName, name, patronym, birthdate, sex));
        }catch(Exception e){e.printStackTrace();}

    };
    
    public void addNewItem(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String name, productionDate;
            Float cost;
            Integer id;

            System.out.print("Enter product name ->");
            name = br.readLine();


            System.out.print("Enter employee production date ->");
            productionDate = br.readLine();

            System.out.print("Enter item cost ->");
            cost = Float.parseFloat(br.readLine());

            System.out.print("Enter item id (must be more than 0!!!, and not repeat other ID's) ->");
            id = Integer.parseInt(br.readLine());

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            ItemModel.addItem(name, productionDate, cost, id);
        } catch(Exception e) {e.printStackTrace();}

    };

    public void statistics(){

        Integer employeeCount = EmployeeModel.EmployeeList.size();

        Float midSalary = (float) 0, totalSalary = (float) 0;

        {
            Float tempSalary = (float) 0.0;

            for(Employee emp: EmployeeModel.EmployeeList){
                tempSalary += emp.getSalary();
            }

            totalSalary = tempSalary;
            if(employeeCount != 0) midSalary = tempSalary/employeeCount;
        }

        Item mostPopularItem = null;
        Integer quantity = -1;

        {
            HashMap<String,Integer> itemMap = new HashMap<>();

            for(Client client: ClientModel.ClientList){
                for(Order orders: client.getOrders(client)){
                    for(Item it: orders.getItems()){
                        
                        Integer tempItemQuantity = itemMap.get(it.getName());

                        if(tempItemQuantity == null || tempItemQuantity ==  0) tempItemQuantity = 1;
                        else{
                            tempItemQuantity += 1;
                            itemMap.put(it.getName(), tempItemQuantity);
                        }

                        if(tempItemQuantity > quantity){
                            mostPopularItem = it;
                            quantity = tempItemQuantity;
                        }
                    }
                }
            }

            
        }

        System.out.println("Employees count: " + employeeCount.toString());
        System.out.println("Medium salary: " + midSalary.toString());
        System.out.println("Total salary: " + totalSalary.toString());
        if(mostPopularItem != null)  System.out.print("Most popular item is: ID: " + mostPopularItem.getId().toString() + " | Item name: " + mostPopularItem.getName() + " | Item cost: " + mostPopularItem.getCost().toString() + "$ | Item production date: " + mostPopularItem.getProductionDate());

    };

    public void ShowClientOrders(){
        System.out.println();

        for(Client client: ClientModel.ClientList){


            System.out.println("_______________________________________");
            System.out.println("Client: " + client.getFirstname() + " " + client.getName());
            System.out.println("_______________ORDERS__________________");

            for(Order order: client.getOrders(client)){
                System.out.println("Order by date: " + order.getCreationDate() + " Total cost: " + order.getTotalCost());
                System.out.println("Items:");
                for(Item it: order.getItems()){
                   System.out.println("ID: " + it.getId().toString() + " | Item name: " + it.getName() + " | Item cost: " + it.getCost().toString() + "$ | Item production date: " + it.getProductionDate());
                }
                System.out.println("______________________________________");
            }
            
        }

        System.out.println("______________________________________");
        System.out.println();
        System.out.println();
    }

    public void initiateModels(){
        ItemModel = new ItemModel();
        ItemModel.readItemList();

        ClientModel = new ClientModel();
        ClientModel.readClientList();

        EmployeeModel = new EmployeeModel();
        EmployeeModel.readEmployeeList();
    }

    public void initiateWrite(){
        ItemModel.writeItemList();
        ClientModel.writeClientList();
        EmployeeModel.writeEmployeeList();
    }

}
