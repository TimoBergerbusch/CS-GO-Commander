package main.Commands;

public class SingleCommand extends StringCommand {

    public SingleCommand(String key) {
        super(key, "");
    }

    @Override
    public String toString() {
        return "Single"+super.toString();
    }
}
