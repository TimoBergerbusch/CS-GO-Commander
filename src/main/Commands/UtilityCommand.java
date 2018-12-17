package main.Commands;

import main.WeaponTypes.UtilityType;

public class UtilityCommand extends Command<UtilityType> {

    public UtilityCommand(String key, UtilityType value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return "Utility" + super.toString();
    }

    @Override
    public String getCommand() {
        return this.key + " " + this.value + ";";
    }
}
