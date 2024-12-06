package ca.pandaaa.spigotGenerator;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class SpigotGenerator extends JavaPlugin {

    private static SpigotGenerator instance;

    private final boolean generateSoundEnum = true;

    @Override
    public void onEnable() {
        instance = this;

        if(generateSoundEnum) {
            try {
                SoundEnum.generateEnumFile("SoundEnum.java");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SpigotGenerator getPlugin() {
        return instance;
    }
}
