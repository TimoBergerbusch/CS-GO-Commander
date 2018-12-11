public enum SecondaryWeaponType {
    GLOCK("glock"), USPS("ups_silencer");

    private String name;

    SecondaryWeaponType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
