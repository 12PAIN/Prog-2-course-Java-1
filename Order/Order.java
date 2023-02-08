package Order;

import java.io.Serializable;
import java.util.ArrayList;
import Item.Item;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public Order(ArrayList<Item> items, String creationDate){
        this.creationDate = creationDate;
        this.items = items;
        
        this.totalCost = (float) 0;

        for(Item currentItem : items){
            this.totalCost += currentItem.getCost();
        }
    }

    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    private String creationDate;

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    private Float totalCost;

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    } 


}
