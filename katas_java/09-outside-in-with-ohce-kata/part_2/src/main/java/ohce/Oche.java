package ohce;

class Oche {

    private static final String WORD_TO_STOP_PROGRAM = "Stop!";

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
        while (!hasToStopProgram(input)) {
            processWord(input);
            input = reader.nextWord();
        }
        writer.write("Adios " + name);
    }

    private static boolean hasToStopProgram(String input) {
        return input.equals(WORD_TO_STOP_PROGRAM);
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
