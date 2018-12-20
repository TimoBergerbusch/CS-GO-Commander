package main.WeaponTypes;

public enum SecondaryWeaponType {
    NONE(""), GLOCK("glock"), USPS("usp_silencer"), P2000("hkp2000"), P250("p250"), DEAGLE("deagle"), FIVESEVEN("fiveseven"), TEC9("tec9"), CZ("cz75a");

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
