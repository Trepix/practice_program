package bankaccount.unit;

import bankaccount.domain.statement.Statement;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StatementTest {

    @Test
    public void should_return_empty_iterator() {
        Statement statement = new Statement();

        assertThat(statement.iterator(), Matchers.emptyIterable());
    }

    private <T> void assertThat(Iterator<T> iterator, Matcher<Iterable<? extends T>> listMatcher) {
        List<T> listFromIterator = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .collect(Collectors.toList());
        String reasonToFail = "Iterator " + listFromIterator + " not contains the elements of " + listMatcher.toString();
        Assert.assertThat(reasonToFail, listFromIterator, listMatcher);
    }


}
