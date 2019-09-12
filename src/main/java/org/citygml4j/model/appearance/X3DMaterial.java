package org.citygml4j.model.appearance;

import org.xmlobjects.gml.model.common.ChildList;

import java.util.ArrayList;
import java.util.List;

public class X3DMaterial extends AbstractSurfaceData {
    private Double ambientIntensity;
    private Color diffuseColor;
    private Color emissiveColor;
    private Color specularColor;
    private Double shininess;
    private Double transparency;
    private Boolean isSmooth;
    private List<String> targets;
    private List<ADEPropertyOfX3DMaterial> adeProperties;

    public Double getAmbientIntensity() {
        return ambientIntensity != null ? ambientIntensity : 0.2;
    }

    public void setAmbientIntensity(Double ambientIntensity) {
        if (ambientIntensity == null || (ambientIntensity >= 0 && ambientIntensity <= 1))
            this.ambientIntensity = ambientIntensity;
    }

    public Color getDiffuseColor() {
        return diffuseColor != null ? diffuseColor : new Color(0.8);
    }

    public void setDiffuseColor(Color diffuseColor) {
        this.diffuseColor = diffuseColor;
    }

    public Color getEmissiveColor() {
        return emissiveColor != null ? emissiveColor : new Color(0);
    }

    public void setEmissiveColor(Color emissiveColor) {
        this.emissiveColor = emissiveColor;
    }

    public Color getSpecularColor() {
        return specularColor != null ? specularColor : new Color(1);
    }

    public void setSpecularColor(Color specularColor) {
        this.specularColor = specularColor;
    }

    public Double getShininess() {
        return shininess != null ? shininess : 0.2;
    }

    public void setShininess(Double shininess) {
        if (shininess == null || (shininess >= 0 && shininess <= 1))
            this.shininess = shininess;
    }

    public Double getTransparency() {
        return transparency != null ? transparency : 0;
    }

    public void setTransparency(Double transparency) {
        if (transparency == null || (transparency >= 0 && transparency <= 1))
            this.transparency = transparency;
    }

    public Boolean getSmooth() {
        return isSmooth != null ? isSmooth : false;
    }

    public void setSmooth(Boolean smooth) {
        isSmooth = smooth;
    }

    public List<String> getTargets() {
        if (targets == null)
            targets = new ArrayList<>();

        return targets;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

    public List<ADEPropertyOfX3DMaterial> getADEPropertiesOfX3DMaterial() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfX3DMaterial(List<ADEPropertyOfX3DMaterial> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}
