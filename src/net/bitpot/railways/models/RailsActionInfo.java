package net.bitpot.railways.models;

import com.intellij.openapi.module.Module;
import net.bitpot.railways.contracts.PHPClass;
import net.bitpot.railways.contracts.PHPMethod;
import net.bitpot.railways.gui.RailwaysIcons;
import net.bitpot.railways.utils.StormroutesPsiUtils;
import org.jetbrains.plugins.ruby.rails.model.RailsApp;
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.classes.RClass;
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.RMethod;
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.Visibility;

import javax.swing.*;
import java.util.Objects;

/**
 * Contains information about controller action.
 *
 * @author Basil Gren
 *         on 23.11.2014.
 */
public class RailsActionInfo {

    // Class which is referenced by route action, it might not have
    // implementation of the method, as the method can be inherited.
    private PHPClass psiClass = null;

    // Route action method.
    private PHPMethod psiMethod = null;


    public PHPClass getPsiClass() {
        return psiClass;
    }

    public PHPMethod getPsiMethod() {
        return psiMethod;
    }


    public Icon getIcon() {
        Visibility vis = getMethodVisibility();
        if (vis != null)
            switch (vis) {
                case PRIVATE:
                case PROTECTED:
                    return RailwaysIcons.NODE_METHOD;

                case PUBLIC:
                    return RailwaysIcons.NODE_ROUTE_ACTION;
            }

        return RailwaysIcons.NODE_ERROR;
    }


    public Visibility getMethodVisibility() {
        if (getPsiMethod() == null)
            return null;

        return psiMethod.getVisibility();
    }


    public void update(Module module, String controllerName, String actionName) {
        RailsApp app = RailsApp.fromModule(module);
        update(app, controllerName, actionName);
    }

    // TODO: cache found classes and methods to reuse found values.
    public void update(RailsApp app, String controllerShortName, String actionName) {
        if ((app == null) || controllerShortName.isEmpty()) {
            psiMethod = null;
            psiClass = null;
            return;
        }

        String qualifiedName =
                StormroutesPsiUtils.getControllerClassNameByShortName(controllerShortName);

        if (psiClass != null && (!psiClass.isValid() ||
                !Objects.equals(psiClass.getFQN().getFullPath(), qualifiedName))) {
            psiMethod = null;
            psiClass = null;
        }

        // Find psiClass if it's not specified or already invalid.
        if (psiClass == null) {
            psiClass = StormroutesPsiUtils.findControllerClass(app, qualifiedName);
        }

        if (psiClass != null) {
            if (psiMethod == null || !psiMethod.isValid()) {
                psiMethod = StormroutesPsiUtils.findControllerMethod(app,
                        psiClass, actionName);
            } else {
                // Even if psiMethod is valid, its name can be different - it
                // usually happens when user edits method name - the psiElement
                // is just updated.
                if (!actionName.equals(psiMethod.getName()))
                    psiMethod = null;
            }
        }
    }

}
