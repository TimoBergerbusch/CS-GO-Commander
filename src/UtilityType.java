public enum UtilityType {
    HE("hegrendae"), SMOKE("smokegrenade"), INCENDIARY("incgrenade"), MOLOTOV("molotov"), TASER("taser");

    private String name;

    UtilityType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
