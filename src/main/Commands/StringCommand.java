package main.Commands;

public class StringCommand extends Command<String> {
    public StringCommand(String key, String value) {
        super(key, value);
    }

    public String toString() {
        return "String" + super.toString();
    }
}
