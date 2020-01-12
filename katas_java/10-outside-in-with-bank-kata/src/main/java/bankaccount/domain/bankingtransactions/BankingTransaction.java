package bankaccount.domain.bankingtransactions;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
public class BankingTransaction {

    private final LocalDate date;
    private final int amount;

    private BankingTransaction(LocalDate date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public static BankingTransaction deposit(LocalDate date, int amount) {
        return new BankingTransaction(date, amount);
    }

    public static BankingTransaction withdrawal(LocalDate date, int amount) {
        return new BankingTransaction(date, -amount);
    }
}
