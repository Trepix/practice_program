package bankaccount.unit;

import bankaccount.domain.Display;
import bankaccount.domain.statement.BritishStatementPrinter;
import bankaccount.domain.statement.Statement;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BritishDateStatementPrinterTest {

    private Display display;
    private BritishStatementPrinter britishStatementPrinter;

    @Before
    public void setUp() {
        display = mock(Display.class);
        britishStatementPrinter = new BritishStatementPrinter(display);
    }

    @Test
    public void when_statement_is_empty_should_print_headers() {
        Statement statement = new Statement();

        britishStatementPrinter.print(statement);

        verify(display).show("date || credit || debit || balance");
    }

}
