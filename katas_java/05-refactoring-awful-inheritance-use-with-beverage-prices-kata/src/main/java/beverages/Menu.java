package beverages;

import static beverages.Coffee.CoffeeBuilder;

public class Menu {

    public static CoffeeBuilder coffee() {
        return new CoffeeBuilder();
    }

}
