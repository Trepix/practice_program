package bank.statement;

import bank.BankingTransaction;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = PRIVATE)
public class StatementRow {
    private final BankingTransaction transaction;
    private final int balance;

    public static StatementRow deposit(BankingTransaction transaction, int balance) {
        return new StatementRow(transaction, balance);
    }

    public static StatementRow withdrawal(BankingTransaction transaction, int balance) {
        return new StatementRow(transaction, balance);
    }

}
