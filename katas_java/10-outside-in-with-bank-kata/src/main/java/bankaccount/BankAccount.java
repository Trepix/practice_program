package bankaccount;

import bankaccount.bankingtransactions.BankingTransaction;
import bankaccount.bankingtransactions.BankingTransactionRepository;
import bankaccount.statement.StatementPrinter;

public class BankAccount {

    private Calendar calendar;
    private StatementPrinter printer;
    private BankingTransactionRepository bankingTransactionRepository;


    public BankAccount(Calendar calendar, Display display) {
        this.calendar = calendar;
    }

    public BankAccount(Calendar calendar, StatementPrinter printer, BankingTransactionRepository bankingTransactionRepository) {
        this.calendar = calendar;
        this.printer = printer;
        this.bankingTransactionRepository = bankingTransactionRepository;
    }

    public void deposit(int amount) {
        bankingTransactionRepository.add(BankingTransaction.deposit(calendar.date(), amount));
    }

    public void withdraw(int amount) {
    }

    public void printStatement() {
        this.printer.print(bankingTransactionRepository.generateStatement());
    }
}
