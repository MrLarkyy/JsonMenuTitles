package xyz.larkyy.jsonmenutitles.plugin_spigot;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.larkyy.jsonmenutitles.nms_1_19_3.nms_1_19_3.NMS_1_19_3;
import xyz.larkyy.jsonmenutitles.nms_1_19_4.nms_1_19_4.NMS_1_19_4;
import xyz.larkyy.jsonmenutitles.nms_api.nms_api.NMSHandler;

public final class JsonMenuTitles extends JavaPlugin {

    private static JsonMenuTitles instance;
    private NMSHandler nmsHandler;

    @Override
    public void onLoad() {
        instance = this;
        switch (getServer().getBukkitVersion()) {
            case "1.19.3-R0.1-SNAPSHOT" -> {
                nmsHandler = new NMS_1_19_3();
            }
            case "1.19.4-R0.1-SNAPSHOT" -> {
                nmsHandler = new NMS_1_19_4();
            }
        }
    }

    @Override
    public void onEnable() {
        nmsHandler.injectAll();
        getServer().getPluginManager().registerEvents(new Listeners(),this);
    }

    @Override
    public void onDisable() {
        nmsHandler.ejectAll();
    }

    public NMSHandler getNmsHandler() {
        return nmsHandler;
    }

    public static JsonMenuTitles getInstance() {
        return instance;
    }
}
