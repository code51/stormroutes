package net.bitpot.railways.facades;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;

public class PHPProjectAndLibrariesScope extends GlobalSearchScope {
    public PHPProjectAndLibrariesScope(Project project) {
    }

    @Override
    public int compare(@NotNull VirtualFile virtualFile, @NotNull VirtualFile virtualFile1) {
        return 0;
    }

    @Override
    public boolean isSearchInModuleContent(@NotNull Module module) {
        return false;
    }

    @Override
    public boolean isSearchInLibraries() {
        return false;
    }

    @Override
    public boolean contains(@NotNull VirtualFile virtualFile) {
        return false;
    }
}
