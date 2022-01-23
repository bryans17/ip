import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        ui.showStartUpMessage();
        this.tasks = new TaskList();
        try {
            this.storage = new Storage(tasks);
        } catch (IOException e) {
            ui.showFeedbackMessage("\n\tError reading from duke.txt\n");
        }
    }

    /**
     * Executes the instructions given by user
     */
    public void execute() {
        boolean exit = false;
        String input;
        while (!exit) {
            try {
                input = ui.readCommand();
                String[] commands = input.split(" ", 2);
                Command command = Parser.parseString(commands);
                switch (command) {
                case BYE:
                    exit = true;
                    ui.showFeedbackMessage("\n\tBye. Hope to see you again soon!\n");
                    try {
                        storage.saveTask();
                    } catch (IOException e) {
                        ui.showFeedbackMessage("\n\tError saving duke.txt file.\n");
                    }
                    break;
                case LIST:
                    ui.showFeedbackMessage(tasks.printTasks());
                    break;
                case MARK:
                    ui.showFeedbackMessage(tasks.markAsDone(commands[1], true));
                    break;
                case UNMARK:
                    ui.showFeedbackMessage(tasks.unmarkDone(commands[1]));
                    break;
                case DELETE:
                    ui.showFeedbackMessage(tasks.deleteTask(commands[1]));
                    break;
                case INVALID:
                    ui.showFeedbackMessage("\n\t" + commands[0] + " is not a valid command\n");
                    break;
                case EMPTY:
                    break;
                default:
                    ui.showFeedbackMessage(tasks.addTask(command, commands[1], true));
                    break;
                }
            } catch (DukeException e) {
                ui.showFeedbackMessage("\n\t" + e.getMessage() + "\n");
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.execute();
    }
}
