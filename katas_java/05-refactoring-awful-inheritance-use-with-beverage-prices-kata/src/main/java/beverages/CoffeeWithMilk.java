package beverages;

public class CoffeeWithMilk extends Coffee {

    private final Beverage beverage;

    public CoffeeWithMilk() {
        this.beverage = null;
    }

    public CoffeeWithMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double price() {
        return super.price() +  0.10;
    }
}
