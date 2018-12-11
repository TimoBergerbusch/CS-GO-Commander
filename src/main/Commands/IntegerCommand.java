package main.Commands;

public class IntegerCommand extends Command<Integer> {
    public IntegerCommand(String key, Integer value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return "Integer"+super.toString();
    }
}
