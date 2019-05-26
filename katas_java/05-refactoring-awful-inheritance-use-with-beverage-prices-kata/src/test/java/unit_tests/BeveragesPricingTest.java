package unit_tests;

import beverages.Beverage;
import beverages.Coffee;
import beverages.HotChocolate;
import beverages.Tea;
import org.hamcrest.Matcher;
import org.junit.Test;

import static beverages.toppings.WithCinnamon.withCinnamon;
import static beverages.toppings.WithCream.withCream;
import static beverages.toppings.WithMilk.withMilk;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BeveragesPricingTest {

    @Test
    public void computes_coffee_price() {
        Beverage coffee = new Coffee();
        assertThat(coffee.price(), is(closeTo(1.20)));
    }

    @Test
    public void computes_tea_price() {
        Beverage tea = new Tea();
        assertThat(tea.price(), is(closeTo(1.50)));
    }

    @Test
    public void computes_hot_chocolate_price() {
        Beverage hotChocolate = new HotChocolate();
        assertThat(hotChocolate.price(), is(closeTo(1.45)));
    }

    @Test
    public void computes_tea_with_milk_price() {
        Beverage teaWithMilk = withMilk(new Tea());
        assertThat(teaWithMilk.price(), is(closeTo(1.60)));
    }

    @Test
    public void computes_coffee_with_milk_price() {
        Beverage coffeeWithMilk = withMilk(new Coffee());
        assertThat(coffeeWithMilk.price(), is(closeTo(1.30)));
    }

    @Test
    public void computes_coffee_with_milk_and_cream_price() {
        Beverage coffeeWithMilkAndCream = withCream(withMilk(new Coffee()));
        assertThat(coffeeWithMilkAndCream.price(), is(closeTo(1.45)));
    }

    @Test
    public void computes_hot_chocolate_with_cream_price() {
        Beverage hotChocolateWithCream = withCream(new HotChocolate());
        assertThat(hotChocolateWithCream.price(), is(closeTo(1.60)));
    }

    @Test
    public void computes_coffee_with_cinnamon_price() {
        Beverage beverageWithCinnamon = withCinnamon(new Coffee());
        assertThat(beverageWithCinnamon.price(), is(closeTo(1.25)));
    }

    @Test
    public void computes_tea_with_cinnamon_price() {
        Beverage beverageWithCinnamon = withCinnamon(new Tea());
        assertThat(beverageWithCinnamon.price(), is(closeTo(1.55)));
    }

    @Test
    public void computes_hot_chocolate_with_cinnamon_price() {
        Beverage beverageWithCinnamon = withCinnamon(new HotChocolate());
        assertThat(beverageWithCinnamon.price(), is(closeTo(1.50)));
    }

    private static Matcher<Double> closeTo(double operand) {
        return org.hamcrest.number.IsCloseTo.closeTo(operand, 0.001);
    }
}
