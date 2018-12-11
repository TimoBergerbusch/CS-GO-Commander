public class BooleanCommand extends Command<Boolean> {


    public BooleanCommand(String key, Boolean value) {
        super(key, value);
    }

    @Override
    public String getCommand() {
        return this.key + " " + (this.value ? 1 : 0)+";";
    }
}
