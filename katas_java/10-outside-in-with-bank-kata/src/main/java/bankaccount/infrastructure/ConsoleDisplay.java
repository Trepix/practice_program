package bankaccount.infrastructure;

import bankaccount.domain.Display;

public class ConsoleDisplay implements Display {
    public void show(String line) {
        System.out.println(line);
    }
}
