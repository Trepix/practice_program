package beverages;

public class WithCream implements Beverage {

    private final Beverage beverage;

    private WithCream(Beverage beverage) {
        this.beverage = beverage;
    }

    public static WithCream withCream(Beverage beverage) {
        return new WithCream(beverage);
    }
    @Override
    public double price() {
        return this.beverage.price() +  0.15;
    }
}
