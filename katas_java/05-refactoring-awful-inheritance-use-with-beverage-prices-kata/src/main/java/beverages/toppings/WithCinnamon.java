package beverages.toppings;

import beverages.Beverage;

public class WithCinnamon implements Beverage {

    private final Beverage beverage;

    private WithCinnamon(Beverage beverage) {
        this.beverage = beverage;
    }

    public static WithCinnamon withCinnamon(Beverage beverage) {
        return new WithCinnamon(beverage);
    }
    @Override
    public double price() {
        return this.beverage.price() +  0.05;
    }
}
