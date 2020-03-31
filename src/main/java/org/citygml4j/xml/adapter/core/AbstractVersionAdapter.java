package org.citygml4j.xml.adapter.core;

import org.citygml4j.model.ade.generic.GenericADEPropertyOfAbstractVersion;
import org.citygml4j.model.core.ADEPropertyOfAbstractVersion;
import org.citygml4j.model.core.AbstractVersion;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.CityGMLBuilderHelper;
import org.citygml4j.xml.adapter.CityGMLSerializerHelper;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public abstract class AbstractVersionAdapter<T extends AbstractVersion> extends AbstractFeatureWithLifespanAdapter<T> {
    private final QName substitutionGroup = new QName(CityGMLConstants.CITYGML_3_0_VERSIONING_NAMESPACE, "AbstractGenericApplicationPropertyOfAbstractVersion");

    @Override
    public void buildChildObject(T object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI()))
            buildADEProperty(object, name, reader);
        else
            super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void buildADEProperty(T object, QName name, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (!CityGMLBuilderHelper.addADEProperty(name, ADEPropertyOfAbstractVersion.class, object.getADEPropertiesOfAbstractVersion(),
                GenericADEPropertyOfAbstractVersion::of, reader, substitutionGroup))
            super.buildADEProperty(object, name, reader);
    }

    @Override
    public void writeChildElements(T object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);

        for (ADEPropertyOfAbstractVersion<?> property : object.getADEPropertiesOfAbstractVersion())
            CityGMLSerializerHelper.serializeADEProperty(property, namespaces, writer);
    }
}