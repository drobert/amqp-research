package name.danielrobert.amqp;

public class NinetiesClickHandler implements ClickHandler, ClickValidator, ClickTagger {
    public static final String KEYWORD = "Dobalina";

    @Override
    public String processClick(String click) {
        return (null != click) ? click.replace("Mista", "Mr. Bob") : "";
    }

    @Override
    public boolean isAClick(String payload) {
        return null != payload && payload.contains(KEYWORD);
    }

    @Override
    public String tagClick(String clickContents) {
        return "At " + System.currentTimeMillis() + " finished processing click: " + clickContents;
    }

}
