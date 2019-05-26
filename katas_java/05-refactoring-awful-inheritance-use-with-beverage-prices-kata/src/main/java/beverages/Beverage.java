package beverages;

import beverages.toppings.WithCinnamon;
import beverages.toppings.WithCream;
import beverages.toppings.WithMilk;

public interface Beverage {

    double price();

    abstract class BeverageBuilder<T extends BeverageBuilder> {

        Beverage beverage;

        BeverageBuilder(Beverage beverage) {
            this.beverage = beverage;
        }

        @SuppressWarnings("unchecked")
        T withMilk() {
            beverage = WithMilk.withMilk(beverage);
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        T withCream() {
            beverage = WithCream.withCream(beverage);
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        T withCinnamon() {
            beverage = WithCinnamon.withCinnamon(beverage);
            return (T) this;
        }

        public Beverage order() {
            return beverage;
        }
    }
}
