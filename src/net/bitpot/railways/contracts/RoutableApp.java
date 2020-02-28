package net.bitpot.railways.contracts;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public interface RoutableApp {
    public Module getModule();

    RoutesFiles<VirtualFile> getRoutesFiles();

    public static RoutableApp fromModule(Module module) {
        return null;
    }

    public PHPController findController(String name);

    public Project getProject();
}
