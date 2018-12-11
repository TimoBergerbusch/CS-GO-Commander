package main.Commands;

import main.WeaponTypes.PrimaryWeaponType;

public class PrimaryWeaponCommand extends Command<PrimaryWeaponType> {

    public PrimaryWeaponCommand(String key, PrimaryWeaponType value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return "PrimaryWeapon"+super.toString();
    }
}
