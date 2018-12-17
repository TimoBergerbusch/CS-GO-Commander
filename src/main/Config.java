package main;

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

public class Config {

    private ArrayList<Command> commands;

    @NotNull
    @Contract(" -> new")
    public static Config getDefaultConfig() {
        ArrayList<Command> defaultCommands = new ArrayList<>();

        defaultCommands.add(new BooleanCommand("mp_do_warmup_period", true));
        defaultCommands.add(new IntegerCommand("mp_halftime_duration", 30));
        defaultCommands.add(new BooleanCommand("mp_halftime", true));
        defaultCommands.add(new IntegerCommand("mp_warmup_duration", 60));

        defaultCommands.add(new IntegerCommand("mp_maxrounds", 50));
        defaultCommands.add(new IntegerCommand("mp_roundtime", 9999));
        defaultCommands.add(new IntegerCommand("mp_maxmoney", 60000));
        defaultCommands.add(new IntegerCommand("mp_startmoney", 50000));
        defaultCommands.add(new IntegerCommand("mp_freezetime", 10));
        defaultCommands.add(new IntegerCommand("mp_buytime", 9999));
        defaultCommands.add(new BooleanCommand("mp_buy_anywhere", true));
        defaultCommands.add(new IntegerCommand("mp_limitteams", 3));
        defaultCommands.add(new BooleanCommand("mp_autoteambalance", true));

        defaultCommands.add(new BooleanCommand("mp_free_armor", true));
        defaultCommands.add(new IntegerCommand("sv_infinite_ammo", 2));
        defaultCommands.add(new IntegerCommand("ammo_grenade_limit_total", 4));
        defaultCommands.add(new BooleanCommand("mp_buy_allow_grenades", true));

        defaultCommands.add(new BooleanCommand("mp_teammates_are_enemies", true));
        defaultCommands.add(new BooleanCommand("mp_damage_headshot_only", true));
        defaultCommands.add(new BooleanCommand("mp_death_drop_gun", true));

        defaultCommands.add(new BooleanCommand("sv_showimpacts", true));
        defaultCommands.add(new BooleanCommand("sv_grenade_trajectory", true));

        defaultCommands.add(new PrimaryWeaponCommand("mp_t_default_primary", PrimaryWeaponType.AK74));
        defaultCommands.add(new PrimaryWeaponCommand("mp_ct_default_primary", PrimaryWeaponType.M4A1));
        defaultCommands.add(new SecondaryWeaponCommand("mp_t_default_secondary", SecondaryWeaponType.GLOCK));
        defaultCommands.add(new SecondaryWeaponCommand("mp_ct_default_secondary", SecondaryWeaponType.USPS));

        defaultCommands.add(new UtilityCommand("give", UtilityType.SMOKE));

        return new Config(defaultCommands);
    }

    public static Config readConfigFile(@NotNull File configFile) {
        ArrayList<Command> commands = null;
        try {
            String content = Config.readFile(configFile.getPath(), StandardCharsets.UTF_8);
            String[] stringCommands = content.split(";");
            commands = new ArrayList<>(stringCommands.length);

            for (int i = 0; i < stringCommands.length; i++) {
                stringCommands[i] = stringCommands[i].trim();

                commands.add(readCommand(stringCommands[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Config(commands);
    }

    private Config(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public static Command readCommand(@NotNull String row) {
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
}
