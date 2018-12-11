package main.WeaponTypes;

import java.util.Arrays;
import java.util.List;

public enum PrimaryWeaponType {
    AK74("ak47"), M4A1("m4a1_silencer");

    private String name;

    PrimaryWeaponType(String name) {
        this.name = name;
    }

    public static PrimaryWeaponType contains(String value) {
        List<PrimaryWeaponType> primaryWeaponTypes = Arrays.asList(PrimaryWeaponType.values());
        for (PrimaryWeaponType pwt : primaryWeaponTypes)
            if (pwt.name.equalsIgnoreCase(value))
                return pwt;

        return null;
    }

    public String toString() {
        return "weapon_" + this.name;
    }
}
