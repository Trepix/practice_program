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
        int amount = 0;
        for (BankingTransaction bankingTransaction : bankingTransactions) {
            amount += bankingTransaction.amount();
            StatementLine statementLine = new StatementLine(bankingTransaction, amount);
            statementLines.push(statementLine);
        }
        return statementLines.iterator();
    }
}
