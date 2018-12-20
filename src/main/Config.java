package main;

import FastDevelopment.PropertiesFetch;
import main.Commands.*;
import main.WeaponTypes.PrimaryWeaponType;
import main.WeaponTypes.SecondaryWeaponType;
import main.WeaponTypes.UtilityType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    private ArrayList<Command> commands;
    public static String[] BASIC_KEYS = new String[]{"mp_do_warmup_period", "mp_warmup_duration",
            "mp_halftime_duration", "mp_halftime", "mp_maxrounds", "mp_roundtime", "mp_maxmoney", "mp_startmoney",
            "mp_freezetime", "mp_buytime", "mp_buy_anywhere", "mp_limitteams", "mp_autoteambalance", "mp_free_armor",
            "sv_infinite_ammo", "ammo_grenade_limit_total", "mp_buy_allow_grenades", "mp_teammates_are_enemies",
            "mp_damage_headshot_only", "mp_death_drop_gun", "sv_showimpacts", "sv_grenade_trajectory",
            "mp_t_default_primary", "mp_ct_default_primary", "mp_t_default_secondary", "mp_ct_default_secondary"};

    @NotNull
    @Contract(" -> new")
    public static Config getDefaultConfig() {
        ArrayList<Command> defaultCommands = new ArrayList<>();

        defaultCommands.add(new BooleanCommand("mp_do_warmup_period", true));
        defaultCommands.add(new IntegerCommand("mp_warmup_duration", 60));
        defaultCommands.add(new IntegerCommand("mp_halftime_duration", 30));
        defaultCommands.add(new BooleanCommand("mp_halftime", false));

        defaultCommands.add(new IntegerCommand("mp_maxrounds", 30));
        defaultCommands.add(new IntegerCommand("mp_roundtime", 300));
        defaultCommands.add(new IntegerCommand("mp_maxmoney", 16000));
        defaultCommands.add(new IntegerCommand("mp_startmoney", 800));
        defaultCommands.add(new IntegerCommand("mp_freezetime", 10));
        defaultCommands.add(new IntegerCommand("mp_buytime", 30));
        defaultCommands.add(new BooleanCommand("mp_buy_anywhere", false));
        defaultCommands.add(new IntegerCommand("mp_limitteams", 5));
        defaultCommands.add(new BooleanCommand("mp_autoteambalance", true));

        defaultCommands.add(new BooleanCommand("mp_free_armor", false));
        defaultCommands.add(new IntegerCommand("sv_infinite_ammo", 0));
        defaultCommands.add(new IntegerCommand("ammo_grenade_limit_total", 4));
        defaultCommands.add(new BooleanCommand("mp_buy_allow_grenades", true));

        defaultCommands.add(new BooleanCommand("mp_teammates_are_enemies", false));
        defaultCommands.add(new BooleanCommand("mp_damage_headshot_only", false));
        defaultCommands.add(new BooleanCommand("mp_death_drop_gun", true));

        defaultCommands.add(new BooleanCommand("sv_showimpacts", false));
        defaultCommands.add(new BooleanCommand("sv_grenade_trajectory", false));

        defaultCommands.add(new PrimaryWeaponCommand("mp_t_default_primary", PrimaryWeaponType.AK74));
        defaultCommands.add(new PrimaryWeaponCommand("mp_ct_default_primary", PrimaryWeaponType.M4A1));
        defaultCommands.add(new SecondaryWeaponCommand("mp_t_default_secondary", SecondaryWeaponType.GLOCK));
        defaultCommands.add(new SecondaryWeaponCommand("mp_ct_default_secondary", SecondaryWeaponType.USPS));

//        defaultCommands.add(new UtilityCommand("give", UtilityType.SMOKE));

        return new Config(defaultCommands);
    }

    public static Config getDefaultConfigIni() {
        StringBuilder sb = new StringBuilder();


        for (String key : BASIC_KEYS) {
            sb.append(key).append(" ").append(PropertiesFetch.get("DEFAULT COMMANDS", key)).append(";");
        }

        return Config.parseString(sb.toString());
    }

    public static Config readConfigFile(@NotNull File configFile) {
        String content = "";
        try {
            content = Config.readFile(configFile.getPath(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseString(content);
    }

    public static Config parseString(String content) {
        ArrayList<Command> commands = null;
        String[] stringCommands = content.split(";");
        commands = new ArrayList<>(stringCommands.length);

        for (int i = 0; i < stringCommands.length; i++) {
            stringCommands[i] = stringCommands[i].trim();

            if (!stringCommands[i].startsWith("//"))
                commands.add(readCommand(stringCommands[i]));
        }

        return new Config(commands);
    }

    private Config(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public static Command readCommand(@NotNull String row) {
        if (row.startsWith("//"))
            return null;
        String[] commandparts = row.split(" ");
        Command cmd = null;

        String key = commandparts[0];
        key = key.replace(";", "");

        if (commandparts.length >= 3)
            System.err.println("Unexpected number of items for command(" + row + ") with parts " + commandparts.length);
        else if (commandparts.length == 1)
            cmd = new SingleCommand(key);
        else { // commandparts.length == 2
            String value = commandparts[1];
            value = value.replace(";", "");
            if (Arrays.asList(Command.booleanCommands).contains(key)) {
                if (value.equals("0"))
                    cmd = new BooleanCommand(key, false);
                else
                    cmd = new BooleanCommand(key, true);
            } else {
                int intValue;
                try {
                    intValue = Integer.parseInt(value);
                    cmd = new IntegerCommand(key, intValue);
                } catch (Exception e) {

                    value = value.replace("weapon_", "");

                    PrimaryWeaponType pwt = PrimaryWeaponType.contains(value);
                    SecondaryWeaponType swt = SecondaryWeaponType.contains(value);
                    UtilityType ut = UtilityType.contains(value);
                    if (pwt != null) {
                        cmd = new PrimaryWeaponCommand(key, pwt);
                    } else if (swt != null) {
                        cmd = new SecondaryWeaponCommand(key, swt);
                    } else //noinspection StatementWithEmptyBody
                        if (ut != null) {
                            // TODO
                        } else
                            cmd = new StringCommand(key, value);
                }
            }
        }
        return cmd;
    }

    public Command getCommand(String key) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getKey().equalsIgnoreCase(key))
                return commands.get(i);
        }
        return null;
    }

    public ArrayList<String> getCommandKeys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (Command cmd : commands)
            allKeys.add(cmd.getKey());

        return allKeys;
    }

    @NotNull
    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Command cmd : this.commands) {
            sb.append(cmd.getCommand()).append("\n");
        }
        return sb.toString();
    }

    public String toPrettyString() {
        ArrayList<Command> basicBeauty = this.clusterCommands(new String[]{"mp_do_warmup_period", "mp_warmup_duration",
                "mp_halftime_duration", "mp_halftime", "mp_maxrounds", "mp_roundtime", "mp_maxmoney", "mp_startmoney",
                "mp_freezetime", "mp_buytime", "mp_buy_anywhere", "mp_limitteams", "mp_autoteambalance",});
        ArrayList<Command> modeBeauty = this.clusterCommands(new String[]{"mp_free_armor", "sv_infinite_ammo",
                "ammo_grenade_limit_total", "mp_buy_allow_grenades", "mp_teammates_are_enemies",
                "mp_damage_headshot_only", "mp_death_drop_gun", "sv_showimpacts", "sv_grenade_trajectory",});
        ArrayList<Command> weaponBeauty = this.clusterCommands(new String[]{"mp_t_default_primary", "mp_ct_default_primary", "mp_t_default_secondary", "mp_ct_default_secondary"});
        ArrayList<Command> rest = this.collapseCommands(basicBeauty, modeBeauty, weaponBeauty);

        StringBuilder sb = new StringBuilder();
        // HEADER
        sb.append("//###########################################################//").append("\n");
        sb.append("//############### Produced by CS-GO Commander ###############//").append("\n");
        sb.append("//################# - by Timo Bergerbusch - #################//").append("\n");
        sb.append("//###########################################################//").append("\n");

        // General Commands
        sb.append("//***********************************************************//").append("\n");
        sb.append("//***************      General  Commands      ***************//").append("\n");
        sb.append("//***********************************************************//").append("\n");
        for (Command cmd : basicBeauty)
            sb.append(cmd.getCommand()).append("\n");

        // Mode Commands
        sb.append("//***********************************************************//").append("\n");
        sb.append("//***************      Gamemode Commands      ***************//").append("\n");
        sb.append("//***********************************************************//").append("\n");
        for (Command cmd : modeBeauty)
            sb.append(cmd.getCommand()).append("\n");

        // weapon Commands
        sb.append("//***********************************************************//").append("\n");
        sb.append("//***************       Weapon Commands       ***************//").append("\n");
        sb.append("//***********************************************************//").append("\n");
        for (Command cmd : weaponBeauty)
            sb.append(cmd.getCommand()).append("\n");

        // other Commands
        sb.append("//***********************************************************//").append("\n");
        sb.append("//***************       Other  Commands       ***************//").append("\n");
        sb.append("//***********************************************************//").append("\n");
        for (Command cmd : rest)
            sb.append(cmd.getCommand()).append("\n");

        return sb.toString();
    }

    private ArrayList<Command> collapseCommands(ArrayList<Command> basicBeauty, ArrayList<Command> modeBeauty, ArrayList<Command> weaponBeauty) {
        ArrayList<Command> rest = new ArrayList<>();
        for (Command cmd : commands) {
            if (!basicBeauty.contains(cmd) && !modeBeauty.contains(cmd) && !weaponBeauty.contains(cmd))
                rest.add(cmd);
        }
        return rest;
    }

    private ArrayList<Command> clusterCommands(String[] strings) {
        List<String> keys = Arrays.asList(strings);
        ArrayList<Command> list = new ArrayList<>();
        for (Command cmd : commands) {
            if (keys.contains(cmd.getKey()))
                list.add(cmd);
        }
        return list;
    }
}
