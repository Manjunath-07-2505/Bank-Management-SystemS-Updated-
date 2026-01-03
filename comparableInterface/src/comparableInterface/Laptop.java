package comparableInterface;

public class Laptop implements Comparable<Laptop> {
    int id;
    String brand;
     int price;

   Laptop() {
        this.id = id;
        this.brand = brand;
        this.price = price;
    }

    	// TODO Auto-generated constructor stub

	

	@Override
    public int compareTo(Laptop other) {
        // Ascending order by id
        return id-other.id;
    }

    @Override
    public String toString() {
        return "Laptop{id=" + id + ", brand='" + brand + "', price=" + price + "}";
    }
}
