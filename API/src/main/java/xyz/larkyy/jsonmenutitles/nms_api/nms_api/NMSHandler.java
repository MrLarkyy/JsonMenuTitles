package xyz.larkyy.jsonmenutitles.nms_api.nms_api;

import org.bukkit.entity.Player;

public abstract class NMSHandler {

    public abstract void inject(Player player);
    public abstract void eject(Player player);
    public abstract void injectAll();
    public abstract void ejectAll();

}
