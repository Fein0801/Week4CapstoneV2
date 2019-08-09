package bad_code;

public class Item {

    private String name;
    private String price;

    public Item() {
	super();
    }

    public Item(String name, String price) {
	super();
	this.name = name;
	this.price = price;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPrice() {
	return price;
    }

    public void setPrice(String price) {
	this.price = price;
    }

    public static Item parse(String input) {
	String name = "name";
	String price = "price";
	if (input.contains(", ")) {
	    String[] data = input.split(", ");
	    name = data[0];
	    price = data[1];
	}
	return new Item(name, price);

    }

    @Override
    public String toString() {
	return "\n\t{\n\t\t" + "item: \"" + name + "\",\n\t\t" + "qty: 0" + ",\n\t\t" + "price: " + price + "\n\t}";
    }

}
