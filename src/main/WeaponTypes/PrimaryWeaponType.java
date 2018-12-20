package main.WeaponTypes;

public enum PrimaryWeaponType {
    NONE(""), P90("P90"), MAG7("MAG7"), FAMAS("famas"), GALIL("galilar"), AK74("ak47"), M4A1S("m4a1_silencer"), M4A1("m4a1"), AUG("aug"), SCOUT("ssg08"), AWP("awp");

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
        System.out.println("name: " + this.name);
//        if(this.name.equals(""))
//            return " \"test\" ";
        return "weapon_" + this.name;
    }
}
