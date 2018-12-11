public abstract class Command<T> {

    public static String[] booleanCommands = new String[]{
            "mp_free_armor", "sv_allow_votes", "mp_overtime_enable",
            "mp_match_can_clinch", "mp_halftime", "mp_damage_headshot_only",
            "mp_weapons_allow_map_placed", "mp_death_drop_gun", "mp_ignore_round_win_conditions"
    };

    protected String key;
    protected T value;

    public Command(String key, T value) {
        this.key = key;
        this.value = value;
    }

    protected Command(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getCommand() {
        return this.key + " " + this.value + ";";
    }
}
