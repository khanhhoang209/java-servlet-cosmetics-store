
package cosmetics;

/**
 *
 * @author khanhhoang
 */

public class Cosmetics {
    private String id;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private String image;
    private String describe;

    public Cosmetics() {
        this.id = "";
        this.name = "";
        category = "";
        this.quantity = 0;
        this.price = 0;
        this.image = "";
        this.describe = "";
    }

    public Cosmetics(String id, String name, String category, int quantity, double price, String image, String describe) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.describe = describe;
    }

    public Cosmetics(String id, String name, String category, int quantity, double price, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }
    
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    
    
    public Cosmetics(String id, String name, String category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
