package bankaccount.domain.statement;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Statement {

    List<BankingTransaction> bankingTransactions = new LinkedList<>();

    public void add(BankingTransaction bankingTransaction) {
        bankingTransactions.add(bankingTransaction);
    }
}
