package org.citygml4j.model.cityobjectgroup;

import org.citygml4j.model.core.AbstractCityObjectProperty;
import org.citygml4j.model.core.AbstractLogicalSpace;
import org.citygml4j.model.core.AbstractThematicSurface;
import org.citygml4j.model.core.ClosureSurface;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.model.generics.GenericThematicSurface;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.common.ChildList;

import java.util.List;

public class CityObjectGroup extends AbstractLogicalSpace implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private List<RoleProperty> groupMembers;
    private AbstractCityObjectProperty groupParent;
    private List<ADEPropertyOfCityObjectGroup> adeProperties;

    @Override
    public boolean isValidBoundarySurface(AbstractThematicSurface boundarySurface) {
        return boundarySurface instanceof ClosureSurface
                || boundarySurface instanceof GenericThematicSurface;
    }

    @Override
    public Code getClassifier() {
        return classifier;
    }

    @Override
    public void setClassifier(Code classifier) {
        this.classifier = asChild(classifier);
    }

    @Override
    public List<Code> getFunctions() {
        if (functions == null)
            functions = new ChildList<>(this);

        return functions;
    }

    @Override
    public void setFunctions(List<Code> functions) {
        this.functions = asChild(functions);
    }

    @Override
    public List<Code> getUsages() {
        if (usages == null)
            usages = new ChildList<>(this);

        return usages;
    }

    @Override
    public void setUsages(List<Code> usages) {
        this.usages = asChild(usages);
    }

    public List<RoleProperty> getGroupMembers() {
        if (groupMembers == null)
            groupMembers = new ChildList<>(this);

        return groupMembers;
    }

    public void setGroupMembers(List<RoleProperty> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public AbstractCityObjectProperty getGroupParent() {
        return groupParent;
    }

    public void setGroupParent(AbstractCityObjectProperty groupParent) {
        this.groupParent = asChild(groupParent);
    }

    public List<ADEPropertyOfCityObjectGroup> getADEPropertiesOfCityObjectGroup() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfCityObjectGroup(List<ADEPropertyOfCityObjectGroup> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}