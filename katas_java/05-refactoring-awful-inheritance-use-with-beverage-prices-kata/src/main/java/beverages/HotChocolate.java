package beverages;

public class HotChocolate implements Beverage {

    @Override
    public double price() {
        return 1.45;
    }

    public static class HotChocolateBuilder extends BeverageBuilder<HotChocolateBuilder> {

        HotChocolateBuilder() {
            super(new HotChocolate());
        }

        public HotChocolateBuilder withCream() {
            return super.withCream();
        }

        public HotChocolateBuilder withCinnamon() {
            return super.withCinnamon();
        }
    }
}
