package net.bitpot.railways.contracts;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;

public interface RoutableApp {
    public Module getModule();

    RoutesFiles<VirtualFile> getRoutesFiles();
}
