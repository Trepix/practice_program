package ohce;

class Oche {
    private Reader reader;
    private Writer writer;
    private Hour hour;

    Oche(Reader reader, Writer writer, Hour hour) {
        this.reader = reader;
        this.writer = writer;
        this.hour = hour;
    }

    void runs(String name) {
        printGreeting(name);

        String input;
        while (!(input = reader.nextWord()).equals("Stop!")) {
            String reversed = new StringBuilder(input).reverse().toString();
            writer.write(reversed);
            if (reversed.equals(input)) {
                writer.write("¡Bonita palabra!");
            }
        }
        writer.write("Adios " + name);
    }

    private void printGreeting(String name) {
        if (hour.getIn24Format() < 12) {
            writer.write("¡Buenas días " + name + "!");
        } else if (hour.getIn24Format() < 20){
            writer.write("¡Buenas tardes " + name + "!");
        } else {
            writer.write("¡Buenas noches " + name + "!");
        }
    }
}
