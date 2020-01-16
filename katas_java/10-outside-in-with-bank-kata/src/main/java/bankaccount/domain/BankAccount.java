package bankaccount.domain;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import bankaccount.domain.bankingtransactions.BankingTransactionRepository;
import bankaccount.domain.statement.StatementPrinter;
import bankaccount.infrastructure.InMemoryBankingTransactionRepository;

public class BankAccount {

    private Calendar calendar;
    private StatementPrinter printer;
    private BankingTransactionRepository bankingTransactionRepository;


    public BankAccount(Calendar calendar, Display display) {
        this.calendar = calendar;
        this.bankingTransactionRepository = new InMemoryBankingTransactionRepository();
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
        bankingTransactionRepository.add(BankingTransaction.withdrawal(calendar.date(), amount));
    }

    public void printStatement() {
        this.printer.print(bankingTransactionRepository.generateStatement());
    }
}
