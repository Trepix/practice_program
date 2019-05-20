package unit_tests;

import static beverages.toppings.WithCinnamon.withCinnamon;
import static beverages.toppings.WithCream.withCream;
import static beverages.toppings.WithMilk.withMilk;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import org.junit.Test;

import beverages.Beverage;
import beverages.Coffee;
import beverages.HotChocolate;
import beverages.Tea;

public class BeveragesPricingTest {

  @Test
  public void computes_coffee_price() {
    Beverage coffee = new Coffee();
    assertThat(coffee.price(), is(closeTo(1.20, 0.001)));
  }

  @Test
  public void computes_tea_price() {
    Beverage tea = new Tea();
    assertThat(tea.price(), is(closeTo(1.50, 0.001)));
  }

  @Test
  public void computes_hot_chocolate_price() {
    Beverage hotChocolate = new HotChocolate();
    assertThat(hotChocolate.price(), is(closeTo(1.45, 0.001)));
  }

  @Test
  public void computes_tea_with_milk_price() {
    Beverage teaWithMilk = withMilk(new Tea());
    assertThat(teaWithMilk.price(), is(closeTo(1.60, 0.001)));
  }

  @Test
  public void computes_coffee_with_milk_price() {
    Beverage coffeeWithMilk = withMilk(new Coffee());
    assertThat(coffeeWithMilk.price(), is(closeTo(1.30, 0.001)));
  }

  @Test
  public void computes_coffee_with_milk_and_cream_price() {
    Beverage coffeeWithMilkAndCream = withCream(withMilk(new Coffee()));
    assertThat(coffeeWithMilkAndCream.price(), is(closeTo(1.45, 0.001)));
  }

  @Test
  public void computes_hot_chocolate_with_cream_price() {
    Beverage hotChocolateWithCream = withCream(new HotChocolate());
    assertThat(hotChocolateWithCream.price(), is(closeTo(1.60, 0.001)));
  }

  @Test
  public void computes_with_cinnamon_topping_price() {
    Beverage beverageWithCinnamon = withCinnamon(new Beverage() {
      @Override
      public double price() {
        return 0;
      }
    });
    assertThat(beverageWithCinnamon.price(), is(closeTo(0.05, 0.001)));
  }
}
