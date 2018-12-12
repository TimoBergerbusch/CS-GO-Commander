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
        defaultCommands.add(new IntegerCommand("mp_maxrounds", 50));
        defaultCommands.add(new BooleanCommand("mp_death_drop_gun", false));
        defaultCommands.add(new BooleanCommand("mp_halftime", false));
        defaultCommands.add(new IntegerCommand("mp_limitteams", 3));
        defaultCommands.add(new BooleanCommand("mp_autoteambalance", false));
        defaultCommands.add(new BooleanCommand("mp_teammembers_are_enemies", false));
        defaultCommands.add(new BooleanCommand("mp_damage_headshot_only", false));

        defaultCommands.add(new PrimaryWeaponCommand("mp_t_default_primary", PrimaryWeaponType.AK74));
        defaultCommands.add(new PrimaryWeaponCommand("mp_ct_default_primary", PrimaryWeaponType.AK74));
        defaultCommands.add(new SecondaryWeaponCommand("mp_t_default_primary", SecondaryWeaponType.GLOCK));
        defaultCommands.add(new SecondaryWeaponCommand("mp_ct_default_primary", SecondaryWeaponType.USPS));

        defaultCommands.add(new BooleanCommand("mp_free_armor", true));

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
