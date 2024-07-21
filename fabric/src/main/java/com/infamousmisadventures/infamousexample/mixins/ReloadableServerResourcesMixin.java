package com.infamousmisadventures.infamousexample.mixins;

import com.infamousmisadventures.infamousexample.datapack.DatapackReloadListener;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = ReloadableServerResources.class)
public class ReloadableServerResourcesMixin {

    @Inject(method= "listeners()Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    private void example$listeners(CallbackInfoReturnable<List<PreparableReloadListener>> cir){
        List<PreparableReloadListener> listeners = new ArrayList<>();
        listeners.addAll(cir.getReturnValue());
        listeners.addAll(DatapackReloadListener.reloadListeners());
        cir.setReturnValue(listeners);
    }
}
