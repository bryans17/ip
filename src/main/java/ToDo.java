public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String formatSave() {
        return "T |" + (super.isDone ? "1| " : "0| ") + super.description;
    }
    /*
     * Customized toString method for Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
