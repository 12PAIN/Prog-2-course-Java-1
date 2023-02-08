package Client;

import java.io.Serializable;
import java.util.ArrayList;

import Human.Human;
import Order.Order;

public class Client extends Human implements Serializable{

    private static final long serialVersionUID = 1L;

    public Client(String firstname, String name, String patronym, String birthdate, String sex, ArrayList<Order> orders){
        super(firstname, name, patronym, birthdate, sex);
        this.orders = orders;
    }

    private ArrayList<Order> orders;

    public ArrayList<Order> getOrders(Client client) {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void initiateOrders(){
        if(orders == null) orders = new ArrayList<>();
    }

    public void addOrder(Order order){
        if(orders != null) orders.add(order);
    }
}
