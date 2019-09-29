package ohce;

class MomentOfTheDay {
    private final HourRetriever hourRetriever;

    MomentOfTheDay(HourRetriever hourRetriever) {
        this.hourRetriever = hourRetriever;
    }

    boolean isTheMorning() {
        return hourRetriever.getIn24Format() >= 6 && hourRetriever.getIn24Format() < 12;
    }

    boolean isTheAfternoon() {
        return hourRetriever.getIn24Format() >= 12 && hourRetriever.getIn24Format() < 20;
    }
}
