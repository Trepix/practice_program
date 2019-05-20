package beverages.toppings;

import beverages.Beverage;

public class WithMilk implements Beverage {

    private final Beverage beverage;

    private WithMilk(Beverage beverage) {
        this.beverage = beverage;
    }

    public static WithMilk withMilk(Beverage beverage) {
        return new WithMilk(beverage);
    }

    @Override
    public double price() {
        return this.beverage.price() +  0.10;
    }
}
