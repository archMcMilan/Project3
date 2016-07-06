package model.versions.type;

/**
 * Created by Artem on 05.07.16.
 */
public class Package {
    private String type;
    private int amount;
    private int price;

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Package{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
