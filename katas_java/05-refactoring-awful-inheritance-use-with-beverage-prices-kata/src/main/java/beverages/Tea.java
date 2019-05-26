package beverages;

public class Tea implements Beverage {

    @Override
    public double price() {
        return 1.5;
    }

    public static class TeaBuilder extends BeverageBuilder<TeaBuilder> {

        TeaBuilder() {
            super(new Tea());
        }

        public TeaBuilder withMilk() {
            return super.withMilk();
        }

        public TeaBuilder withCinnamon() {
            return super.withCinnamon();
        }
    }
}
