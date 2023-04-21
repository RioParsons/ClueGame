public class StartGameCommand implements Command {
    private ClueGUI gui;

    public StartGameCommand(ClueGUI gui) {
        this.gui = gui;
    }

    public void execute() {
        gui.homeScreen();
    }
}