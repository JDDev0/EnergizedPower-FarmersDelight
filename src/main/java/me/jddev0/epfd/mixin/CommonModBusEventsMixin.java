package me.jddev0.epfd.mixin;

import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.event.CommonModBusEvents;

@Mixin(CommonModBusEvents.class)
public abstract class CommonModBusEventsMixin {
    @Inject(method = "onModifyDefaultComponents", at = @At("HEAD"), cancellable = true)
    private static void onModifyDefaultComponents(ModifyDefaultComponentsEvent event, CallbackInfo ci) {
        //Fix crash in Farmer's Delight during datagen

        if(DatagenModLoader.isRunningDataGen())
            ci.cancel();
    }
}
