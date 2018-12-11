public enum PrimaryWeaponType {
    AK74("ak47"), M4A1("m4a1_silencer");

    private String name;

    PrimaryWeaponType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
