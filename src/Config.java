import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Config {

    private ArrayList<Command> commands;

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

    public static Config readConfigFile(File configFile) {
        try {
            String content = Config.readFile(configFile.getPath(), StandardCharsets.UTF_8);
            String[] stringCommands = content.split(";");
            ArrayList<Command> commands = new ArrayList<>(stringCommands.length);

            for (int i = 0; i < stringCommands.length; i++) {
                stringCommands[i] = stringCommands[i].trim();
                String[] commandparts = stringCommands[i].split(" ");

                if (commandparts.length >= 3)
                    System.err.println("Unexpected number of items for command(" + stringCommands[i] + ") with parts " + commandparts.length);
                else if (commandparts.length == 1)
                    commands.add(new SingleCommand(commandparts[0]));
                else if (commandparts.length == 2) {
                    // TODO:
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public Config(ArrayList<Command> commands) {
        this.commands = commands;
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
