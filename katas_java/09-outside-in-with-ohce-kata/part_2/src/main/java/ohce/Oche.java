package ohce;

class Oche {
    private final IO io;
    private Hour hour;

    Oche(IO io, Hour hour) {
        this.io = io;
        this.hour = hour;
    }

    void runs(String name) {
        if (hour.getIn24Format() < 12) {
            io.write("¡Buenas días " + name + "!");
        }
        else {
            io.write("¡Buenas tardes " + name + "!");
        }
        String input;
        while (!(input = io.read()).equals("Stop!")) {
            String reversed = new StringBuilder(input).reverse().toString();
            io.write(reversed);
            if (reversed.equals(input)) {
                io.write("¡Bonita palabra!");
            }
        }
        io.write("Adios " + name);
    }
}
