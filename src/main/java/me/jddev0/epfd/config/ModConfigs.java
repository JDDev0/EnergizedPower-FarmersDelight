package me.jddev0.epfd.config;

import com.mojang.logging.LogUtils;
import me.jddev0.ep.config.Config;
import me.jddev0.ep.config.ConfigValidationException;
import me.jddev0.ep.config.ConfigValue;
import me.jddev0.ep.config.value.*;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

public final class ModConfigs {
    private static final Logger LOGGER = LogUtils.getLogger();

    private ModConfigs() {}

    public static final Config COMMON_CONFIG = new Config(getRelativeConfigFile("common_addon_farmers_delight.conf"),
            "Energized Power - Farmer's Delight Common Config (IMPORTANT: Not all values are synced from the server to the client.)");

    //Blocks
    public static final ConfigValue<Integer> COMMON_ELECTRIC_STOVE_CAPACITY = registerEnergyCapacityConfigValue(
            "block.electric_stove", "Electric Stove", 1024
    );
    public static final ConfigValue<Integer> COMMON_ELECTRIC_STOVE_TRANSFER_RATE = registerEnergyTransferRateConfigValue(
            "block.electric_stove", "Electric Stove", 32
    );
    public static final ConfigValue<Integer> COMMON_ELECTRIC_STOVE_ENERGY_CONSUMPTION_PER_TICK = registerEnergyConsumptionPerTickConfigValue(
            "block.electric_stove", "Electric Stove", 8
    );
    public static final ConfigValue<Integer> COMMON_ELECTRIC_STOVE_RECIPE_DURATION_MULTIPLIER = registerCookingDurationMultiplierConfigValue(
            "block.electric_stove", "Electric Stove", 2
    );

    public static final ConfigValue<Integer> COMMON_INDUCTION_STOVE_CAPACITY = registerEnergyCapacityConfigValue(
            "block.induction_stove", "Induction Stove", 2048
    );
    public static final ConfigValue<Integer> COMMON_INDUCTION_STOVE_TRANSFER_RATE = registerEnergyTransferRateConfigValue(
            "block.induction_stove", "Induction Stove", 64
    );
    public static final ConfigValue<Integer> COMMON_INDUCTION_STOVE_ENERGY_CONSUMPTION_PER_TICK = registerEnergyConsumptionPerTickConfigValue(
            "block.induction_stove", "Induction Stove", 16
    );
    public static final ConfigValue<Integer> COMMON_INDUCTION_STOVE_RECIPE_DURATION_MULTIPLIER = registerCookingDurationMultiplierConfigValue(
            "block.induction_stove", "Induction Stove", 3
    );

    private static File getRelativeConfigFile(String fileName) {
        return FMLPaths.CONFIGDIR.get().resolve("energizedpower/" + fileName).toFile();
    }

    private static ConfigValue<Integer> registerEnergyCapacityConfigValue(String baseConfigKey, String itemName, int defaultValue) {
        return COMMON_CONFIG.register(new IntegerConfigValue(
                baseConfigKey + ".capacity",
                "The energy capacity of the " + itemName + " in FE",
                defaultValue,
                1, null
        ));
    }
    private static ConfigValue<Integer> registerEnergyTransferRateConfigValue(String baseConfigKey, String itemName, int defaultValue) {
        return COMMON_CONFIG.register(new IntegerConfigValue(
                baseConfigKey + ".transfer_rate",
                "The energy transfer rate of the " + itemName + " in FE per tick",
                defaultValue,
                1, null
        ));
    }
    private static ConfigValue<Integer> registerEnergyConsumptionPerTickConfigValue(String baseConfigKey, String itemName, int defaultValue) {
        return COMMON_CONFIG.register(new IntegerConfigValue(
                baseConfigKey + ".energy_consumption_per_tick",
                "The energy consumption of the " + itemName + " if active in FE per tick",
                defaultValue,
                1, null
        ));
    }
    private static ConfigValue<Integer> registerCookingDurationMultiplierConfigValue(String baseConfigKey, String itemName, int defaultValue) {
        return COMMON_CONFIG.register(new IntegerConfigValue(
                baseConfigKey + ".recipe_duration_multiplier",
                "The multiplier by which the time a recipe of the skillet and cooking pot using the " + itemName +
                        " requires is multiplied by (Integer only).",
                defaultValue,
                1, null
        ));
    }

    public static void registerConfigs(boolean isServer) {
        if(!COMMON_CONFIG.isLoaded()) {
            try {
                COMMON_CONFIG.read();

                LOGGER.info("Energized Power common config was successfully loaded");
            }catch(IOException | ConfigValidationException e) {
                LOGGER.error("Energized Power common config could not be read", e);
            }
        }
    }
}
