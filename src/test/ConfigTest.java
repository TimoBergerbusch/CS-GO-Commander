package test;

import main.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ConfigTest {

    @Test
    public void readConfigFileTest() {
        String input = "bot_kick;\n" +
                "mp_freezetime 1;\n" +
                "mp_round_restart_delay 5;\n" +
                "mp_maxrounds 16;\n" +
                "mp_match_can_clinch 1;\n" +
                "mp_overtime_enable 1;\n" +
                "mp_limitteams 1;\n" +
                "sv_allow_votes 0;\n" +
                "mp_free_armor 1;\n" +
                "mp_ct_default_primary weapon_ak47;\n" +
                "mp_t_default_primary weapon_ak47;\n" +
                "mp_ct_default_secondary weapon_p250;\n" +
                "mp_t_default_secondary weapon_p250;\n" +
                "mp_weapons_allow_map_placed 0;\n" +
                "mp_death_drop_gun 0;\n" +
                "mp_ignore_round_win_conditions 0;\n" +
                "mp_halftime 1;\n" +
                "mp_halftime_duration 8;\n" +
                "mp_warmup_end;\n" +
                "mp_restartgame 3;\n";
        Assertions.assertEquals(input, Config.readConfigFile(new File("H:\\Dokumente\\GitHub\\CS-GO-Commander\\commandfiles\\aim_map_exec.cfg")).toString());
    }

    @Test
    public void parseSingleCommandsTest() {
        Assertions.assertEquals("SingleStringCommand:\t key = bot_kick\t value = ", Config.readCommand("bot_kick;").toString());
        Assertions.assertEquals("SingleStringCommand:\t key = mp_warmup_end\t value = ", Config.readCommand("mp_warmup_end;").toString());
    }

    @Test
    public void parseIntegerCommandsTest() {
        Assertions.assertEquals("IntegerCommand:\t key = mp_round_restart_delay\t value = 5", Config.readCommand("mp_round_restart_delay 5;").toString());
        Assertions.assertEquals("IntegerCommand:\t key = mp_maxrounds\t value = 16", Config.readCommand("mp_maxrounds 16;").toString());
        Assertions.assertEquals("IntegerCommand:\t key = mp_limitteams\t value = 1", Config.readCommand("mp_limitteams 1;").toString());
    }

    @Test
    public void parseBooleanCommandsTest() {
        Assertions.assertEquals("BooleanCommand:\t key = mp_freezetime\t value = true", Config.readCommand("mp_freezetime 1;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = mp_match_can_clinch\t value = true", Config.readCommand("mp_match_can_clinch 1;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = mp_overtime_enable\t value = true", Config.readCommand("mp_overtime_enable 1;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = sv_allow_votes\t value = false", Config.readCommand("sv_allow_votes 0;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = mp_free_armor\t value = true", Config.readCommand("mp_free_armor 1;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = mp_damage_headshot_only\t value = true", Config.readCommand("mp_damage_headshot_only 1;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = mp_weapons_allow_map_placed\t value = false", Config.readCommand("mp_weapons_allow_map_placed 0;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = mp_death_drop_gun\t value = false", Config.readCommand("mp_death_drop_gun 0;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = mp_ignore_round_win_conditions\t value = false", Config.readCommand("mp_ignore_round_win_conditions 0;").toString());
        Assertions.assertEquals("BooleanCommand:\t key = mp_halftime\t value = true", Config.readCommand("mp_halftime 1;").toString());
    }

    @Test
    public void parseWeaponCommandsTest() {
        Assertions.assertEquals("PrimaryWeaponCommand:\t key = mp_ct_default_primary\t value = weapon_ak47", Config.readCommand("mp_ct_default_primary weapon_ak47;").toString());
        Assertions.assertEquals("PrimaryWeaponCommand:\t key = mp_ct_default_primary\t value = weapon_m4a1_silencer", Config.readCommand("mp_ct_default_primary weapon_m4a1_silencer;").toString());
        Assertions.assertEquals("SecondaryWeaponCommand:\t key = mp_ct_default_secondary\t value = weapon_usp_silencer", Config.readCommand("mp_ct_default_secondary weapon_usp_silencer;").toString());
        Assertions.assertEquals("SecondaryWeaponCommand:\t key = mp_ct_default_secondary\t value = weapon_glock", Config.readCommand("mp_ct_default_secondary weapon_glock;").toString());
    }
}