import mars_rover.CardinalDirection;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CardinalDirectionCreationTests {

    @Test
    public void creatingNorth() {
        assertThat(CardinalDirection.from("N"), is(CardinalDirection.NORTH));
    }

    @Test
    public void creatingEast() {
        assertThat(CardinalDirection.from("E"), is(CardinalDirection.EAST));
    }

    @Test
    public void creatingSouth() {
        assertThat(CardinalDirection.from("S"), is(CardinalDirection.SOUTH));
    }

    @Test
    public void creatingWest() {
        assertThat(CardinalDirection.from("W"), is(CardinalDirection.WEST));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingInvalidCardinalDirection() {
        CardinalDirection.from("X");
    }

}
