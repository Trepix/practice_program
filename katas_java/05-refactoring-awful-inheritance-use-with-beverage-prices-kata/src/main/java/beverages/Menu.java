package beverages;

import beverages.HotChocolate.HotChocolateBuilder;

import static beverages.Coffee.CoffeeBuilder;
import static beverages.Tea.*;

public class Menu {

    public static CoffeeBuilder coffee() {
        return new CoffeeBuilder();
    }

    public static TeaBuilder tea() {
        return new TeaBuilder();
    }

    public static HotChocolateBuilder hotChocolate() {
        return new HotChocolateBuilder();
    }

}
