package beverages;

import static beverages.Coffee.CoffeeBuilder;
import static beverages.Tea.*;

public class Menu {

    public static CoffeeBuilder coffee() {
        return new CoffeeBuilder();
    }

    public static TeaBuilder tea() {
        return new TeaBuilder();
    }

}
