package machine.models;

public class Coffee {
    private final Integer water;
    private final Integer milk;
    private final Integer coffeeBeans;
    private final Integer price;

    public Coffee(Integer water, Integer coffeeBeans, Integer milk, Integer price) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.price = price;
    }

    public Integer getWater() {
        return water;
    }

    public Integer getMilk() {
        return milk;
    }

    public Integer getCoffeeBeans() {
        return coffeeBeans;
    }

    public Integer getPrice() {
        return price;
    }
}
