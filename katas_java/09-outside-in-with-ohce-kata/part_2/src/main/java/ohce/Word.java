package ohce;

class Word {
    private final String value;

    private Word(String value) {
        this.value = value;
    }

    static Word from(String word) {
        return new Word(word);
    }

    String reverseIt() {
        return new StringBuilder(value).reverse().toString();
    }

}
