package main.WeaponTypes;

import java.util.Arrays;
import java.util.List;

public enum UtilityType {
    HE("hegrendae"), SMOKE("smokegrenade"), INCENDIARY("incgrenade"), MOLOTOV("molotov"), TASER("taser");

    private String name;

    UtilityType(String name) {
        this.name = name;
    }

    public static UtilityType contains(String value) {
        List<UtilityType> utilityTypes = Arrays.asList(UtilityType.values());
        for (UtilityType ut : utilityTypes)
            if (ut.name.equalsIgnoreCase(value))
                return ut;

        return null;
    }

    public String toString() {
        return this.name;
    }
}
