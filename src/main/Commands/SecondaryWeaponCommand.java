package main.Commands;

import main.WeaponTypes.SecondaryWeaponType;

public class SecondaryWeaponCommand extends Command<SecondaryWeaponType> {
    public SecondaryWeaponCommand(String key, SecondaryWeaponType value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return "SecondaryWeapon"+super.toString();
    }
}
