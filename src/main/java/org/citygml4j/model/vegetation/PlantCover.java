package org.citygml4j.model.vegetation;

import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.model.deprecated.vegetation.DeprecatedPropertiesOfPlantCover;
import org.citygml4j.util.Envelopes;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.model.measures.Length;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class PlantCover extends AbstractVegetationObject implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private Length averageHeight;
    private Length minHeight;
    private Length maxHeight;
    private List<ADEPropertyOfPlantCover<?>> adeProperties;

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

    public Length getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(Length averageHeight) {
        this.averageHeight = asChild(averageHeight);
    }

    public Length getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Length minHeight) {
        this.minHeight = asChild(minHeight);
    }

    public Length getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Length maxHeight) {
        this.maxHeight = asChild(maxHeight);
    }

    @Override
    public DeprecatedPropertiesOfPlantCover getDeprecatedProperties() {
        return (DeprecatedPropertiesOfPlantCover) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedPropertiesOfPlantCover createDeprecatedProperties() {
        return new DeprecatedPropertiesOfPlantCover();
    }

    public List<ADEPropertyOfPlantCover<?>> getADEPropertiesOfPlantCover() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfPlantCover(List<ADEPropertyOfPlantCover<?>> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPlantCover properties = getDeprecatedProperties();

            if (properties.getLod1MultiSurface() != null && properties.getLod1MultiSurface().getObject() != null)
                envelope.include(properties.getLod1MultiSurface().getObject().computeEnvelope());

            if (properties.getLod4MultiSurface() != null && properties.getLod4MultiSurface().getObject() != null)
                envelope.include(properties.getLod4MultiSurface().getObject().computeEnvelope());

            if (properties.getLod1MultiSolid() != null && properties.getLod1MultiSolid().getObject() != null)
                envelope.include(properties.getLod1MultiSolid().getObject().computeEnvelope());

            if (properties.getLod2MultiSolid() != null && properties.getLod2MultiSolid().getObject() != null)
                envelope.include(properties.getLod2MultiSolid().getObject().computeEnvelope());

            if (properties.getLod3MultiSolid() != null && properties.getLod3MultiSolid().getObject() != null)
                envelope.include(properties.getLod3MultiSolid().getObject().computeEnvelope());

            if (properties.getLod4MultiSolid() != null && properties.getLod4MultiSolid().getObject() != null)
                envelope.include(properties.getLod4MultiSolid().getObject().computeEnvelope());
        }

        if (adeProperties != null) {
            for (ADEPropertyOfPlantCover<?> property : adeProperties)
                Envelopes.updateEnvelope(property, envelope, options);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}