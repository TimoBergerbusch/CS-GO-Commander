package main.Commands;

public class BooleanCommand extends Command<Boolean> {


    public BooleanCommand(String key, Boolean value) {
        super(key, value);
    }

    @Override
    public String getCommand() {
        return this.key + " " + (this.value ? 1 : 0) + ";";
    }

    @Override
    public String toString() {
        return "Boolean"+super.toString();
    }
}
