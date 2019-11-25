package bank.statement;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = PRIVATE)
public class StatementRow {
    private final LocalDate date;
    private final int depositAmount;
    private final int withdrawalAmount;
    private final int balance;

    public static StatementRow deposit(LocalDate date, int depositAmount, int balance) {
        return new StatementRow(date, depositAmount, 0, balance);
    }
}
