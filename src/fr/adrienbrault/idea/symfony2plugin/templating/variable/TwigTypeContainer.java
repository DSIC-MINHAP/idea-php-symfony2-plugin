package fr.adrienbrault.idea.symfony2plugin.templating.variable;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import com.jetbrains.php.lang.psi.elements.PhpNamedElement;
import fr.adrienbrault.idea.symfony2plugin.templating.variable.dict.PsiVariable;
import fr.adrienbrault.idea.symfony2plugin.util.PhpElementsUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TwigTypeContainer {

    private PhpNamedElement phpNamedElement;
    private String stringElement;
    private Object dataHolder;

    public TwigTypeContainer(PhpNamedElement phpNamedElement) {
        this.phpNamedElement = phpNamedElement;
    }

    public TwigTypeContainer(String stringElement) {
        this.stringElement = stringElement;
    }

    @Nullable
    public PhpNamedElement getPhpNamedElement() {
        return phpNamedElement;
    }

    @Nullable
    public String getStringElement() {
        return stringElement;
    }

    public static Collection<TwigTypeContainer> fromCollection(Project project, List<PsiVariable> psiVariables) {

        List<TwigTypeContainer> twigTypeContainerList = new ArrayList<TwigTypeContainer>();

        for(PsiVariable phpNamedElement :psiVariables) {
            Collection<PhpClass> phpClass = PhpElementsUtil.getClassFromPhpTypeSet(project, phpNamedElement.getTypes());
            if(phpClass.size() > 0) {
                twigTypeContainerList.add(new TwigTypeContainer(phpClass.iterator().next()));
            }
        }

        return twigTypeContainerList;
    }

    public TwigTypeContainer withDataHolder(Object object) {
        this.dataHolder = object;
        return this;
    }

    public Object getDataHolder() {
        return dataHolder;
    }

}