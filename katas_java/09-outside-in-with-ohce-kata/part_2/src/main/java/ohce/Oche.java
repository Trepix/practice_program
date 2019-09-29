package ohce;

class Oche {
    private final IO io;
    private Reader reader;
    private Hour hour;

    Oche(IO io, Reader reader, Hour hour) {
        this.io = io;
        this.reader = reader;
        this.hour = hour;
    }

    void runs(String name) {
        printGreeting(name);

        String input;
        while (!(input = reader.nextWord()).equals("Stop!")) {
            String reversed = new StringBuilder(input).reverse().toString();
            io.write(reversed);
            if (reversed.equals(input)) {
                io.write("¡Bonita palabra!");
            }
        }
        io.write("Adios " + name);
    }

    private void printGreeting(String name) {
        if (hour.getIn24Format() < 12) {
            io.write("¡Buenas días " + name + "!");
        } else if (hour.getIn24Format() < 20){
            io.write("¡Buenas tardes " + name + "!");
        } else {
            io.write("¡Buenas noches " + name + "!");
        }
    }
}
