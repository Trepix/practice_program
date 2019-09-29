package ohce;

class Oche {
    private final IO io;
    private Reader reader;
    private Writer writer;
    private Hour hour;

    Oche(IO io, Reader reader, Hour hour) {
        this.io = io;
        this.reader = reader;
        this.hour = hour;
    }

    Oche(IO io, Reader reader, Writer writer, Hour hour) {
        this.io = io;
        this.reader = reader;
        this.writer = writer;
        this.hour = hour;
    }

    void runs(String name) {
        printGreeting(name);

        String input;
        while (!(input = reader.nextWord()).equals("Stop!")) {
            String reversed = new StringBuilder(input).reverse().toString();
            write(reversed);
            if (reversed.equals(input)) {
                write("¡Bonita palabra!");
            }
        }
        write("Adios " + name);
    }

    private void write(String s) {
        if (writer == null) {
            io.write(s);
        }
        else writer.write(s);

    }

    private void printGreeting(String name) {
        if (hour.getIn24Format() < 12) {
            write("¡Buenas días " + name + "!");
        } else if (hour.getIn24Format() < 20){
            write("¡Buenas tardes " + name + "!");
        } else {
            write("¡Buenas noches " + name + "!");
        }
    }
}
