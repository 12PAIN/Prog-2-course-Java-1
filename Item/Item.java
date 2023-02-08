package Item;

import java.io.Serializable;

public class Item implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public Item(String name, String productionDate, Float cost, Integer id){
        this.cost = cost;
        this.name = name;
        this.productionDate = productionDate;
        this.id = id;
    }

    private Integer id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String productionDate;
    
    public String getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
    private Float cost;

    public Float getCost() {
        return cost;
    }
    public void setCost(Float cost) {
        this.cost = cost;
    }
}
