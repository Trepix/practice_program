package ohce;

class Oche {

    private static final String WORD_TO_STOP_PROGRAM = "Stop!";

    private Reader reader;
    private Writer writer;
    private MomentOfTheDay momentOfTheDay;

    Oche(Reader reader, Writer writer, HourRetriever hourRetriever) {
        this.reader = reader;
        this.writer = writer;
        this.momentOfTheDay = new MomentOfTheDay(hourRetriever);
    }

    private static boolean hasToStopProgram(String input) {
        return input.equals(WORD_TO_STOP_PROGRAM);
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

    private void processWord(String input) {
        Word word = Word.from(input);
        writer.write(word.reverseIt());
        if (word.isPalindrome()) {
            writer.write("¡Bonita palabra!");
        }
    }

    private void printGreeting(String name) {
        if (momentOfTheDay.isTheMorning()) {
            writer.write("¡Buenas días " + name + "!");
        } else if (momentOfTheDay.isTheAfternoon()) {
            writer.write("¡Buenas tardes " + name + "!");
        } else {
            writer.write("¡Buenas noches " + name + "!");
        }
    }
}
