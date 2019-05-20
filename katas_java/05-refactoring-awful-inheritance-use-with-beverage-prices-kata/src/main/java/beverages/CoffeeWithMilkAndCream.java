package beverages;

public class CoffeeWithMilkAndCream extends Coffee {

    private final Beverage beverage;

    public CoffeeWithMilkAndCream() {
        this.beverage = null;
    }

    public CoffeeWithMilkAndCream(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double price() {
        return super.price() +  0.25;
    }
}
