package org.citygml4j.adapter.xml.core;

import org.citygml4j.model.core.AbstractThematicSurfaceProperty;
import org.xmlobjects.gml.adapter.feature.AbstractFeaturePropertyAdapter;

import javax.xml.namespace.QName;

public class AbstractThematicSurfacePropertyAdapter extends AbstractFeaturePropertyAdapter<AbstractThematicSurfaceProperty> {

    @Override
    public AbstractThematicSurfaceProperty createObject(QName name) {
        return new AbstractThematicSurfaceProperty();
    }
}
