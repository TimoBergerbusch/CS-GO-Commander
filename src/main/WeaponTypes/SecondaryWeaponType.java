package main.WeaponTypes;

public enum SecondaryWeaponType {
    GLOCK("glock"), USPS("usp_silencer"), P250("p250");

    private String name;

    SecondaryWeaponType(String name) {
        this.name = name;
    }

    public static SecondaryWeaponType contains(String value) {
        SecondaryWeaponType[] secondaryWeaponTypes = SecondaryWeaponType.values();
        for (SecondaryWeaponType swt : secondaryWeaponTypes)
            if (swt.name.equalsIgnoreCase(value))
                return swt;

        return null;
    }

    public String toString() {
        return this.name;
    }

    public String getCommand() {
        return "weapon_" + this.name;
    }
}
