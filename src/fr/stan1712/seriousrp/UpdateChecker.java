package fr.stan1712.seriousrp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;

public class UpdateChecker {

	private Plugin plugin = Main.getPlugin(Main.class);
    private int resourceId;

    public UpdateChecker(Plugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

	public void getVersion(final Consumer<String> consumer) {
        try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
            }
        } catch (IOException exception) {
            this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
        }
    }
}