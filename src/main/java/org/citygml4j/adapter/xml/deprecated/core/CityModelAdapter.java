package org.citygml4j.adapter.xml.deprecated.core;

import org.citygml4j.adapter.xml.CityGMLBuilderHelper;
import org.citygml4j.adapter.xml.CityGMLSerializerHelper;
import org.citygml4j.adapter.xml.appearance.AppearancePropertyAdapter;
import org.citygml4j.adapter.xml.core.AbstractCityObjectPropertyAdapter;
import org.citygml4j.model.ade.generic.GenericADEPropertyOfCityModel;
import org.citygml4j.model.appearance.AppearanceProperty;
import org.citygml4j.model.core.ADEPropertyOfCityModel;
import org.citygml4j.model.core.AbstractCityObjectProperty;
import org.citygml4j.model.core.CityModel;
import org.citygml4j.util.CityGMLConstants;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.annotation.XMLElements;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.gml.adapter.GMLBuilderHelper;
import org.xmlobjects.gml.adapter.feature.AbstractFeatureAdapter;
import org.xmlobjects.gml.adapter.feature.FeatureArrayPropertyAdapter;
import org.xmlobjects.gml.adapter.feature.FeaturePropertyAdapter;
import org.xmlobjects.gml.model.common.GenericElement;
import org.xmlobjects.gml.model.feature.AbstractFeature;
import org.xmlobjects.gml.model.feature.FeatureArrayProperty;
import org.xmlobjects.gml.model.feature.FeatureProperty;
import org.xmlobjects.gml.util.GMLConstants;
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
        @XMLElement(name = "CityModel", namespaceURI = CityGMLConstants.CITYGML_2_0_CORE_NAMESPACE),
        @XMLElement(name = "CityModel", namespaceURI = CityGMLConstants.CITYGML_1_0_CORE_NAMESPACE)
})
public class CityModelAdapter extends AbstractFeatureAdapter<CityModel> {
    private final QName[] substitutionGroups = new QName[] {
            new QName(CityGMLConstants.CITYGML_2_0_CORE_NAMESPACE, "_GenericApplicationPropertyOfCityModel"),
            new QName(CityGMLConstants.CITYGML_1_0_CORE_NAMESPACE, "_GenericApplicationPropertyOfCityModel"),
    };

    @Override
    public CityModel createObject(QName name) {
        return new CityModel();
    }

    @Override
    public void buildChildObject(CityModel object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isCityGMLCoreNamespace(name.getNamespaceURI()) && "cityObjectMember".equals(name.getLocalPart())) {
            object.getCityObjectMembers().add(reader.getObjectUsingBuilder(AbstractCityObjectPropertyAdapter.class));
            return;
        } else if (CityGMLBuilderHelper.isCityGMLAppearanceNamespace(name.getNamespaceURI()) && "appearanceMember".equals(name.getLocalPart())) {
            object.getAppearanceMembers().add(reader.getObjectUsingBuilder(AppearancePropertyAdapter.class));
            return;
        } else if (GMLBuilderHelper.isGMLNamespace(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "featureMember":
                    object.getFeatureMembers().add(reader.getObjectUsingBuilder(FeaturePropertyAdapter.class));
                    return;
                case "featureMembers":
                    FeatureArrayProperty<AbstractFeature> featureMembers = reader.getObjectUsingBuilder(FeatureArrayPropertyAdapter.class);
                    for (AbstractFeature feature : featureMembers.getObjects())
                        object.getFeatureMembers().add(new FeatureProperty<>(feature));

                    for (GenericElement element : featureMembers.getGenericElements())
                        object.getFeatureMembers().add(new FeatureProperty(element));
                    return;
                default:
                    super.buildChildObject(object, name, attributes, reader);
                    return;
            }
        }

        ObjectBuilder<ADEPropertyOfCityModel> builder = reader.getXMLObjects().getBuilder(name, ADEPropertyOfCityModel.class);
        if (builder != null)
            object.getADEPropertiesOfCityModel().add(reader.getObjectUsingBuilder(builder));
        else if (CityGMLBuilderHelper.createAsGenericADEProperty(name, reader, substitutionGroups))
            object.getADEPropertiesOfCityModel().add(GenericADEPropertyOfCityModel.of(reader.getDOMElement()));
    }

    @Override
    public Element createElement(CityModel object, Namespaces namespaces) {
        return Element.of(CityGMLSerializerHelper.getCoreNamespace(namespaces), "CityModel");
    }

    @Override
    public void writeChildElements(CityModel object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String coreNamespace = CityGMLSerializerHelper.getCoreNamespace(namespaces);

        for (AbstractCityObjectProperty property : object.getCityObjectMembers())
            writer.writeElementUsingSerializer(Element.of(coreNamespace, "cityObjectMember"), property, AbstractCityObjectPropertyAdapter.class, namespaces);

        for (AppearanceProperty property : object.getAppearanceMembers())
            writer.writeElementUsingSerializer(Element.of(coreNamespace, "appearanceMember"), property, AppearancePropertyAdapter.class, namespaces);

        for (FeatureProperty property : object.getFeatureMembers())
            writer.writeElementUsingSerializer(Element.of(GMLConstants.GML_3_1_NAMESPACE, "featureMember"), property, FeaturePropertyAdapter.class, namespaces);

        for (ADEPropertyOfCityModel property : object.getADEPropertiesOfCityModel())
            CityGMLSerializerHelper.serializeADEProperty(property, namespaces, writer);
    }
}
