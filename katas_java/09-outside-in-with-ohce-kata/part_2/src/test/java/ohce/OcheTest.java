package ohce;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OcheTest {

    @Test
    public void assertTrue() {
        assertThat(true, is(true));
    }
}
