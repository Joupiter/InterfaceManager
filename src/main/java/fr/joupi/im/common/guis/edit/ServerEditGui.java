package fr.joupi.im.common.guis.edit;

import be.alexandre01.dnplugin.api.objects.server.DNServer;
import fr.joupi.im.common.guis.ServerListGui;
import fr.joupi.im.InterfaceManager;
import fr.joupi.im.utils.Utils;
import fr.joupi.im.utils.gui.Gui;
import fr.joupi.im.utils.gui.GuiButton;
import fr.joupi.im.utils.item.ItemBuilder;
import fr.joupi.im.utils.item.XMaterial;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
public class ServerEditGui extends Gui<InterfaceManager> {

    private final DNServer server;

    public ServerEditGui(InterfaceManager plugin, DNServer server) {
        super(plugin, "&7» &a" + server.getName().split("/")[1], 5);
        this.server = server;
    }

    @Override
    public void setup() {
        setItems(getBorders(), XMaterial.CYAN_STAINED_GLASS_PANE.parseItem());

        setItem(20, new GuiButton(new ItemBuilder(Material.BARRIER).setName("&7» &cÉteindre le serveur").build(), event -> {
            getServer().stop();
            close((Player) event.getWhoClicked());
            Utils.sendMessages((Player) event.getWhoClicked(), "&aLe serveur &b" + getServer().getName().split("/")[1] + " &as'est éteint");
        }));

        setItem(22, new GuiButton(new ItemBuilder(Material.PAINTING).setName("&7» &eMaintenance").build(),
                event -> new ServerWhitelistGui(getPlugin(), getServer(), false).onOpen((Player) event.getWhoClicked())));

        setItem(24, new GuiButton(new ItemBuilder(Material.CHEST).setName("&7» &6Joueurs").build(),
                event -> new ServerPlayerListGui(getPlugin(), getServer(), false).onOpen((Player) event.getWhoClicked())));

        setItem(40, new GuiButton(new ItemBuilder(Material.WOOD_DOOR).setName("&7» &cRetour à la liste des serveurs").build(),
                event -> new ServerListGui(getPlugin(), getServer().getRemoteExecutor()).onOpen((Player) event.getWhoClicked())));
    }
/*
⣿⣿⣿⣿⣿⣿⣿⡟⣫⣥⣶⣶⣦⣬⣍⠛⣩⣴⣶⣬⣍⠻⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⡿⠋⣾⣿⢛⣩⣥⣶⣶⣤⣄⢻⣛⣋⣉⣉⣓⠈⢿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⠟⣵⣿⣿⣿⡿⠿⠿⠿⡿⠿⠿⢦⡹⣿⣿⣿⣿⣿⣶⡝⢿⣿⣿⣿⣿
⣿⣿⣿⢃⣾⣿⢟⠭⠒⢊⣉⣁⣨⡍⠉⡙⠲⡤⠿⠿⣿⠝⠓⢂⣉⣘⠛⠛⠿⣿
⣿⣿⠏⣾⡿⢋⣁⠰⢠⣛⡛⠛⠛⠛⢳⣾⡄⣔⠒⣤⠄⡰⠉⠙⣛⠲⠝⣿⡆⢀
⣿⡿⠚⣁⣴⣿⣿⣿⡀⣯⣄⡉⡀⠛⢀⣿⠇⣏⢂⡇⡆⢨⡀⠾⢸⡿⠇⢹⡇⢸
⣿⠓⣾⣿⣿⣿⣿⣿⣧⡘⢭⣙⣓⣒⠦⠋⠔⢓⣢⣇⡲⡈⠦⣴⣒⣂⡡⠏⣠⣿
⢃⡇⣿⣿⡿⢛⣻⣿⣿⣿⣶⣤⣤⣤⠖⢁⣴⣿⣿⣿⣿⣮⣒⢤⣤⣤⡔⠺⣿⣿
⢸⣷⣿⣿⣴⢫⡭⠙⠻⠿⣿⣛⣉⠉⠉⠁⠁⡀⡀⢹⡄⡀⠈⠉⢁⣿⠃⠆⣽⣿
⡌⢿⣿⣿⣿⣦⣝⠷⣮⣕⡒⠦⢭⣉⣉⣙⣒⣛⣓⣛⣓⣚⣛⣉⣉⡠⠊⣼⣿⣿
⣿⣦⠻⣿⣿⣿⣿⣷⣦⣭⣙⠛⠶⢶⣦⣬⣭⣍⣩⣭⣭⣭⣭⣭⠴⠶⢃⣿⣿⣿
⣿⣿⣷⣌⡻⢿⣿⣿⣿⣿⣿⣿⣷⣶⣦⣤⣤⣤⣤⣤⣤⣤⣤⣤⣶⠆⣿⣿⣿⣿
⠈⠛⠿⣿⣿⣶⣬⣝⡛⠿⠿⣿⣿⣿⣿⣿⣿⣷⡄⡀⡀⣠⣿⣿⠏⠈⠛⢿⣿⣿
⡀⡀⡀⡀⠉⠛⠿⣿⣿⣿⣶⡶⡀⠙⠛⠻⠿⠛⠻⠶⠞⠛⠛⠡⢾⡀⡀⡀⠙⢿
⡀⡀⡀⡀⡀⡀⡀⠙⠻⣿⡀⡀⡀⡀⣀⣀⠘⢃⡀⡀⡀⡀⡀⣸⡀⡀⡀⡀⠈⡀
⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⠈⠳⠶⠂⢻⣿⣯⣿⣿⠜⠗⠒⠒⠚⠁⡀⡀⡀⡀⡀
 */
}