package xyz.larkyy.jsonmenutitles.plugin_spigot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.larkyy.jsonmenutitles.nms_api.nms_api.NMSHandler;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        nmsHandler().inject(e.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        nmsHandler().eject(e.getPlayer());
    }

    private NMSHandler nmsHandler() {
        return JsonMenuTitles.getInstance().getNmsHandler();
    }

}
