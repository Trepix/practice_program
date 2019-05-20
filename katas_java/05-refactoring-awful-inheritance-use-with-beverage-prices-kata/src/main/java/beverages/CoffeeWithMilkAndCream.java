package beverages;

public class CoffeeWithMilkAndCream extends Coffee {

    private final Beverage beverage;

    public CoffeeWithMilkAndCream(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double price() {
        return this.beverage.price() +  0.25;
    }
}
