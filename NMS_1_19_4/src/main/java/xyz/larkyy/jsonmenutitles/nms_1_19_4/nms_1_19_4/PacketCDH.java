package xyz.larkyy.jsonmenutitles.nms_1_19_4.nms_1_19_4;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

public class PacketCDH extends ChannelDuplexHandler {

    private final Player player;

    public PacketCDH(Player player) {
        this.player = player;
    }
    @Override
    public void write(ChannelHandlerContext ctx, Object packetObj, ChannelPromise promise) throws Exception {
        if (!(packetObj instanceof Packet<?> pkt)) {
            super.write(ctx,packetObj,promise);
            return;
        }
        var name = pkt.getClass().getSimpleName();
        if (name.equalsIgnoreCase("packetplayoutopenwindow")) {
            ClientboundOpenScreenPacket packet = (ClientboundOpenScreenPacket) pkt;
            var title = packet.getTitle();
            packetObj = new ClientboundOpenScreenPacket(
                    packet.getContainerId(),
                    packet.getType(),
                    CraftChatMessage.fromJSONOrString(title.getString())
            );
        }
        super.write(ctx, packetObj, promise);
    }

}
