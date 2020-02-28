package net.bitpot.railways.contracts;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

public interface PHPClass extends PsiElement {
    Project getProject();

    String getQualifiedName();

    PHPSuperClass getPsiSuperClass();
}
