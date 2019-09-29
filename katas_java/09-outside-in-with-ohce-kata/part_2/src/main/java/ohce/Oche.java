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

        String input = reader.nextWord();
        while (!input.equals("Stop!")) {
            processWord(input);
            input = reader.nextWord();
        }
        writer.write("Adios " + name);
    }

    private void processWord(String input) {
        Word word = Word.from(input);
        writer.write(word.reverseIt());
        if (word.isPalindrome()) {
            writer.write("¡Bonita palabra!");
        }
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
