package org.citygml4j.model.ade.generic;

import org.citygml4j.model.construction.ADEPropertyOfInteriorWallSurface;
import org.w3c.dom.Element;

public class GenericADEPropertyOfInteriorWallSurface extends ADEPropertyOfInteriorWallSurface<Element> implements ADEGenericProperty {

    private GenericADEPropertyOfInteriorWallSurface(Element value) {
        super(value);
    }

    public static GenericADEPropertyOfInteriorWallSurface of(Element value) {
        return new GenericADEPropertyOfInteriorWallSurface(value);
    }
}