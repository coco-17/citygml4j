package org.citygml4j.adapter.xml.appearance;

import org.citygml4j.adapter.xml.CityGMLBuilderHelper;
import org.citygml4j.adapter.xml.CityGMLSerializerHelper;
import org.citygml4j.model.ade.generic.GenericADEPropertyOfParameterizedTexture;
import org.citygml4j.model.appearance.ADEPropertyOfParameterizedTexture;
import org.citygml4j.model.appearance.ParameterizedTexture;
import org.citygml4j.model.appearance.TextureAssociationProperty;
import org.citygml4j.util.CityGMLConstants;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.annotation.XMLElements;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElements({
        @XMLElement(name = "ParameterizedTexture", namespaceURI = CityGMLConstants.CITYGML_3_0_APPEARANCE_NAMESPACE),
        @XMLElement(name = "ParameterizedTexture", namespaceURI = CityGMLConstants.CITYGML_2_0_APPEARANCE_NAMESPACE),
        @XMLElement(name = "ParameterizedTexture", namespaceURI = CityGMLConstants.CITYGML_1_0_APPEARANCE_NAMESPACE)
})
public class ParameterizedTextureAdapter extends AbstractTextureAdapter<ParameterizedTexture> {
    private final QName[] substitutionGroups = new QName[] {
            new QName(CityGMLConstants.CITYGML_3_0_APPEARANCE_NAMESPACE, "AbstractGenericApplicationPropertyOfParameterizedTexture"),
            new QName(CityGMLConstants.CITYGML_2_0_APPEARANCE_NAMESPACE, "_GenericApplicationPropertyOfParameterizedTexture"),
            new QName(CityGMLConstants.CITYGML_1_0_APPEARANCE_NAMESPACE, "_GenericApplicationPropertyOfParameterizedTexture"),
    };

    @Override
    public ParameterizedTexture createObject(QName name) {
        return new ParameterizedTexture();
    }

    @Override
    public void buildChildObject(ParameterizedTexture object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isCityGMLAppearanceNamespace(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "textureParameterization":
                    object.getTextureParameterization().add(reader.getObjectUsingBuilder(TextureAssociationPropertyAdapter.class));
                    return;
                case "target":
                    object.getTextureParameterization().add(reader.getObjectUsingBuilder(org.citygml4j.adapter.xml.deprecated.appearance.TextureAssociationPropertyAdapter.class));
                    return;
            }
        }

        if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            ObjectBuilder<ADEPropertyOfParameterizedTexture> builder = reader.getXMLObjects().getBuilder(name, ADEPropertyOfParameterizedTexture.class);
            if (builder != null)
                object.getADEPropertiesOfParameterizedTexture().add(reader.getObjectUsingBuilder(builder));
            else if (CityGMLBuilderHelper.createAsGenericADEProperty(name, reader, substitutionGroups))
                object.getADEPropertiesOfParameterizedTexture().add(GenericADEPropertyOfParameterizedTexture.of(reader.getDOMElement()));
        } else
            super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public Element createElement(ParameterizedTexture object, Namespaces namespaces) {
        return Element.of(CityGMLSerializerHelper.getAppearanceNamespace(namespaces), "ParameterizedTexture");
    }

    @Override
    public void writeChildElements(ParameterizedTexture object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String appearanceNamespace = CityGMLSerializerHelper.getAppearanceNamespace(namespaces);
        boolean isCityGML3 = CityGMLConstants.CITYGML_3_0_APPEARANCE_NAMESPACE.equals(appearanceNamespace);

        for (TextureAssociationProperty property : object.getTextureParameterization()) {
            if (isCityGML3)
                writer.writeElementUsingSerializer(Element.of(appearanceNamespace, "textureParameterization"), property, TextureAssociationPropertyAdapter.class, namespaces);
            else
                writer.writeElementUsingSerializer(Element.of(appearanceNamespace, "target"), property, org.citygml4j.adapter.xml.deprecated.appearance.TextureAssociationPropertyAdapter.class, namespaces);
        }

        for (ADEPropertyOfParameterizedTexture property : object.getADEPropertiesOfParameterizedTexture())
            CityGMLSerializerHelper.serializeADEProperty(property, namespaces, writer);
    }
}