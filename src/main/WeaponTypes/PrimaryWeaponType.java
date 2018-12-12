package main.WeaponTypes;

public enum PrimaryWeaponType {
    AK74("ak47"), M4A1("m4a1_silencer");

    private String name;

    PrimaryWeaponType(String name) {
        this.name = name;
    }

    public static PrimaryWeaponType contains(String value) {
        PrimaryWeaponType[] primaryWeaponTypes = PrimaryWeaponType.values();
        for (PrimaryWeaponType pwt : primaryWeaponTypes)
            if (pwt.name.equalsIgnoreCase(value))
                return pwt;

        return null;
    }

    public String toString() {
        return this.name;
    }

    public String getCommand() {
        return "weapon_" + this.name;
    }
}
