package cosmetics;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author khanhhoang
 */
public class Cart {

    private Map<String, Cosmetics> cart;

    public Cart() {
    }

    public Cart(Map<String, Cosmetics> cart) {
        this.cart = cart;
    }

    public void clear() {
        this.cart.clear();
    }

    public Map<String, Cosmetics> getCart() {
        return cart;
    }

    public void setCart(Map<String, Cosmetics> cart) {
        this.cart = cart;
    }

    public boolean add(Cosmetics cosmetics) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(cosmetics.getId())) {
                int currentQuantity = this.cart.get(cosmetics.getId()).getQuantity();
                cosmetics.setQuantity(currentQuantity + cosmetics.getQuantity());
            }
            this.cart.put(cosmetics.getId(), cosmetics);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    public boolean remove(String id) {
        boolean check = false;
        try {
            if (this.cart != null) {
                this.cart.remove(id);
                check = true;
            }

        } catch (Exception e) {
        }
        return check;
    }

    public boolean edit(String id, Cosmetics cosmetics) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.replace(id, cosmetics);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
}
