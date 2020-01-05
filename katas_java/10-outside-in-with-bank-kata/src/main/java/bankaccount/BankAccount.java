package bankaccount;

public class BankAccount {

    private StatementPrinter printer;

    public BankAccount(Calendar calendar, Display display) {
    }

    public BankAccount(Calendar calendar, StatementPrinter printer) {
        this.printer = printer;
    }

    public void deposit(int amount) {
    }

    public void withdraw(int amount) {
    }

    public void printStatement() {
        this.printer.print(new Statement());
    }
}
