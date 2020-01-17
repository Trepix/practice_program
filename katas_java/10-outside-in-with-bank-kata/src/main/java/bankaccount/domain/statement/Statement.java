package bankaccount.domain.statement;

import bankaccount.domain.bankingtransactions.BankingTransaction;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Statement implements Iterable<StatementLine> {

    List<BankingTransaction> bankingTransactions = new LinkedList<>();

    public void add(BankingTransaction bankingTransaction) {
        bankingTransactions.add(bankingTransaction);
    }

    @Override
    public Iterator<StatementLine> iterator() {
        LinkedList<StatementLine> statementLines = new LinkedList<>();
        for (BankingTransaction bankingTransaction : bankingTransactions) {
            statementLines.push(new StatementLine(bankingTransaction, bankingTransaction.amount()));
        }
        return statementLines.iterator();
    }
}
