package main.Commands;

public abstract class Command<T> {

    public static StringCommand ErrorCommand = new SingleCommand("ERROR");

    public static String[] booleanCommands = {
            "mp_free_armor", "sv_allow_votes", "mp_overtime_enable", "mp_buy_anywhere",
            "mp_match_can_clinch", "mp_halftime", "mp_damage_headshot_only",
            "mp_weapons_allow_map_placed", "mp_death_drop_gun", "mp_ignore_round_win_conditions" ,
            "mp_do_warmup_period", "mp_autoteambalance", "mp_buy_allow_grenades", "mp_teammates_are_enemies",
            "sv_showimpacts", "sv_grenade_trajectory"
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

    @Override
    public String toString() {
        return "Command:\t key = " + key + "\t value = " + value;
    }
}
