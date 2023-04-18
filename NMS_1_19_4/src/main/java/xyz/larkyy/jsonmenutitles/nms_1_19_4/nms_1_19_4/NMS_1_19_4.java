package xyz.larkyy.jsonmenutitles.nms_1_19_4.nms_1_19_4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.larkyy.jsonmenutitles.nms_api.nms_api.NMSHandler;

public final class NMS_1_19_4 extends NMSHandler {

    @Override
    public void inject(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerGamePacketListenerImpl packetListener = craftPlayer.getHandle().connection;
        Connection connection;
        try {
            var field = packetListener.getClass().getDeclaredField("h");
            field.setAccessible(true);
            connection = (Connection) field.get(packetListener);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        var pipeline = connection.channel.pipeline();

        PacketCDH cdh = new PacketCDH(player);

        for (String str : pipeline.toMap().keySet()) {
            if (pipeline.get(str) instanceof Connection) {
                pipeline.addBefore("packet_handler", "InventoryJsonTitles_packet_reader", cdh);
                break;
            }
        }
    }

    @Override
    public void eject(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerGamePacketListenerImpl packetListener = craftPlayer.getHandle().connection;
        Connection connection;
        try {
            var field = packetListener.getClass().getDeclaredField("h");
            field.setAccessible(true);
            connection = (Connection) field.get(packetListener);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Channel channel = connection.channel;
        if (channel != null) {
            try {
                if (channel.pipeline().names().contains("InventoryJsonTitles_packet_reader")) {
                    channel.pipeline().remove("InventoryJsonTitles_packet_reader");
                }
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void injectAll() {
        Bukkit.getOnlinePlayers().forEach(this::inject);
    }

    @Override
    public void ejectAll() {
        Bukkit.getOnlinePlayers().forEach(this::eject);
    }
}
