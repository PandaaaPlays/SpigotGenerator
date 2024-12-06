package ca.pandaaa.spigotGenerator;

import org.bukkit.Material;
import org.bukkit.Sound;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SoundEnum {

    public static void generateEnumFile(String fileName) throws IOException {
        File pluginFolder = SpigotGenerator.getPlugin().getDataFolder();

        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }

        File enumFile = new File(pluginFolder, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(enumFile))) {
            writer.write("public enum SoundEnum {\n");

            for (Sound sound : Sound.values()) {
                String soundName = sound.name();

                // Extraire la catégorie (premier mot)
                String category = soundName.split("_")[0].toUpperCase();

                // Trouver le Material correspondant
                Material material = findMatchingMaterial(soundName);

                // Ajouter une entrée à l'enum
                writer.write(String.format("    %s(\"%s\", Material.%s, \"%s\"),\n",
                        soundName,
                        soundName,
                        material.name(),
                        category
                ));
            }

            writer.write("    ;\n\n");
            writer.write("    private final String soundName;\n");
            writer.write("    private final Material material;\n");
            writer.write("    private final String category;\n\n");
            writer.write("    SoundEnum(String soundName, Material material, String category) {\n");
            writer.write("        this.soundName = soundName;\n");
            writer.write("        this.material = material;\n");
            writer.write("        this.category = category;\n");
            writer.write("    }\n\n");
            writer.write("    public String getSoundName() {\n");
            writer.write("        return soundName;\n");
            writer.write("    }\n\n");
            writer.write("    public Material getMaterial() {\n");
            writer.write("        return material;\n");
            writer.write("    }\n\n");
            writer.write("    public String getCategory() {\n");
            writer.write("        return catergory;\n");
            writer.write("    }\n\n");
        }
    }

    private static Material findMatchingMaterial(String soundName) {
        return Arrays.stream(Material.values())
                .filter(material -> soundName.contains(material.name()))
                .findFirst()
                .orElse(Material.BARRIER); // Default material if no match is found
    }
}
