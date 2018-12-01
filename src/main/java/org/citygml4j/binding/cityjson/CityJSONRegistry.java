package org.citygml4j.binding.cityjson;

import org.citygml4j.binding.cityjson.feature.AbstractCityObjectType;
import org.citygml4j.binding.cityjson.feature.BridgeConstructionElementType;
import org.citygml4j.binding.cityjson.feature.BridgeInstallationType;
import org.citygml4j.binding.cityjson.feature.BridgePartType;
import org.citygml4j.binding.cityjson.feature.BridgeType;
import org.citygml4j.binding.cityjson.feature.BuildingInstallationType;
import org.citygml4j.binding.cityjson.feature.BuildingPartType;
import org.citygml4j.binding.cityjson.feature.BuildingType;
import org.citygml4j.binding.cityjson.feature.CityFurnitureType;
import org.citygml4j.binding.cityjson.feature.CityObjectGroupType;
import org.citygml4j.binding.cityjson.feature.GenericCityObjectType;
import org.citygml4j.binding.cityjson.feature.LandUseType;
import org.citygml4j.binding.cityjson.feature.PlantCoverType;
import org.citygml4j.binding.cityjson.feature.RailwayType;
import org.citygml4j.binding.cityjson.feature.RoadType;
import org.citygml4j.binding.cityjson.feature.SolitaryVegetationObjectType;
import org.citygml4j.binding.cityjson.feature.TINReliefType;
import org.citygml4j.binding.cityjson.feature.TransportSquareType;
import org.citygml4j.binding.cityjson.feature.TunnelInstallationType;
import org.citygml4j.binding.cityjson.feature.TunnelPartType;
import org.citygml4j.binding.cityjson.feature.TunnelType;
import org.citygml4j.binding.cityjson.feature.WaterBodyType;
import org.citygml4j.binding.cityjson.geometry.SemanticsType;
import org.citygml4j.model.citygml.ade.ADEException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CityJSONRegistry {
    private static CityJSONRegistry instance;

    private final Map<String, Class<? extends AbstractCityObjectType>> types;
    private final Map<String, Class<? extends SemanticsType>> semanticSurfaces;
    private final Map<Class<? extends AbstractCityObjectType>, Map<String, Class<?>>> attributes;

    private CityJSONRegistry() {
        types = new ConcurrentHashMap<>();
        semanticSurfaces = new ConcurrentHashMap<>();
        attributes = new ConcurrentHashMap<>();

        registerType("Building", BuildingType.class);
        registerType("BuildingPart", BuildingPartType.class);
        registerType("BuildingInstallation", BuildingInstallationType.class);
        registerType("Bridge", BridgeType.class);
        registerType("BridgePart", BridgePartType.class);
        registerType("BridgeInstallation", BridgeInstallationType.class);
        registerType("BridgeConstructionElement", BridgeConstructionElementType.class);
        registerType("TINRelief", TINReliefType.class);
        registerType("WaterBody", WaterBodyType.class);
        registerType("PlantCover", PlantCoverType.class);
        registerType("SolitaryVegetationObject", SolitaryVegetationObjectType.class);
        registerType("LandUse", LandUseType.class);
        registerType("CityFurniture", CityFurnitureType.class);
        registerType("GenericCityObject", GenericCityObjectType.class);
        registerType("Road", RoadType.class);
        registerType("Railway", RailwayType.class);
        registerType("TransportSquare", TransportSquareType.class);
        registerType("Tunnel", TunnelType.class);
        registerType("TunnelPart", TunnelPartType.class);
        registerType("TunnelInstallation", TunnelInstallationType.class);
        registerType("CityObjectGroup", CityObjectGroupType.class);
    }

    public static synchronized CityJSONRegistry getInstance() {
        if (instance == null)
            instance = new CityJSONRegistry();

        return instance;
    }

    public String getCityObjectType(AbstractCityObjectType cityObject) {
        String type = null;
        for (Map.Entry<String, Class<? extends AbstractCityObjectType>> entry : types.entrySet()) {
            if (cityObject.getClass() == entry.getValue()) {
                type = entry.getKey();
                break;
            }
        }

        if (type == null)
            type = cityObject.getClass().getTypeName();

        return type;
    }

    public Class<?> getCityObjectClass(String type) {
        Class<?> typeClass = types.get(type);
        if (typeClass == null) {
            try {
                Class<?> tmp = Class.forName(type);
                if (AbstractCityObjectType.class.isAssignableFrom(tmp))
                    typeClass = tmp;
            } catch (ClassNotFoundException e) {
                //
            }
        }

        return typeClass;
    }

    public void registerCityObject(String type, Class<? extends AbstractCityObjectType> typeClass) throws ADEException {
        if (type == null)
            throw new ADEException("The city object type must not be null.");

        if (!type.startsWith("+"))
            throw new ADEException("The city object type '" + type + "' must start with a '+'.");

        if (typeClass == null)
            throw new ADEException("The city object type class must not be null.");

        if (types.containsKey(type))
            throw new ADEException("The city object type '" + type + "' is already registered.");

        if (types.containsValue(typeClass))
            throw new ADEException("The city object type class '" + typeClass.getTypeName() + "' is already registered.");

        registerType(type, typeClass);
    }

    private void registerType(String type, Class<? extends AbstractCityObjectType> typeClass) {
        types.put(type, typeClass);
    }

    public void unregisterCityObject(String type) throws ADEException {
        if (type == null)
            throw new ADEException("The city object type must not be null.");

        if (!type.startsWith("+"))
            throw new ADEException("The city object type '" + type + "' must start with a '+'.");

        types.remove(type);
    }

    public String getSemanticSurfaceType(SemanticsType semanticsType) {
        String type = null;
        for (Map.Entry<String, Class<? extends SemanticsType>> entry : semanticSurfaces.entrySet()) {
            if (semanticsType.getClass() == entry.getValue()) {
                type = entry.getKey();
                break;
            }
        }

        if (type == null)
            type = semanticsType.getClass().getTypeName();

        return type;
    }

    public Class<?> getSemanticSurfaceClass(String type) {
        Class<?> typeClass = semanticSurfaces.get(type);
        if (typeClass == null) {
            try {
                Class<?> tmp = Class.forName(type);
                if (SemanticsType.class.isAssignableFrom(tmp))
                    typeClass = tmp;
            } catch (ClassNotFoundException e) {
                //
            }
        }

        return typeClass;
    }

    public void registerSemanticSurface(String type, Class<? extends SemanticsType> semanticSurfaceClass) throws ADEException {
        if (type == null)
            throw new ADEException("The semantic surface type must not be null.");

        if (!type.startsWith("+"))
            throw new ADEException("The semantic surface type '" + type + "' must start with a '+'.");

        if (semanticSurfaceClass == null)
            throw new ADEException("The semantic surface class must not be null.");

        if (semanticSurfaces.containsKey(type))
            throw new ADEException("The semantic surface type '" + type + "' is already registered.");

        if (semanticSurfaces.containsValue(semanticSurfaceClass))
            throw new ADEException("The semantic surface class '" + semanticSurfaceClass.getTypeName() + "' is already registered.");

        semanticSurfaces.put(type, semanticSurfaceClass);
    }

    public void unregisterSemanticSurface(String type) throws ADEException {
        if (type == null)
            throw new ADEException("The semantic surface type must not be null.");

        if (!type.startsWith("+"))
            throw new ADEException("The semantic surface type '" + type + "' must start with a '+'.");

        semanticSurfaces.remove(type);
    }

    public Class<?> getExtensionAttributeClass(String propertyName, AbstractCityObjectType target) {
        for (Map.Entry<Class<? extends AbstractCityObjectType>, Map<String, Class<?>>> entry : attributes.entrySet()) {
            if (entry.getKey().isInstance(target))
                return entry.getValue().get(propertyName);
        }

        return null;
    }

    public void registerExtensionAttribute(String name, Class<?> attributeClass, Class<? extends AbstractCityObjectType> targetClass) throws ADEException {
        if (name == null)
            throw new ADEException("The extension attribute name must not be null.");

        if (!name.startsWith("+"))
            throw new ADEException("The extension attribute name '" + name + "' must start with a '+'.");

        if (attributeClass == null)
            throw new ADEException("The extension attribute class must not be null.");

        if (targetClass == null)
            throw new ADEException("The extension attribute target class must not be null.");

        for (Map.Entry<Class<? extends AbstractCityObjectType>, Map<String, Class<?>>> entry : attributes.entrySet()) {
            if (entry.getKey().isAssignableFrom(targetClass) || targetClass.isAssignableFrom(entry.getKey())) {
                if (entry.getValue().containsKey(name))
                    throw new ADEException("The extension attribute '" + name + "' is already registered with " + entry.getKey().getTypeName());
            }
        }

        Map<String, Class<?>> attribute = attributes.computeIfAbsent(targetClass, v -> new ConcurrentHashMap<>());
        attribute.put(name, attributeClass);
    }

    public void unregisterExtensionAttribute(String name, Class<? extends AbstractCityObjectType> targetClass) throws ADEException {
        if (name == null)
            throw new ADEException("The extension attribute name must not be null.");

        if (!name.startsWith("+"))
            throw new ADEException("The extension attribute name '" + name + "' must start with a '+'.");

        if (targetClass == null)
            throw new ADEException("The extension attribute target class must not be null.");

        for (Map.Entry<Class<? extends AbstractCityObjectType>, Map<String, Class<?>>> entry : attributes.entrySet()) {
            if (entry.getKey().isAssignableFrom(targetClass) || targetClass.isAssignableFrom(entry.getKey()))
                entry.getValue().remove(name);
        }
    }
}