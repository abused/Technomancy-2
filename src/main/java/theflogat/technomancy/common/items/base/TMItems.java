package theflogat.technomancy.common.items.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;
import theflogat.technomancy.Technomancy;
import theflogat.technomancy.common.items.technom.ItemBoost;
import theflogat.technomancy.common.items.technom.ItemCoilCoupler;
import theflogat.technomancy.common.items.technom.ItemProcessedOre;
import theflogat.technomancy.common.items.technom.existence.ItemExistenceGem;
import theflogat.technomancy.common.items.technom.existence.ItemTreasure;
import theflogat.technomancy.common.items.tome.ItemRitualTome;
import theflogat.technomancy.lib.Ids;
import theflogat.technomancy.lib.Names;
import theflogat.technomancy.util.Ore;

public class TMItems {

	// Item Instances
    public static Item essentiaCannon;
    public static Item itemMaterial;
    public static Item itemPen;
    public static Item itemWandCores;
    public static Item itemFusionFocus;
    public static Item itemBoost = Ids.itemBoost ? new ItemBoost() : null;
    public static Item ritualTome = Ids.ritualTome ? new ItemRitualTome() : null;
    public static Item itemBM;
    public static Item itemBO;
    public static Item manaBucket;
    public static Item coilCoupler;
	public static Item itemTechnoturgeScepter;
	public static Item exGem = Ids.exGem ? new ItemExistenceGem() : null;
	public static ItemTreasure treasures = Ids.treasures ? new ItemTreasure() : null;
    
    public static void initTechnomancy() {
        registerItem(itemBoost, Names.itemBoost);
        registerItem(ritualTome, Names.ritualTome);
        registerItem(coilCoupler, Names.coilCoupler);
        registerItem(exGem, Names.exGem);
        registerItem(treasures, "treasures");
    }

    public static void registerRenders() {
        regItem(itemBoost);
        regItem(ritualTome);
        regItem(coilCoupler);
        regItem(exGem);
        regItem(treasures);
    }
    
    public static void initPureOres() {
    	for (Ore ore : Ore.ores) {
    		if (ore.getEnabled()) {
				ore.setPure(new ItemProcessedOre(ore).setRegistryName("pure" + ore.oreName().substring(3)));
				ForgeRegistries.ITEMS.register(ore.getPure());
                Technomancy.proxy.registerWithMapper(ore.getPure(), ore.getNames());
            }
    	}
    }

    public static void initPureOresRender() {
        for (Ore ore : Ore.ores) {
            if (ore.getEnabled()) {
                for (int i = 0; i < 6; i++) {
                    //ResourceLocation location = ore.getPure().getRegistryName();
                    ResourceLocation location = new ResourceLocation("technom:purebase");
                    ModelLoader.setCustomModelResourceLocation(ore.getPure(), i, new ModelResourceLocation(location, "type=" + i));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerColor() {
        for (Ore ore : Ore.ores) {
            if (ore.getEnabled()) {
                switch (ore.oreName().substring(3)) {
                    case "gold":
                        setItemColor(ore.getPure(), 0xE8EF23);
                        break;
                    case "iron":
                        setItemColor(ore.getPure(), 0xBFBFBF);
                        break;
                    case "copper":
                        setItemColor(ore.getPure(), 0xE47200);
                        break;
                    case "tin":
                        setItemColor(ore.getPure(), 0xA5C7DE);
                        break;
                    case "lead":
                        setItemColor(ore.getPure(), 0x444072);
                        break;
                    case "silver":
                        setItemColor(ore.getPure(), 0xF9F9F9);
                        break;
                    case "nickel":
                        setItemColor(ore.getPure(), 0xDEE187);
                        break;
                    case "mithril":
                        setItemColor(ore.getPure(), 0xb0c4de);
                        break;
                    case "platinum":
                        setItemColor(ore.getPure(), 0xb0e0e6);
                        break;
                    case "iridium":
                        setItemColor(ore.getPure(), 0xe0ffff);
                        break;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void setItemColor(Item item, int color){
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColorHandler(color), item);
    }
    
    private static void registerItem(Item item, String name) {
		if(item!=null) {
			ForgeRegistries.ITEMS.register(item.setRegistryName(name));
		}
	}

    public static void regItem(Item item) {
        if(item != null) {
            ModelResourceLocation res = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, res);
        }
    }
}
