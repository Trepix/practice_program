package beverages;

public class Coffee implements Beverage {

    @Override
    public double price() {
        return 1.2;
    }

    public static class CoffeeBuilder extends BeverageBuilder<CoffeeBuilder> {

        CoffeeBuilder() {
            super(new Coffee());
        }

        public CoffeeBuilder withMilk() {
            return super.withMilk();
        }
        
        public CoffeeBuilder withCream() {
            return super.withCream();
        }

        public CoffeeBuilder withCinnamon() {
            return super.withCinnamon();
        }
    }
}
