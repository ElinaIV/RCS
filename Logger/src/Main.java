public class Main {
    public static void main(String[] args) {
        Logger.getInstance().setLevel(Logger.Level.WARNING);
        Logger.getInstance().setMode(Logger.Mode.ACCUMULATION);

        Logger.getInstance().error("Hello, ");
        Logger.getInstance().error("world!");
        Logger.getInstance().flush();

    }
}
