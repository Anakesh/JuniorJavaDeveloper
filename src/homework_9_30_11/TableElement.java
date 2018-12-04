package homework_9_30_11;

/**
 * Created by Pavel on 04.12.2018.
 */
public class TableElement implements Comparable<TableElement>{
    private int id;
    private String name;
    private double price;
    private int quantity;

    public TableElement(int id, String name, double price, int quantity) throws TableException {
        if(id<1_0000_0000) {
            this.id = id;
        } else throw new TableException("Id exceeds maximum value");
        if(name.length()<=30) {
            this.name = name;
        } else throw new TableException("Name is too long");
        if(price<100000) {
            this.price = price;
        } else throw new TableException("Price exceeds maximum value");
        if(quantity<100000) {
            this.quantity = quantity;
        } else throw new TableException("Quantity exceeds maximum value");

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(TableElement o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        String strId = String.valueOf(id);
        StringBuilder fullId = new StringBuilder();
        for(int i = 0;i<(8-strId.length());i++){
            fullId.append(0);
        }
        fullId.append(strId);

        StringBuilder fullName = new StringBuilder();
        fullName.append(name);
        for(int i = 0;i<(30-name.length());i++){
            fullName.append(" ");
        }

        String strPrice = String.valueOf(price);
        StringBuilder fullPrice = new StringBuilder();
        for(int i = 0;i<(8-strPrice.length());i++){
            fullPrice.append(0);
        }
        fullPrice.append(strPrice);

        String strQuantity = String.valueOf(quantity);
        StringBuilder fullQuant = new StringBuilder();
        for(int i = 0;i<(4-strQuantity.length());i++){
            fullQuant.append(0);
        }
        fullQuant.append(strQuantity);

        return fullId.toString()+ " "+
                fullName.toString() +" "+
                fullPrice.toString()+" "+
                fullQuant.toString();

    }
}
