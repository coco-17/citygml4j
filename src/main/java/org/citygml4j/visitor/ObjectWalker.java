package org.citygml4j.visitor;

import org.citygml4j.ADERegistry;
import org.citygml4j.model.ade.ADEObject;
import org.citygml4j.model.ade.ADEProperty;
import org.citygml4j.model.appearance.AbstractSurfaceData;
import org.citygml4j.model.appearance.AbstractSurfaceDataProperty;
import org.citygml4j.model.appearance.AbstractTexture;
import org.citygml4j.model.appearance.Appearance;
import org.citygml4j.model.appearance.GeoreferencedTexture;
import org.citygml4j.model.appearance.ParameterizedTexture;
import org.citygml4j.model.appearance.TextureAssociation;
import org.citygml4j.model.appearance.X3DMaterial;
import org.citygml4j.model.bridge.AbstractBridge;
import org.citygml4j.model.bridge.Bridge;
import org.citygml4j.model.bridge.BridgeConstructiveElement;
import org.citygml4j.model.bridge.BridgeConstructiveElementMember;
import org.citygml4j.model.bridge.BridgeFurniture;
import org.citygml4j.model.bridge.BridgeFurnitureMember;
import org.citygml4j.model.bridge.BridgeFurnitureProperty;
import org.citygml4j.model.bridge.BridgeInstallation;
import org.citygml4j.model.bridge.BridgeInstallationMember;
import org.citygml4j.model.bridge.BridgeInstallationProperty;
import org.citygml4j.model.bridge.BridgePart;
import org.citygml4j.model.bridge.BridgePartProperty;
import org.citygml4j.model.bridge.BridgeRoom;
import org.citygml4j.model.bridge.BridgeRoomMember;
import org.citygml4j.model.building.AbstractBuilding;
import org.citygml4j.model.building.AbstractBuildingSubdivision;
import org.citygml4j.model.building.AbstractBuildingSubdivisionMember;
import org.citygml4j.model.building.Building;
import org.citygml4j.model.building.BuildingConstructiveElement;
import org.citygml4j.model.building.BuildingConstructiveElementMember;
import org.citygml4j.model.building.BuildingFurniture;
import org.citygml4j.model.building.BuildingFurnitureMember;
import org.citygml4j.model.building.BuildingFurnitureProperty;
import org.citygml4j.model.building.BuildingInstallation;
import org.citygml4j.model.building.BuildingInstallationMember;
import org.citygml4j.model.building.BuildingInstallationProperty;
import org.citygml4j.model.building.BuildingPart;
import org.citygml4j.model.building.BuildingPartProperty;
import org.citygml4j.model.building.BuildingRoom;
import org.citygml4j.model.building.BuildingRoomMember;
import org.citygml4j.model.building.BuildingUnit;
import org.citygml4j.model.building.Storey;
import org.citygml4j.model.cityfurniture.CityFurniture;
import org.citygml4j.model.cityobjectgroup.CityObjectGroup;
import org.citygml4j.model.cityobjectgroup.Role;
import org.citygml4j.model.cityobjectgroup.RoleProperty;
import org.citygml4j.model.construction.AbstractConstruction;
import org.citygml4j.model.construction.AbstractConstructionSurface;
import org.citygml4j.model.construction.AbstractConstructiveElement;
import org.citygml4j.model.construction.AbstractFillingElement;
import org.citygml4j.model.construction.AbstractFillingElementProperty;
import org.citygml4j.model.construction.AbstractFillingSurface;
import org.citygml4j.model.construction.AbstractFillingSurfaceProperty;
import org.citygml4j.model.construction.AbstractFurniture;
import org.citygml4j.model.construction.AbstractInstallation;
import org.citygml4j.model.construction.CeilingSurface;
import org.citygml4j.model.construction.Door;
import org.citygml4j.model.construction.DoorSurface;
import org.citygml4j.model.construction.FloorSurface;
import org.citygml4j.model.construction.GroundSurface;
import org.citygml4j.model.construction.InteriorWallSurface;
import org.citygml4j.model.construction.OtherConstruction;
import org.citygml4j.model.construction.OuterCeilingSurface;
import org.citygml4j.model.construction.OuterFloorSurface;
import org.citygml4j.model.construction.RoofSurface;
import org.citygml4j.model.construction.WallSurface;
import org.citygml4j.model.construction.Window;
import org.citygml4j.model.construction.WindowSurface;
import org.citygml4j.model.core.AbstractAppearance;
import org.citygml4j.model.core.AbstractAppearanceProperty;
import org.citygml4j.model.core.AbstractCityObject;
import org.citygml4j.model.core.AbstractCityObjectProperty;
import org.citygml4j.model.core.AbstractDynamizer;
import org.citygml4j.model.core.AbstractDynamizerProperty;
import org.citygml4j.model.core.AbstractFeature;
import org.citygml4j.model.core.AbstractFeatureProperty;
import org.citygml4j.model.core.AbstractFeatureWithLifespan;
import org.citygml4j.model.core.AbstractFeatureWithLifespanProperty;
import org.citygml4j.model.core.AbstractLogicalSpace;
import org.citygml4j.model.core.AbstractOccupiedSpace;
import org.citygml4j.model.core.AbstractPhysicalSpace;
import org.citygml4j.model.core.AbstractPointCloud;
import org.citygml4j.model.core.AbstractSpace;
import org.citygml4j.model.core.AbstractSpaceBoundary;
import org.citygml4j.model.core.AbstractSpaceBoundaryProperty;
import org.citygml4j.model.core.AbstractThematicSurface;
import org.citygml4j.model.core.AbstractUnoccupiedSpace;
import org.citygml4j.model.core.AbstractVersion;
import org.citygml4j.model.core.AbstractVersionProperty;
import org.citygml4j.model.core.AbstractVersionTransition;
import org.citygml4j.model.core.AbstractVersionTransitionProperty;
import org.citygml4j.model.core.Address;
import org.citygml4j.model.core.AddressProperty;
import org.citygml4j.model.core.CityModel;
import org.citygml4j.model.core.CityObjectRelation;
import org.citygml4j.model.core.CityObjectRelationProperty;
import org.citygml4j.model.core.ClosureSurface;
import org.citygml4j.model.core.ImplicitGeometry;
import org.citygml4j.model.deprecated.bridge.DeprecatedPropertiesOfAbstractBridge;
import org.citygml4j.model.deprecated.bridge.DeprecatedPropertiesOfBridgeConstructiveElement;
import org.citygml4j.model.deprecated.bridge.DeprecatedPropertiesOfBridgeFurniture;
import org.citygml4j.model.deprecated.bridge.DeprecatedPropertiesOfBridgeInstallation;
import org.citygml4j.model.deprecated.bridge.DeprecatedPropertiesOfBridgeRoom;
import org.citygml4j.model.deprecated.building.DeprecatedPropertiesOfAbstractBuilding;
import org.citygml4j.model.deprecated.building.DeprecatedPropertiesOfBuildingFurniture;
import org.citygml4j.model.deprecated.building.DeprecatedPropertiesOfBuildingInstallation;
import org.citygml4j.model.deprecated.building.DeprecatedPropertiesOfBuildingRoom;
import org.citygml4j.model.deprecated.cityfurniture.DeprecatedPropertiesOfCityFurniture;
import org.citygml4j.model.deprecated.cityobjectgroup.DeprecatedPropertiesOfCityObjectGroup;
import org.citygml4j.model.deprecated.construction.DeprecatedPropertiesOfAbstractFillingSurface;
import org.citygml4j.model.deprecated.core.DeprecatedPropertiesOfAbstractCityObject;
import org.citygml4j.model.deprecated.core.DeprecatedPropertiesOfAbstractThematicSurface;
import org.citygml4j.model.deprecated.generics.DeprecatedPropertiesOfGenericOccupiedSpace;
import org.citygml4j.model.deprecated.transportation.DeprecatedPropertiesOfAbstractTransportationSpace;
import org.citygml4j.model.deprecated.transportation.TransportationComplex;
import org.citygml4j.model.deprecated.tunnel.DeprecatedPropertiesOfAbstractTunnel;
import org.citygml4j.model.deprecated.tunnel.DeprecatedPropertiesOfHollowSpace;
import org.citygml4j.model.deprecated.tunnel.DeprecatedPropertiesOfTunnelFurniture;
import org.citygml4j.model.deprecated.tunnel.DeprecatedPropertiesOfTunnelInstallation;
import org.citygml4j.model.deprecated.vegetation.DeprecatedPropertiesOfPlantCover;
import org.citygml4j.model.deprecated.vegetation.DeprecatedPropertiesOfSolitaryVegetationObject;
import org.citygml4j.model.deprecated.waterbody.DeprecatedPropertiesOfWaterBody;
import org.citygml4j.model.dynamizer.AbstractAtomicTimeseries;
import org.citygml4j.model.dynamizer.AbstractTimeseries;
import org.citygml4j.model.dynamizer.CompositeTimeseries;
import org.citygml4j.model.dynamizer.Dynamizer;
import org.citygml4j.model.dynamizer.GenericTimeseries;
import org.citygml4j.model.dynamizer.StandardFileTimeseries;
import org.citygml4j.model.dynamizer.TabulatedFileTimeseries;
import org.citygml4j.model.dynamizer.TimeValuePair;
import org.citygml4j.model.dynamizer.TimeValuePairProperty;
import org.citygml4j.model.dynamizer.TimeseriesComponentProperty;
import org.citygml4j.model.generics.GenericLogicalSpace;
import org.citygml4j.model.generics.GenericOccupiedSpace;
import org.citygml4j.model.generics.GenericThematicSurface;
import org.citygml4j.model.generics.GenericUnoccupiedSpace;
import org.citygml4j.model.landuse.LandUse;
import org.citygml4j.model.pointcloud.PointCloud;
import org.citygml4j.model.relief.AbstractReliefComponent;
import org.citygml4j.model.relief.AbstractReliefComponentProperty;
import org.citygml4j.model.relief.BreaklineRelief;
import org.citygml4j.model.relief.MassPointRelief;
import org.citygml4j.model.relief.RasterRelief;
import org.citygml4j.model.relief.ReliefFeature;
import org.citygml4j.model.relief.TINRelief;
import org.citygml4j.model.transportation.AbstractTransportationSpace;
import org.citygml4j.model.transportation.AuxiliaryTrafficArea;
import org.citygml4j.model.transportation.AuxiliaryTrafficSpace;
import org.citygml4j.model.transportation.AuxiliaryTrafficSpaceProperty;
import org.citygml4j.model.transportation.ClearanceSpace;
import org.citygml4j.model.transportation.ClearanceSpaceProperty;
import org.citygml4j.model.transportation.Hole;
import org.citygml4j.model.transportation.HoleProperty;
import org.citygml4j.model.transportation.HoleSurface;
import org.citygml4j.model.transportation.Intersection;
import org.citygml4j.model.transportation.IntersectionProperty;
import org.citygml4j.model.transportation.Marking;
import org.citygml4j.model.transportation.MarkingProperty;
import org.citygml4j.model.transportation.Railway;
import org.citygml4j.model.transportation.Road;
import org.citygml4j.model.transportation.Section;
import org.citygml4j.model.transportation.SectionProperty;
import org.citygml4j.model.transportation.Square;
import org.citygml4j.model.transportation.Track;
import org.citygml4j.model.transportation.TrafficArea;
import org.citygml4j.model.transportation.TrafficSpace;
import org.citygml4j.model.transportation.TrafficSpaceProperty;
import org.citygml4j.model.transportation.Waterway;
import org.citygml4j.model.tunnel.AbstractTunnel;
import org.citygml4j.model.tunnel.HollowSpace;
import org.citygml4j.model.tunnel.HollowSpaceMember;
import org.citygml4j.model.tunnel.Tunnel;
import org.citygml4j.model.tunnel.TunnelConstructiveElement;
import org.citygml4j.model.tunnel.TunnelConstructiveElementMember;
import org.citygml4j.model.tunnel.TunnelFurniture;
import org.citygml4j.model.tunnel.TunnelFurnitureMember;
import org.citygml4j.model.tunnel.TunnelFurnitureProperty;
import org.citygml4j.model.tunnel.TunnelInstallation;
import org.citygml4j.model.tunnel.TunnelInstallationMember;
import org.citygml4j.model.tunnel.TunnelInstallationProperty;
import org.citygml4j.model.tunnel.TunnelPart;
import org.citygml4j.model.tunnel.TunnelPartProperty;
import org.citygml4j.model.vegetation.AbstractVegetationObject;
import org.citygml4j.model.vegetation.PlantCover;
import org.citygml4j.model.vegetation.SolitaryVegetationObject;
import org.citygml4j.model.versioning.Version;
import org.citygml4j.model.versioning.VersionTransition;
import org.citygml4j.model.waterbody.AbstractWaterBoundarySurface;
import org.citygml4j.model.waterbody.WaterBody;
import org.citygml4j.model.waterbody.WaterGroundSurface;
import org.citygml4j.model.waterbody.WaterSurface;
import org.citygml4j.xml.ade.ADEContext;
import org.xmlobjects.gml.model.base.AbstractArrayProperty;
import org.xmlobjects.gml.model.base.AbstractAssociation;
import org.xmlobjects.gml.model.base.AbstractGML;
import org.xmlobjects.gml.model.base.AbstractInlineOrByReferenceProperty;
import org.xmlobjects.gml.model.base.AbstractInlineProperty;
import org.xmlobjects.gml.model.common.GenericElement;
import org.xmlobjects.gml.model.coverage.AbstractContinuousCoverage;
import org.xmlobjects.gml.model.coverage.AbstractCoverage;
import org.xmlobjects.gml.model.coverage.AbstractDiscreteCoverage;
import org.xmlobjects.gml.model.coverage.GridCoverage;
import org.xmlobjects.gml.model.coverage.MultiCurveCoverage;
import org.xmlobjects.gml.model.coverage.MultiPointCoverage;
import org.xmlobjects.gml.model.coverage.MultiSolidCoverage;
import org.xmlobjects.gml.model.coverage.MultiSurfaceCoverage;
import org.xmlobjects.gml.model.coverage.RectifiedGridCoverage;
import org.xmlobjects.gml.model.feature.AbstractFeatureMember;
import org.xmlobjects.gml.model.feature.FeatureProperty;
import org.xmlobjects.gml.model.geometry.AbstractGeometry;
import org.xmlobjects.gml.model.temporal.TimeInstant;
import org.xmlobjects.gml.model.temporal.TimeInstantProperty;
import org.xmlobjects.gml.model.temporal.TimePeriod;
import org.xmlobjects.gml.model.valueobjects.AbstractValue;
import org.xmlobjects.gml.model.valueobjects.CompositeValue;
import org.xmlobjects.gml.model.valueobjects.Value;
import org.xmlobjects.gml.model.valueobjects.ValueArray;
import org.xmlobjects.gml.model.valueobjects.ValueProperty;
import org.xmlobjects.gml.visitor.GeometryWalker;

import java.util.ArrayList;

public abstract class ObjectWalker extends GeometryWalker implements ObjectVisitor {
    ADEWalkerHelper adeWalkerHelper;
    boolean shouldWalk = true;

    public ObjectWalker() {
        ADERegistry registry = ADERegistry.getInstance();
        if (registry.hasADEContexts()) {
            for (ADEContext context : ADERegistry.getInstance().getADEContexts())
                withADEWalker(context.getADEWalker());
        }
    }

    public ObjectWalker withADEWalker(ADEWalker walker) {
        if (walker != null) {
            if (adeWalkerHelper == null)
                adeWalkerHelper = new ADEWalkerHelper();

            adeWalkerHelper.addADEWalker(walker);
            walker.setParentWalker(this);
        }

        return this;
    }

    public boolean shouldWalk() {
        return shouldWalk;
    }

    public void setShouldWalk(boolean shouldWalk) {
        this.shouldWalk = shouldWalk;
    }

    public void reset() {
        shouldWalk = true;
    }

    public void visit(AbstractGML object) {
    }

    @Override
    public void visit(AbstractGeometry geometry) {
        visit((AbstractGML) geometry);
    }

    public void visit(org.xmlobjects.gml.model.feature.AbstractFeature feature) {
        visit((AbstractGML) feature);

        visit(feature.getLocation());

        for (GenericElement genericElement : feature.getGenericProperties())
            visit(genericElement);
    }

    public void visit(AbstractFeature feature) {
        visit((org.xmlobjects.gml.model.feature.AbstractFeature) feature);

        for (ADEProperty<?> property : new ArrayList<>(feature.getADEPropertiesOfAbstractFeature()))
            visit(property);
    }

    public void visit(AbstractAppearance appearance) {
        visit((AbstractFeatureWithLifespan) appearance);

        for (ADEProperty<?> property : new ArrayList<>(appearance.getADEPropertiesOfAbstractAppearance()))
            visit(property);
    }

    public void visit(AbstractAtomicTimeseries atomicTimeseries) {
        visit((AbstractTimeseries) atomicTimeseries);

        for (ADEProperty<?> property : new ArrayList<>(atomicTimeseries.getADEPropertiesOfAbstractAtomicTimeseries()))
            visit(property);
    }

    public void visit(AbstractBridge bridge) {
        visit((AbstractConstruction) bridge);

        for (BridgeConstructiveElementMember member : new ArrayList<>(bridge.getBridgeConstructiveElements()))
            visit(member);

        for (BridgeInstallationMember member : new ArrayList<>(bridge.getBridgeInstallations()))
            visit(member);

        for (BridgeRoomMember member : new ArrayList<>(bridge.getBridgeRooms()))
            visit(member);

        for (BridgeFurnitureMember member : new ArrayList<>(bridge.getBridgeFurniture()))
            visit(member);

        for (AddressProperty member : new ArrayList<>(bridge.getAddresses()))
            visit(member);

        if (bridge.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractBridge deprecatedProperties = bridge.getDeprecatedProperties();

            for (BridgePartProperty property : new ArrayList<>(deprecatedProperties.getConsistsOfBridgeParts()))
                visit(property);

            visit(deprecatedProperties.getLod1MultiSurface());
            visit(deprecatedProperties.getLod4MultiCurve());
            visit(deprecatedProperties.getLod4MultiSurface());
            visit(deprecatedProperties.getLod4Solid());
            visit(deprecatedProperties.getLod4TerrainIntersectionCurve());
        }

        for (ADEProperty<?> property : new ArrayList<>(bridge.getADEPropertiesOfAbstractBridge()))
            visit(property);
    }

    public void visit(AbstractBuilding building) {
        visit((AbstractConstruction) building);

        for (BuildingConstructiveElementMember member : new ArrayList<>(building.getBuildingConstructiveElements()))
            visit(member);

        for (BuildingInstallationMember member : new ArrayList<>(building.getBuildingInstallations()))
            visit(member);

        for (BuildingRoomMember member : new ArrayList<>(building.getBuildingRooms()))
            visit(member);

        for (BuildingFurnitureMember member : new ArrayList<>(building.getBuildingFurniture()))
            visit(member);

        for (AbstractBuildingSubdivisionMember member : new ArrayList<>(building.getBuildingSubdivisions()))
            visit(member);

        for (AddressProperty member : new ArrayList<>(building.getAddresses()))
            visit(member);

        if (building.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractBuilding deprecatedProperties = building.getDeprecatedProperties();

            for (BuildingPartProperty property : new ArrayList<>(deprecatedProperties.getConsistsOfBuildingParts()))
                visit(property);

            visit(deprecatedProperties.getLod0RoofEdge());
            visit(deprecatedProperties.getLod1MultiSurface());
            visit(deprecatedProperties.getLod4MultiCurve());
            visit(deprecatedProperties.getLod4MultiSurface());
            visit(deprecatedProperties.getLod4Solid());
            visit(deprecatedProperties.getLod4TerrainIntersectionCurve());
        }

        for (ADEProperty<?> property : new ArrayList<>(building.getADEPropertiesOfAbstractBuilding()))
            visit(property);
    }

    public void visit(AbstractBuildingSubdivision buildingSubdivision) {
        visit((AbstractLogicalSpace) buildingSubdivision);

        for (ADEProperty<?> property : new ArrayList<>(buildingSubdivision.getADEPropertiesOfAbstractBuildingSubdivision()))
            visit(property);
    }

    public void visit(AbstractCityObject cityObject) {
        visit((AbstractFeatureWithLifespan) cityObject);

        for (CityObjectRelationProperty property : new ArrayList<>(cityObject.getRelatedTo()))
            visit(property);

        for (AbstractAppearanceProperty property : new ArrayList<>(cityObject.getAppearances()))
            visit(property);

        for (AbstractDynamizerProperty property : new ArrayList<>(cityObject.getDynamizers()))
            visit(property);

        if (cityObject.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractCityObject deprecatedProperties = cityObject.getDeprecatedProperties();

            for (AbstractCityObjectProperty property : new ArrayList<>(deprecatedProperties.getGeneralizesTo()))
                visit(property);
        }

        for (ADEProperty<?> property : new ArrayList<>(cityObject.getADEPropertiesOfAbstractCityObject()))
            visit(property);
    }

    public void visit(AbstractConstruction construction) {
        visit((AbstractOccupiedSpace) construction);

        for (ADEProperty<?> property : new ArrayList<>(construction.getADEPropertiesOfAbstractConstruction()))
            visit(property);
    }

    public void visit(AbstractConstructionSurface constructionSurface) {
        visit((AbstractThematicSurface) constructionSurface);

        for (AbstractFillingSurfaceProperty property : new ArrayList<>(constructionSurface.getFillingSurfaces()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(constructionSurface.getADEPropertiesOfAbstractConstructionSurface()))
            visit(property);
    }

    public void visit(AbstractConstructiveElement constructiveElement) {
        visit((AbstractOccupiedSpace) constructiveElement);

        for (AbstractFillingElementProperty property : new ArrayList<>(constructiveElement.getFillings()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(constructiveElement.getADEPropertiesOfAbstractConstructiveElement()))
            visit(property);
    }

    public void visit(AbstractContinuousCoverage<?> continuousCoverage) {
        visit((AbstractCoverage<?>) continuousCoverage);
    }

    public void visit(AbstractCoverage<?> coverage) {
        visit((org.xmlobjects.gml.model.feature.AbstractFeature) coverage);

        visit(coverage.getDomainSet());

        if (coverage.getRangeSet() != null && coverage.getRangeSet().getValueArrays() != null) {
            for (ValueArray valueArray : coverage.getRangeSet().getValueArrays()) {
                if (valueArray != null)
                    visit(valueArray);
            }
        }
    }

    public void visit(AbstractDiscreteCoverage<?> discreteCoverage) {
        visit((AbstractCoverage<?>) discreteCoverage);
    }

    public void visit(AbstractDynamizer dynamizer) {
        visit((AbstractFeatureWithLifespan) dynamizer);

        for (ADEProperty<?> property : new ArrayList<>(dynamizer.getADEPropertiesOfAbstractDynamizer()))
            visit(property);
    }

    public void visit(AbstractFeatureWithLifespan featureWithLifespan) {
        visit((AbstractFeature) featureWithLifespan);

        for (ADEProperty<?> property : new ArrayList<>(featureWithLifespan.getADEPropertiesOfAbstractFeatureWithLifespan()))
            visit(property);
    }

    public void visit(AbstractFillingElement fillingElement) {
        visit((AbstractOccupiedSpace) fillingElement);

        for (ADEProperty<?> property : new ArrayList<>(fillingElement.getADEPropertiesOfAbstractFillingElement()))
            visit(property);
    }

    public void visit(AbstractFillingSurface fillingSurface) {
        visit((AbstractThematicSurface) fillingSurface);

        if (fillingSurface.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractFillingSurface deprecatedProperties = fillingSurface.getDeprecatedProperties();

            visit(deprecatedProperties.getLod3ImplicitRepresentation());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(fillingSurface.getADEPropertiesOfAbstractFillingSurface()))
            visit(property);
    }

    public void visit(AbstractFurniture furniture) {
        visit((AbstractOccupiedSpace) furniture);

        for (ADEProperty<?> property : new ArrayList<>(furniture.getADEPropertiesOfAbstractFurniture()))
            visit(property);
    }

    public void visit(AbstractInstallation installation) {
        visit((AbstractOccupiedSpace) installation);

        for (ADEProperty<?> property : new ArrayList<>(installation.getADEPropertiesOfAbstractInstallation()))
            visit(property);
    }

    public void visit(AbstractLogicalSpace logicalSpace) {
        visit((AbstractSpace) logicalSpace);

        for (ADEProperty<?> property : new ArrayList<>(logicalSpace.getADEPropertiesOfAbstractLogicalSpace()))
            visit(property);
    }

    public void visit(AbstractOccupiedSpace occupiedSpace) {
        visit((AbstractPhysicalSpace) occupiedSpace);

        visit(occupiedSpace.getLod1ImplicitRepresentation());
        visit(occupiedSpace.getLod2ImplicitRepresentation());
        visit(occupiedSpace.getLod3ImplicitRepresentation());

        for (ADEProperty<?> property : new ArrayList<>(occupiedSpace.getADEPropertiesOfAbstractOccupiedSpace()))
            visit(property);
    }

    public void visit(AbstractPhysicalSpace physicalSpace) {
        visit((AbstractSpace) physicalSpace);

        visit(physicalSpace.getPointCloud());
        visit(physicalSpace.getLod1TerrainIntersectionCurve());
        visit(physicalSpace.getLod2TerrainIntersectionCurve());
        visit(physicalSpace.getLod3TerrainIntersectionCurve());

        for (ADEProperty<?> property : new ArrayList<>(physicalSpace.getADEPropertiesOfAbstractPhysicalSpace()))
            visit(property);
    }

    public void visit(AbstractPointCloud pointCloud) {
        visit((AbstractFeature) pointCloud);

        for (ADEProperty<?> property : new ArrayList<>(pointCloud.getADEPropertiesOfAbstractPointCloud()))
            visit(property);
    }

    public void visit(AbstractReliefComponent reliefComponent) {
        visit((AbstractSpaceBoundary) reliefComponent);

        visit(reliefComponent.getExtent());

        for (ADEProperty<?> property : new ArrayList<>(reliefComponent.getADEPropertiesOfAbstractReliefComponent()))
            visit(property);
    }

    public void visit(AbstractSpace space) {
        visit((AbstractCityObject) space);

        for (AbstractSpaceBoundaryProperty property : new ArrayList<>(space.getBoundaries()))
            visit(property);

        visit(space.getLod0Point());
        visit(space.getLod0MultiSurface());
        visit(space.getLod0MultiCurve());
        visit(space.getLod1Solid());
        visit(space.getLod2Solid());
        visit(space.getLod2MultiSurface());
        visit(space.getLod2MultiCurve());
        visit(space.getLod3Solid());
        visit(space.getLod3MultiSurface());
        visit(space.getLod3MultiCurve());

        for (ADEProperty<?> property : new ArrayList<>(space.getADEPropertiesOfAbstractSpace()))
            visit(property);
    }

    public void visit(AbstractSpaceBoundary spaceBoundary) {
        visit((AbstractCityObject) spaceBoundary);

        for (ADEProperty<?> property : new ArrayList<>(spaceBoundary.getADEPropertiesOfAbstractSpaceBoundary()))
            visit(property);
    }

    public void visit(AbstractSurfaceData surfaceData) {
        visit((AbstractFeature) surfaceData);

        for (ADEProperty<?> property : new ArrayList<>(surfaceData.getADEPropertiesOfAbstractSurfaceData()))
            visit(property);
    }

    public void visit(AbstractTexture texture) {
        visit((AbstractSurfaceData) texture);

        for (ADEProperty<?> property : new ArrayList<>(texture.getADEPropertiesOfAbstractTexture()))
            visit(property);
    }

    public void visit(AbstractThematicSurface thematicSurface) {
        visit((AbstractSpaceBoundary) thematicSurface);

        visit(thematicSurface.getPointCloud());
        visit(thematicSurface.getLod0MultiCurve());
        visit(thematicSurface.getLod0MultiSurface());
        visit(thematicSurface.getLod1MultiSurface());
        visit(thematicSurface.getLod2MultiSurface());
        visit(thematicSurface.getLod3MultiSurface());

        if (thematicSurface.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractThematicSurface deprecatedProperties = thematicSurface.getDeprecatedProperties();

            visit(deprecatedProperties.getLod4MultiSurface());
        }

        for (ADEProperty<?> property : new ArrayList<>(thematicSurface.getADEPropertiesOfAbstractThematicSurface()))
            visit(property);
    }

    public void visit(AbstractTimeseries timeseries) {
        visit((AbstractFeature) timeseries);

        for (ADEProperty<?> property : new ArrayList<>(timeseries.getADEPropertiesOfAbstractTimeseries()))
            visit(property);
    }

    public void visit(AbstractTransportationSpace transportationSpace) {
        visit((AbstractUnoccupiedSpace) transportationSpace);

        for (TrafficSpaceProperty property : new ArrayList<>(transportationSpace.getTrafficSpaces()))
            visit(property);

        for (AuxiliaryTrafficSpaceProperty property : new ArrayList<>(transportationSpace.getAuxiliaryTrafficSpaces()))
            visit(property);

        for (HoleProperty property : new ArrayList<>(transportationSpace.getHoles()))
            visit(property);

        for (MarkingProperty property : new ArrayList<>(transportationSpace.getMarkings()))
            visit(property);

        if (transportationSpace.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractTransportationSpace deprecatedProperties = transportationSpace.getDeprecatedProperties();

            visit(deprecatedProperties.getLod0Network());
            visit(deprecatedProperties.getLod1MultiSurface());
            visit(deprecatedProperties.getLod4MultiSurface());
        }

        for (ADEProperty<?> property : new ArrayList<>(transportationSpace.getADEPropertiesOfAbstractTransportationSpace()))
            visit(property);
    }

    public void visit(AbstractTunnel tunnel) {
        visit((AbstractConstruction) tunnel);

        for (TunnelConstructiveElementMember member : new ArrayList<>(tunnel.getTunnelConstructiveElements()))
            visit(member);

        for (TunnelInstallationMember member : new ArrayList<>(tunnel.getTunnelInstallations()))
            visit(member);

        for (HollowSpaceMember member : new ArrayList<>(tunnel.getHollowSpaces()))
            visit(member);

        for (TunnelFurnitureMember member : new ArrayList<>(tunnel.getTunnelFurniture()))
            visit(member);

        if (tunnel.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractTunnel deprecatedProperties = tunnel.getDeprecatedProperties();

            for (TunnelPartProperty property : new ArrayList<>(deprecatedProperties.getConsistsOfTunnelParts()))
                visit(property);

            visit(deprecatedProperties.getLod1MultiSurface());
            visit(deprecatedProperties.getLod4MultiCurve());
            visit(deprecatedProperties.getLod4MultiSurface());
            visit(deprecatedProperties.getLod4Solid());
            visit(deprecatedProperties.getLod4TerrainIntersectionCurve());
        }

        for (ADEProperty<?> property : new ArrayList<>(tunnel.getADEPropertiesOfAbstractTunnel()))
            visit(property);
    }

    public void visit(AbstractUnoccupiedSpace unoccupiedSpace) {
        visit((AbstractPhysicalSpace) unoccupiedSpace);

        for (ADEProperty<?> property : new ArrayList<>(unoccupiedSpace.getADEPropertiesOfAbstractUnoccupiedSpace()))
            visit(property);
    }

    public void visit(AbstractVegetationObject vegetationObject) {
        visit((AbstractOccupiedSpace) vegetationObject);

        for (ADEProperty<?> property : new ArrayList<>(vegetationObject.getADEPropertiesOfAbstractVegetationObject()))
            visit(property);
    }

    public void visit(AbstractVersion version) {
        visit((AbstractFeatureWithLifespan) version);

        for (ADEProperty<?> property : new ArrayList<>(version.getADEPropertiesOfAbstractVersion()))
            visit(property);
    }

    public void visit(AbstractVersionTransition versionTransition) {
        visit((AbstractFeatureWithLifespan) versionTransition);

        for (ADEProperty<?> property : new ArrayList<>(versionTransition.getADEPropertiesOfAbstractVersionTransition()))
            visit(property);
    }

    public void visit(AbstractWaterBoundarySurface waterBoundarySurface) {
        visit((AbstractThematicSurface) waterBoundarySurface);

        for (ADEProperty<?> property : new ArrayList<>(waterBoundarySurface.getADEPropertiesOfAbstractWaterBoundarySurface()))
            visit(property);
    }

    @Override
    public void visit(Address address) {
        visit((AbstractFeature) address);

        visit(address.getMultiPoint());

        for (ADEProperty<?> property : new ArrayList<>(address.getADEPropertiesOfAddress()))
            visit(property);
    }

    @Override
    public void visit(Appearance appearance) {
        visit((AbstractAppearance) appearance);

        for (AbstractSurfaceDataProperty property : new ArrayList<>(appearance.getSurfaceData()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(appearance.getADEPropertiesOfAppearance()))
            visit(property);
    }

    @Override
    public void visit(AuxiliaryTrafficArea auxiliaryTrafficArea) {
        visit((AbstractThematicSurface) auxiliaryTrafficArea);

        for (ADEProperty<?> property : new ArrayList<>(auxiliaryTrafficArea.getADEPropertiesOfAuxiliaryTrafficArea()))
            visit(property);
    }

    @Override
    public void visit(AuxiliaryTrafficSpace auxiliaryTrafficSpace) {
        visit((AbstractUnoccupiedSpace) auxiliaryTrafficSpace);

        for (ADEProperty<?> property : new ArrayList<>(auxiliaryTrafficSpace.getADEPropertiesOfAuxiliaryTrafficSpace()))
            visit(property);
    }

    @Override
    public void visit(BreaklineRelief breaklineRelief) {
        visit((AbstractReliefComponent) breaklineRelief);

        visit(breaklineRelief.getRidgeOrValleyLines());
        visit(breaklineRelief.getBreaklines());

        for (ADEProperty<?> property : new ArrayList<>(breaklineRelief.getADEPropertiesOfBreaklineRelief()))
            visit(property);
    }

    @Override
    public void visit(Bridge bridge) {
        visit((AbstractBridge) bridge);

        for (BridgePartProperty property : new ArrayList<>(bridge.getBridgeParts()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(bridge.getADEPropertiesOfBridge()))
            visit(property);
    }

    @Override
    public void visit(BridgeConstructiveElement bridgeConstructiveElement) {
        visit((AbstractConstructiveElement) bridgeConstructiveElement);

        if (bridgeConstructiveElement.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBridgeConstructiveElement deprecatedProperties = bridgeConstructiveElement.getDeprecatedProperties();

            visit(deprecatedProperties.getLod1Geometry());
            visit(deprecatedProperties.getLod2Geometry());
            visit(deprecatedProperties.getLod3Geometry());
            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4TerrainIntersectionCurve());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(bridgeConstructiveElement.getADEPropertiesOfBridgeConstructiveElement()))
            visit(property);
    }

    @Override
    public void visit(BridgeFurniture bridgeFurniture) {
        visit((AbstractFurniture) bridgeFurniture);

        if (bridgeFurniture.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBridgeFurniture deprecatedProperties = bridgeFurniture.getDeprecatedProperties();

            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(bridgeFurniture.getADEPropertiesOfBridgeFurniture()))
            visit(property);
    }

    @Override
    public void visit(BridgeInstallation bridgeInstallation) {
        visit((AbstractInstallation) bridgeInstallation);

        if (bridgeInstallation.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBridgeInstallation deprecatedProperties = bridgeInstallation.getDeprecatedProperties();

            visit(deprecatedProperties.getLod2Geometry());
            visit(deprecatedProperties.getLod3Geometry());
            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(bridgeInstallation.getADEPropertiesOfBridgeInstallation()))
            visit(property);
    }

    @Override
    public void visit(BridgePart bridgePart) {
        visit((AbstractBridge) bridgePart);

        for (ADEProperty<?> property : new ArrayList<>(bridgePart.getADEPropertiesOfBridgePart()))
            visit(property);
    }

    @Override
    public void visit(BridgeRoom bridgeRoom) {
        visit((AbstractUnoccupiedSpace) bridgeRoom);

        for (BridgeFurnitureProperty property : new ArrayList<>(bridgeRoom.getBridgeFurniture()))
            visit(property);

        for (BridgeInstallationProperty property : new ArrayList<>(bridgeRoom.getBridgeInstallations()))
            visit(property);

        if (bridgeRoom.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBridgeRoom deprecatedProperties = bridgeRoom.getDeprecatedProperties();

            visit(deprecatedProperties.getLod4Solid());
            visit(deprecatedProperties.getLod4MultiSurface());
        }

        for (ADEProperty<?> property : new ArrayList<>(bridgeRoom.getADEPropertiesOfBridgeRoom()))
            visit(property);
    }

    @Override
    public void visit(Building building) {
        visit((AbstractBuilding) building);

        for (BuildingPartProperty property : new ArrayList<>(building.getBuildingParts()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(building.getADEPropertiesOfBuilding()))
            visit(property);
    }

    @Override
    public void visit(BuildingConstructiveElement buildingConstructiveElement) {
        visit((AbstractConstructiveElement) buildingConstructiveElement);

        for (ADEProperty<?> property : new ArrayList<>(buildingConstructiveElement.getADEPropertiesOfBuildingConstructiveElement()))
            visit(property);
    }

    @Override
    public void visit(BuildingFurniture buildingFurniture) {
        visit((AbstractFurniture) buildingFurniture);

        if (buildingFurniture.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBuildingFurniture deprecatedProperties = buildingFurniture.getDeprecatedProperties();

            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(buildingFurniture.getADEPropertiesOfBuildingFurniture()))
            visit(property);
    }

    @Override
    public void visit(BuildingInstallation buildingInstallation) {
        visit((AbstractInstallation) buildingInstallation);

        if (buildingInstallation.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBuildingInstallation deprecatedProperties = buildingInstallation.getDeprecatedProperties();

            visit(deprecatedProperties.getLod2Geometry());
            visit(deprecatedProperties.getLod3Geometry());
            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(buildingInstallation.getADEPropertiesOfBuildingInstallation()))
            visit(property);
    }

    @Override
    public void visit(BuildingPart buildingPart) {
        visit((AbstractBuilding) buildingPart);

        for (ADEProperty<?> property : new ArrayList<>(buildingPart.getADEPropertiesOfBuildingPart()))
            visit(property);
    }

    @Override
    public void visit(BuildingRoom buildingRoom) {
        visit((AbstractUnoccupiedSpace) buildingRoom);

        for (BuildingFurnitureProperty property : new ArrayList<>(buildingRoom.getBuildingFurniture()))
            visit(property);

        for (BuildingInstallationProperty property : new ArrayList<>(buildingRoom.getBuildingInstallations()))
            visit(property);

        if (buildingRoom.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBuildingRoom deprecatedProperties = buildingRoom.getDeprecatedProperties();

            visit(deprecatedProperties.getLod4Solid());
            visit(deprecatedProperties.getLod4MultiSurface());
        }

        for (ADEProperty<?> property : new ArrayList<>(buildingRoom.getADEPropertiesOfBuildingRoom()))
            visit(property);
    }

    @Override
    public void visit(BuildingUnit buildingUnit) {
        visit((AbstractBuildingSubdivision) buildingUnit);

        for (AddressProperty property : new ArrayList<>(buildingUnit.getAddresses()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(buildingUnit.getADEPropertiesOfBuildingUnit()))
            visit(property);
    }

    @Override
    public void visit(CeilingSurface ceilingSurface) {
        visit((AbstractConstructionSurface) ceilingSurface);

        for (ADEProperty<?> property : new ArrayList<>(ceilingSurface.getADEPropertiesOfCeilingSurface()))
            visit(property);
    }

    @Override
    public void visit(CityFurniture cityFurniture) {
        visit((AbstractOccupiedSpace) cityFurniture);

        if (cityFurniture.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfCityFurniture deprecatedProperties = cityFurniture.getDeprecatedProperties();

            visit(deprecatedProperties.getLod1Geometry());
            visit(deprecatedProperties.getLod2Geometry());
            visit(deprecatedProperties.getLod3Geometry());
            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4TerrainIntersectionCurve());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(cityFurniture.getADEPropertiesOfCityFurniture()))
            visit(property);
    }

    @Override
    public void visit(CityModel cityModel) {
        visit((AbstractFeatureWithLifespan) cityModel);

        for (AbstractCityObjectProperty property : new ArrayList<>(cityModel.getCityObjectMembers()))
            visit(property);

        for (AbstractAppearanceProperty property : new ArrayList<>(cityModel.getAppearanceMembers()))
            visit(property);

        for (AbstractFeatureProperty property : new ArrayList<>(cityModel.getFeatureMembers()))
            visit(property);

        for (AbstractVersionProperty property : new ArrayList<>(cityModel.getVersionMembers()))
            visit(property);

        for (AbstractVersionTransitionProperty property : new ArrayList<>(cityModel.getVersionTransitionMembers()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(cityModel.getADEPropertiesOfCityModel()))
            visit(property);
    }

    @Override
    public void visit(CityObjectGroup cityObjectGroup) {
        visit((AbstractLogicalSpace) cityObjectGroup);

        for (RoleProperty property : new ArrayList<>(cityObjectGroup.getGroupMembers()))
            visit(property);

        visit(cityObjectGroup.getGroupParent());

        if (cityObjectGroup.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfCityObjectGroup deprecatedProperties = cityObjectGroup.getDeprecatedProperties();

            visit(deprecatedProperties.getGeometry());
        }

        for (ADEProperty<?> property : new ArrayList<>(cityObjectGroup.getADEPropertiesOfCityObjectGroup()))
            visit(property);
    }

    @Override
    public void visit(CityObjectRelation cityObjectRelation) {
        visit((AbstractGML) cityObjectRelation);

        visit(cityObjectRelation.getRelatedTo());
    }

    @Override
    public void visit(ClearanceSpace clearanceSpace) {
        visit((AbstractUnoccupiedSpace) clearanceSpace);

        for (ADEProperty<?> property : new ArrayList<>(clearanceSpace.getADEPropertiesOfClearanceSpace()))
            visit(property);
    }

    @Override
    public void visit(ClosureSurface closureSurface) {
        visit((AbstractThematicSurface) closureSurface);

        for (ADEProperty<?> property : new ArrayList<>(closureSurface.getADEPropertiesOfClosureSurface()))
            visit(property);
    }

    @Override
    public void visit(CompositeTimeseries compositeTimeseries) {
        visit((AbstractTimeseries) compositeTimeseries);

        for (TimeseriesComponentProperty property : new ArrayList<>(compositeTimeseries.getComponents()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(compositeTimeseries.getADEPropertiesOfCompositeTimeseries()))
            visit(property);
    }

    @Override
    public void visit(CompositeValue compositeValue) {
        visit((AbstractGML) compositeValue);

        for (ValueProperty property : new ArrayList<>(compositeValue.getValueComponent())) {
            if (property.getObject() != null)
                visit(property.getObject());
        }

        if (compositeValue.getValueComponents() != null) {
            for (Value value : compositeValue.getValueComponents().getObjects()) {
                if (value != null)
                    visit(value);
            }
        }
    }

    @Override
    public void visit(Door door) {
        visit((AbstractFillingElement) door);

        for (AddressProperty property : new ArrayList<>(door.getAddresses()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(door.getADEPropertiesOfDoor()))
            visit(property);
    }

    @Override
    public void visit(DoorSurface doorSurface) {
        visit((AbstractFillingSurface) doorSurface);

        for (AddressProperty property : new ArrayList<>(doorSurface.getAddresses()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(doorSurface.getADEPropertiesOfDoorSurface()))
            visit(property);
    }

    @Override
    public void visit(Dynamizer dynamizer) {
        visit((AbstractDynamizer) dynamizer);

        visit(dynamizer.getDynamicData());

        for (ADEProperty<?> property : new ArrayList<>(dynamizer.getADEPropertiesOfDynamizer()))
            visit(property);
    }

    @Override
    public void visit(FloorSurface floorSurface) {
        visit((AbstractConstructionSurface) floorSurface);

        for (ADEProperty<?> property : new ArrayList<>(floorSurface.getADEPropertiesOfFloorSurface()))
            visit(property);
    }

    @Override
    public void visit(GenericLogicalSpace genericLogicalSpace) {
        visit((AbstractLogicalSpace) genericLogicalSpace);

        for (ADEProperty<?> property : new ArrayList<>(genericLogicalSpace.getADEPropertiesOfGenericLogicalSpace()))
            visit(property);
    }

    @Override
    public void visit(GenericOccupiedSpace genericOccupiedSpace) {
        visit((AbstractOccupiedSpace) genericOccupiedSpace);

        if (genericOccupiedSpace.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfGenericOccupiedSpace deprecatedProperties = genericOccupiedSpace.getDeprecatedProperties();

            visit(deprecatedProperties.getLod0Geometry());
            visit(deprecatedProperties.getLod1Geometry());
            visit(deprecatedProperties.getLod2Geometry());
            visit(deprecatedProperties.getLod3Geometry());
            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod0TerrainIntersectionCurve());
            visit(deprecatedProperties.getLod4TerrainIntersectionCurve());
            visit(deprecatedProperties.getLod0ImplicitRepresentation());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(genericOccupiedSpace.getADEPropertiesOfGenericOccupiedSpace()))
            visit(property);
    }

    @Override
    public void visit(GenericThematicSurface genericThematicSurface) {
        visit((AbstractThematicSurface) genericThematicSurface);

        for (ADEProperty<?> property : new ArrayList<>(genericThematicSurface.getADEPropertiesOfGenericThematicSurface()))
            visit(property);
    }

    @Override
    public void visit(GenericTimeseries genericTimeseries) {
        visit((AbstractAtomicTimeseries) genericTimeseries);

        for (TimeValuePairProperty property : genericTimeseries.getTimeValuePairs()) {
            if (property.getObject() != null)
                visit(property.getObject());
        }

        for (ADEProperty<?> property : new ArrayList<>(genericTimeseries.getADEPropertiesOfGenericTimeseries()))
            visit(property);
    }

    @Override
    public void visit(GenericUnoccupiedSpace genericUnoccupiedSpace) {
        visit((AbstractUnoccupiedSpace) genericUnoccupiedSpace);

        for (ADEProperty<?> property : new ArrayList<>(genericUnoccupiedSpace.getADEPropertiesOfGenericUnoccupiedSpace()))
            visit(property);
    }

    @Override
    public void visit(GeoreferencedTexture georeferencedTexture) {
        visit((AbstractTexture) georeferencedTexture);

        visit(georeferencedTexture.getReferencePoint());

        for (ADEProperty<?> property : new ArrayList<>(georeferencedTexture.getADEPropertiesOfGeoreferencedTexture()))
            visit(property);
    }

    @Override
    public void visit(GridCoverage gridCoverage) {
        visit((AbstractDiscreteCoverage<?>) gridCoverage);
    }

    @Override
    public void visit(GroundSurface groundSurface) {
        visit((AbstractConstructionSurface) groundSurface);

        for (ADEProperty<?> property : new ArrayList<>(groundSurface.getADEPropertiesOfGroundSurface()))
            visit(property);
    }

    @Override
    public void visit(Hole hole) {
        visit((AbstractUnoccupiedSpace) hole);

        for (ADEProperty<?> property : new ArrayList<>(hole.getADEPropertiesOfHole()))
            visit(property);
    }

    @Override
    public void visit(HoleSurface holeSurface) {
        visit((AbstractThematicSurface) holeSurface);

        for (ADEProperty<?> property : new ArrayList<>(holeSurface.getADEPropertiesOfHoleSurface()))
            visit(property);
    }

    @Override
    public void visit(HollowSpace hollowSpace) {
        visit((AbstractUnoccupiedSpace) hollowSpace);

        for (TunnelFurnitureProperty property : new ArrayList<>(hollowSpace.getTunnelFurniture()))
            visit(property);

        for (TunnelInstallationProperty property : new ArrayList<>(hollowSpace.getTunnelInstallations()))
            visit(property);

        if (hollowSpace.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfHollowSpace deprecatedProperties = hollowSpace.getDeprecatedProperties();

            visit(deprecatedProperties.getLod4Solid());
            visit(deprecatedProperties.getLod4MultiSurface());
        }

        for (ADEProperty<?> property : new ArrayList<>(hollowSpace.getADEPropertiesOfHollowSpace()))
            visit(property);
    }

    @Override
    public void visit(ImplicitGeometry implicitGeometry) {
        visit((AbstractGML) implicitGeometry);

        for (AbstractAppearanceProperty property : new ArrayList<>(implicitGeometry.getAppearances()))
            visit(property);

        visit(implicitGeometry.getReferencePoint());
        visit(implicitGeometry.getRelativeGeometry());
    }

    @Override
    public void visit(InteriorWallSurface interiorWallSurface) {
        visit((AbstractConstructionSurface) interiorWallSurface);

        for (ADEProperty<?> property : new ArrayList<>(interiorWallSurface.getADEPropertiesOfInteriorWallSurface()))
            visit(property);
    }

    @Override
    public void visit(Intersection intersection) {
        visit((AbstractTransportationSpace) intersection);

        for (ADEProperty<?> property : new ArrayList<>(intersection.getADEPropertiesOfIntersection()))
            visit(property);
    }

    @Override
    public void visit(LandUse landUse) {
        visit((AbstractThematicSurface) landUse);

        for (ADEProperty<?> property : new ArrayList<>(landUse.getADEPropertiesOfLandUse()))
            visit(property);
    }

    @Override
    public void visit(Marking marking) {
        visit((AbstractThematicSurface) marking);

        for (ADEProperty<?> property : new ArrayList<>(marking.getADEPropertiesOfMarking()))
            visit(property);
    }

    @Override
    public void visit(MassPointRelief massPointRelief) {
        visit((AbstractReliefComponent) massPointRelief);

        visit(massPointRelief.getPointCloud());
        visit(massPointRelief.getReliefPoints());

        for (ADEProperty<?> property : new ArrayList<>(massPointRelief.getADEPropertiesOfMassPointRelief()))
            visit(property);
    }

    @Override
    public void visit(MultiCurveCoverage multiCurveCoverage) {
        visit((AbstractDiscreteCoverage<?>) multiCurveCoverage);
    }

    @Override
    public void visit(MultiPointCoverage multiPointCoverage) {
        visit((AbstractDiscreteCoverage<?>) multiPointCoverage);
    }

    @Override
    public void visit(MultiSolidCoverage multiSolidCoverage) {
        visit((AbstractDiscreteCoverage<?>) multiSolidCoverage);
    }

    @Override
    public void visit(MultiSurfaceCoverage multiSurfaceCoverage) {
        visit((AbstractDiscreteCoverage<?>) multiSurfaceCoverage);
    }

    @Override
    public void visit(OtherConstruction otherConstruction) {
        visit((AbstractConstruction) otherConstruction);

        for (ADEProperty<?> property : new ArrayList<>(otherConstruction.getADEPropertiesOfOtherConstruction()))
            visit(property);
    }

    @Override
    public void visit(OuterCeilingSurface outerCeilingSurface) {
        visit((AbstractConstructionSurface) outerCeilingSurface);

        for (ADEProperty<?> property : new ArrayList<>(outerCeilingSurface.getADEPropertiesOfOuterCeilingSurface()))
            visit(property);
    }

    @Override
    public void visit(OuterFloorSurface outerFloorSurface) {
        visit((AbstractConstructionSurface) outerFloorSurface);

        for (ADEProperty<?> property : new ArrayList<>(outerFloorSurface.getADEPropertiesOfOuterFloorSurface()))
            visit(property);
    }

    @Override
    public void visit(ParameterizedTexture parameterizedTexture) {
        visit((AbstractTexture) parameterizedTexture);

        for (ADEProperty<?> property : new ArrayList<>(parameterizedTexture.getADEPropertiesOfParameterizedTexture()))
            visit(property);
    }

    @Override
    public void visit(PlantCover plantCover) {
        visit((AbstractVegetationObject) plantCover);

        if (plantCover.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfPlantCover deprecatedProperties = plantCover.getDeprecatedProperties();

            visit(deprecatedProperties.getLod1MultiSurface());
            visit(deprecatedProperties.getLod4MultiSurface());
            visit(deprecatedProperties.getLod1MultiSolid());
            visit(deprecatedProperties.getLod2MultiSolid());
            visit(deprecatedProperties.getLod3MultiSolid());
            visit(deprecatedProperties.getLod4MultiSolid());
        }

        for (ADEProperty<?> property : new ArrayList<>(plantCover.getADEPropertiesOfPlantCover()))
            visit(property);
    }

    @Override
    public void visit(PointCloud pointCloud) {
        visit((AbstractPointCloud) pointCloud);

        visit(pointCloud.getPoints());

        for (ADEProperty<?> property : new ArrayList<>(pointCloud.getADEPropertiesOfPointCloud()))
            visit(property);
    }

    @Override
    public void visit(Railway railway) {
        visit((AbstractTransportationSpace) railway);

        for (SectionProperty property : new ArrayList<>(railway.getSections()))
            visit(property);

        for (IntersectionProperty property : new ArrayList<>(railway.getIntersections()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(railway.getADEPropertiesOfRailway()))
            visit(property);
    }

    @Override
    public void visit(RasterRelief rasterRelief) {
        visit((AbstractReliefComponent) rasterRelief);

        visit(rasterRelief.getGrid());

        for (ADEProperty<?> property : new ArrayList<>(rasterRelief.getADEPropertiesOfRasterRelief()))
            visit(property);
    }

    @Override
    public void visit(RectifiedGridCoverage rectifiedGridCoverage) {
        visit((AbstractDiscreteCoverage<?>) rectifiedGridCoverage);
    }

    @Override
    public void visit(ReliefFeature reliefFeature) {
        visit((AbstractSpaceBoundary) reliefFeature);

        for (AbstractReliefComponentProperty property : new ArrayList<>(reliefFeature.getReliefComponents()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(reliefFeature.getADEPropertiesOfReliefFeature()))
            visit(property);
    }

    @Override
    public void visit(Road road) {
        visit((AbstractTransportationSpace) road);

        for (SectionProperty property : new ArrayList<>(road.getSections()))
            visit(property);

        for (IntersectionProperty property : new ArrayList<>(road.getIntersections()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(road.getADEPropertiesOfRoad()))
            visit(property);
    }

    public void visit(Role role) {
        visit((AbstractGML) role);

        visit(role.getGroupMember());
    }

    @Override
    public void visit(RoofSurface roofSurface) {
        visit((AbstractConstructionSurface) roofSurface);

        for (ADEProperty<?> property : new ArrayList<>(roofSurface.getADEPropertiesOfRoofSurface()))
            visit(property);
    }

    @Override
    public void visit(Section section) {
        visit((AbstractTransportationSpace) section);

        for (ADEProperty<?> property : new ArrayList<>(section.getADEPropertiesOfSection()))
            visit(property);
    }

    @Override
    public void visit(SolitaryVegetationObject solitaryVegetationObject) {
        visit((AbstractVegetationObject) solitaryVegetationObject);

        if (solitaryVegetationObject.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfSolitaryVegetationObject deprecatedProperties = solitaryVegetationObject.getDeprecatedProperties();

            visit(deprecatedProperties.getLod1Geometry());
            visit(deprecatedProperties.getLod2Geometry());
            visit(deprecatedProperties.getLod3Geometry());
            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(solitaryVegetationObject.getADEPropertiesOfSolitaryVegetationObject()))
            visit(property);
    }

    @Override
    public void visit(Square square) {
        visit((AbstractTransportationSpace) square);

        for (ADEProperty<?> property : new ArrayList<>(square.getADEPropertiesOfSquare()))
            visit(property);
    }

    @Override
    public void visit(StandardFileTimeseries standardFileTimeseries) {
        visit((AbstractAtomicTimeseries) standardFileTimeseries);

        for (ADEProperty<?> property : new ArrayList<>(standardFileTimeseries.getADEPropertiesOfStandardFileTimeseries()))
            visit(property);
    }

    @Override
    public void visit(Storey storey) {
        visit((AbstractBuildingSubdivision) storey);

        for (ADEProperty<?> property : new ArrayList<>(storey.getADEPropertiesOfStorey()))
            visit(property);
    }

    @Override
    public void visit(TabulatedFileTimeseries tabulatedFileTimeseries) {
        visit((AbstractAtomicTimeseries) tabulatedFileTimeseries);

        for (ADEProperty<?> property : new ArrayList<>(tabulatedFileTimeseries.getADEPropertiesOfTabulatedFileTimeseries()))
            visit(property);
    }

    @Override
    public void visit(TextureAssociation textureAssociation) {
        visit((AbstractGML) textureAssociation);
    }

    @Override
    public void visit(TimeInstant timeInstant) {
        visit((AbstractGML) timeInstant);
    }

    @Override
    public void visit(TimePeriod timePeriod) {
        visit((AbstractGML) timePeriod);

        visit(timePeriod.getBegin());
        visit(timePeriod.getEnd());
    }

    @Override
    public void visit(TINRelief tinRelief) {
        visit((AbstractReliefComponent) tinRelief);

        visit(tinRelief.getTin());

        for (ADEProperty<?> property : new ArrayList<>(tinRelief.getADEPropertiesOfTINRelief()))
            visit(property);
    }

    @Override
    public void visit(Track track) {
        visit((AbstractTransportationSpace) track);

        for (SectionProperty property : new ArrayList<>(track.getSections()))
            visit(property);

        for (IntersectionProperty property : new ArrayList<>(track.getIntersections()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(track.getADEPropertiesOfTrack()))
            visit(property);
    }

    @Override
    public void visit(TrafficArea trafficArea) {
        visit((AbstractThematicSurface) trafficArea);

        for (ADEProperty<?> property : new ArrayList<>(trafficArea.getADEPropertiesOfTrafficArea()))
            visit(property);
    }

    @Override
    public void visit(TrafficSpace trafficSpace) {
        visit((AbstractUnoccupiedSpace) trafficSpace);

        for (TrafficSpaceProperty property : new ArrayList<>(trafficSpace.getPredecessors()))
            visit(property);

        for (TrafficSpaceProperty property : new ArrayList<>(trafficSpace.getSuccessors()))
            visit(property);

        for (ClearanceSpaceProperty property : new ArrayList<>(trafficSpace.getClearanceSpaces()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(trafficSpace.getADEPropertiesOfTrafficSpace()))
            visit(property);
    }

    @Override
    public void visit(TransportationComplex transportationComplex) {
        visit((AbstractTransportationSpace) transportationComplex);

        for (ADEProperty<?> property : new ArrayList<>(transportationComplex.getADEPropertiesOfTransportationComplex()))
            visit(property);
    }

    @Override
    public void visit(Tunnel tunnel) {
        visit((AbstractTunnel) tunnel);

        for (TunnelPartProperty property : new ArrayList<>(tunnel.getTunnelParts()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(tunnel.getADEPropertiesOfTunnel()))
            visit(property);
    }

    @Override
    public void visit(TunnelConstructiveElement tunnelConstructiveElement) {
        visit((AbstractConstructiveElement) tunnelConstructiveElement);

        for (ADEProperty<?> property : new ArrayList<>(tunnelConstructiveElement.getADEPropertiesOfTunnelConstructiveElement()))
            visit(property);
    }

    @Override
    public void visit(TunnelFurniture tunnelFurniture) {
        visit((AbstractFurniture) tunnelFurniture);

        if (tunnelFurniture.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfTunnelFurniture deprecatedProperties = tunnelFurniture.getDeprecatedProperties();

            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(tunnelFurniture.getADEPropertiesOfTunnelFurniture()))
            visit(property);
    }

    @Override
    public void visit(TunnelInstallation tunnelInstallation) {
        visit((AbstractInstallation) tunnelInstallation);

        if (tunnelInstallation.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfTunnelInstallation deprecatedProperties = tunnelInstallation.getDeprecatedProperties();

            visit(deprecatedProperties.getLod2Geometry());
            visit(deprecatedProperties.getLod3Geometry());
            visit(deprecatedProperties.getLod4Geometry());
            visit(deprecatedProperties.getLod4ImplicitRepresentation());
        }

        for (ADEProperty<?> property : new ArrayList<>(tunnelInstallation.getADEPropertiesOfTunnelInstallation()))
            visit(property);
    }

    @Override
    public void visit(TunnelPart tunnelPart) {
        visit((AbstractTunnel) tunnelPart);

        for (ADEProperty<?> property : new ArrayList<>(tunnelPart.getADEPropertiesOfTunnelPart()))
            visit(property);
    }

    @Override
    public void visit(ValueArray valueArray) {
        visit((CompositeValue) valueArray);
    }

    @Override
    public void visit(Version version) {
        visit((AbstractVersion) version);

        for (AbstractFeatureWithLifespanProperty property : new ArrayList<>(version.getVersionMembers()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(version.getADEPropertiesOfVersion()))
            visit(property);
    }

    @Override
    public void visit(VersionTransition versionTransition) {
        visit((AbstractVersionTransition) versionTransition);

        visit(versionTransition.getFrom());
        visit(versionTransition.getTo());

        for (ADEProperty<?> property : new ArrayList<>(versionTransition.getADEPropertiesOfVersionTransition()))
            visit(property);
    }

    @Override
    public void visit(WallSurface wallSurface) {
        visit((AbstractConstructionSurface) wallSurface);

        for (ADEProperty<?> property : new ArrayList<>(wallSurface.getADEPropertiesOfWallSurface()))
            visit(property);
    }

    @Override
    public void visit(WaterBody waterBody) {
        visit((AbstractOccupiedSpace) waterBody);

        if (waterBody.hasDeprecatedProperties()) {
            DeprecatedPropertiesOfWaterBody deprecatedProperties = waterBody.getDeprecatedProperties();

            visit(deprecatedProperties.getLod1MultiCurve());
            visit(deprecatedProperties.getLod1MultiSurface());
            visit(deprecatedProperties.getLod4Solid());
        }

        for (ADEProperty<?> property : new ArrayList<>(waterBody.getADEPropertiesOfWaterBody()))
            visit(property);
    }

    @Override
    public void visit(WaterGroundSurface waterGroundSurface) {
        visit((AbstractWaterBoundarySurface) waterGroundSurface);

        for (ADEProperty<?> property : new ArrayList<>(waterGroundSurface.getADEPropertiesOfWaterGroundSurface()))
            visit(property);
    }

    @Override
    public void visit(WaterSurface waterSurface) {
        visit((AbstractWaterBoundarySurface) waterSurface);

        for (ADEProperty<?> property : new ArrayList<>(waterSurface.getADEPropertiesOfWaterSurface()))
            visit(property);
    }

    @Override
    public void visit(Waterway waterway) {
        visit((AbstractTransportationSpace) waterway);

        for (SectionProperty property : new ArrayList<>(waterway.getSections()))
            visit(property);

        for (IntersectionProperty property : new ArrayList<>(waterway.getIntersections()))
            visit(property);

        for (ADEProperty<?> property : new ArrayList<>(waterway.getADEPropertiesOfWaterway()))
            visit(property);
    }

    @Override
    public void visit(Window window) {
        visit((AbstractFillingElement) window);

        for (ADEProperty<?> property : new ArrayList<>(window.getADEPropertiesOfWindow()))
            visit(property);
    }

    @Override
    public void visit(WindowSurface windowSurface) {
        visit((AbstractFillingSurface) windowSurface);

        for (ADEProperty<?> property : new ArrayList<>(windowSurface.getADEPropertiesOfWindowSurface()))
            visit(property);
    }

    @Override
    public void visit(X3DMaterial x3dMaterial) {
        visit((AbstractSurfaceData) x3dMaterial);

        for (ADEProperty<?> property : new ArrayList<>(x3dMaterial.getADEPropertiesOfX3DMaterial()))
            visit(property);
    }

    @Override
    public void visit(ADEObject adeObject) {
        if (adeWalkerHelper != null && adeWalkerHelper.visit(adeObject))
            return;

        if (adeObject instanceof AbstractFeature)
            adeObject.accept(this);
        else if (adeObject instanceof AbstractGML)
            visit((AbstractGML) adeObject);
        else if (adeObject instanceof ADEProperty<?>) {
            Object value = ((ADEProperty<?>) adeObject).getValue();
            if (value instanceof AbstractAssociation<?>)
                visitProperty((AbstractAssociation<?>) value);
            else
                visitObject(value);
        }
    }

    public void visit(GenericElement genericElement) {
    }

    public void visit(AbstractArrayProperty<?> property) {
        if (property != null) {
            for (Object object : property.getObjects()) {
                if (shouldWalk)
                    visitObject(object);
            }
        }
    }

    public void visit(AbstractInlineOrByReferenceProperty<?> property) {
        if (shouldWalk && property != null && property.getObject() != null)
            visitObject(property.getObject());
    }

    public void visit(AbstractInlineProperty<?> property) {
        if (shouldWalk && property != null && property.getObject() != null)
            visitObject(property.getObject());
    }

    public void visit(FeatureProperty<?> property) {
        if (shouldWalk && property != null) {
            if (property.getObject() != null)
                visitObject(property.getObject());
            else if (property.getGenericElement() != null)
                visit(property.getGenericElement());
        }
    }

    public void visit(AbstractFeatureMember<?> member) {
        if (shouldWalk && member != null) {
            if (member.getObject() != null)
                visitObject(member.getObject());
            else if (member.getGenericElement() != null)
                visit(member.getGenericElement());
        }
    }

    private void visitObject(Object object) {
        if (object instanceof ADEObject)
            visit((ADEObject) object);
        else if (object instanceof Visitable)
            ((Visitable) object).accept(this);
        else if (object instanceof AbstractCoverage)
            ((AbstractCoverage<?>) object).accept(this);
        else if (object instanceof AbstractGeometry)
            ((AbstractGeometry) object).accept(this);
    }

    private void visitProperty(AbstractAssociation<?> property) {
        if (property instanceof FeatureProperty<?>)
            visit((FeatureProperty<?>) property);
        else if (property instanceof AbstractFeatureMember<?>)
            visit((AbstractFeatureMember<?>) property);
        else if (property instanceof AbstractArrayProperty<?>)
            visit((AbstractArrayProperty<?>) property);
        else if (property instanceof AbstractInlineOrByReferenceProperty<?>)
            visit((AbstractInlineOrByReferenceProperty<?>) property);
        else if (property instanceof AbstractInlineProperty<?>)
            visit((AbstractInlineProperty<?>) property);
    }

    private void visit(Value value) {
        if (shouldWalk) {
            if (value.getValue() != null)
                visit(value.getValue());
            else if (value.getGeometry() != null)
                value.getGeometry().accept(this);
            else if (value.getGenericElement() != null)
                visit(value.getGenericElement());
        }
    }

    private void visit(AbstractValue value) {
        if (value instanceof ADEObject)
            visit((ADEObject) value);
        else if (value instanceof CompositeValue)
            ((CompositeValue) value).accept(this);
    }

    private void visit(TimeInstantProperty property) {
        if (shouldWalk && property != null && property.getObject() != null)
            property.getObject().accept(this);
    }

    private void visit(TimeValuePair timeValuePair) {
        if (shouldWalk) {
            if (timeValuePair.getGeometryValue() != null)
                visit(timeValuePair.getGeometryValue());
            else if (timeValuePair.getImplicitGeometryValue() != null)
                visit(timeValuePair.getImplicitGeometryValue());
            else if (timeValuePair.getAppearanceValue() != null)
                visit(timeValuePair.getAppearanceValue());
        }
    }
}