package ohce;

class Oche {
    private final IO io;
    private Hour hour;

    Oche(IO io, Hour hour) {
        this.io = io;
        this.hour = hour;
    }

    void runs(String name) {
        io.write("¡Buenas días " + name + "!");
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
