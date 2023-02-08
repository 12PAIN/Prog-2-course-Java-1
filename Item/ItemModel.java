package Item;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ItemModel {
    
    public ArrayList<Item> ItemList;

    public void initiateItemList(){
        if(ItemList == null) ItemList = new ArrayList<>();
    }

    public Item selectItem(Integer id){
        Item item = null;

        for(Item it: ItemList){
            if(it.getId() == id){
                item = it;
                break;
            }
        }

        return item;
    }

    public void addItem(String name, String productionDate, Float cost, Integer id){
        initiateItemList();

        Item newItem = new Item(name, productionDate, cost, id);

        ItemList.add(newItem);
    }

    public void writeItemList(){

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {

            fout = new FileOutputStream("./ItemList.ser", false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(ItemList);

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

    public void readItemList(){
        
        FileInputStream fin = null;
        ObjectInputStream ios = null;

        try{
            initiateItemList();
            fin = new FileInputStream("./ItemList.ser");
            ios = new ObjectInputStream(fin);

            @SuppressWarnings("unchecked") ArrayList<Item> readCase = (ArrayList<Item>) ios.readObject();

            
            ItemList = readCase;

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
