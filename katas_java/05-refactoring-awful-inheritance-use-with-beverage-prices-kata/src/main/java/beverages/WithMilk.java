package beverages;

public class WithMilk extends Coffee {

    private final Beverage beverage;

    public WithMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double price() {
        return this.beverage.price() +  0.10;
    }
}
