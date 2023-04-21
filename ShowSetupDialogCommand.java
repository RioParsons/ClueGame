public class ShowSetupDialogCommand implements Command {
    private ClueGUI gui;

    public ShowSetupDialogCommand(ClueGUI gui) {
        this.gui = gui;
    }

    public void execute() {
        gui.showSetupDialog();
    }
}