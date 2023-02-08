package Client;

import java.util.ArrayList;

import Item.Item;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Order.Order;

public class ClientModel {

    public ArrayList<Client> ClientList;

    public Client addClient(String firstname, String name, String patronym, String birthdate, String sex, ArrayList<Order> orders){
        initiateClientList();

        Client newClient = new Client(firstname, name, patronym, birthdate, sex, orders);

        ClientList.add(newClient);

        return newClient;
    }

    public Client selectClient(String firstName, String Name, String birthdate){
        Client client = null;
        if(ClientList != null){
            for(Client cClient : ClientList){
                if(cClient.getFirstname().equals(firstName) && cClient.getName().equals(Name) && cClient.getBirthdate().equals(birthdate)){
                    client = cClient;
                    break;
                }
            }
        }

        return client;
    }

    public void initiateClientList(){
        if(ClientList == null) ClientList = new ArrayList<>();
    }

    public void newOrder(ArrayList<Item> items, Client client){
        client.initiateOrders();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        Order newOrder = new Order(items, dtf.format(now).toString());
        client.addOrder(newOrder);
    }

    public void writeClientList(){

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {

            fout = new FileOutputStream("./Clients.ser", false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(ClientList);

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

    public void readClientList(){

        FileInputStream fin = null;
        ObjectInputStream ios = null;

        try{
            initiateClientList();
            fin = new FileInputStream("./Clients.ser");
            ios = new ObjectInputStream(fin);

            @SuppressWarnings("unchecked") ArrayList<Client> readCase = (ArrayList<Client>) ios.readObject();

            
            ClientList = readCase;

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
