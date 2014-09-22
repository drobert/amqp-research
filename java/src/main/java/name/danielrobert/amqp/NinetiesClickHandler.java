package name.danielrobert.amqp;

public class NinetiesClickHandler implements ClickHandler {

    @Override
    public String processClick(String click) {
        return (null != click) ? click.replace("Mista", "Mr. Bob") : "";
    }

}
