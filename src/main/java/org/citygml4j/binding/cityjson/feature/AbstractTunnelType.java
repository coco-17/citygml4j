/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 * 
 * Copyright 2013-2017 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.citygml4j.binding.cityjson.feature;

import com.google.gson.annotations.JsonAdapter;
import org.citygml4j.binding.cityjson.geometry.GeometryTypeName;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTunnelType extends AbstractCityObjectType {
	@JsonAdapter(TunnelAttributesAdapter.class)
	private TunnelAttributes attributes;
	private List<String> children;
	
	AbstractTunnelType() {
	}
	
	public AbstractTunnelType(String gmlId) {
		super(gmlId);
	}
	
	@Override
	public TunnelAttributes newAttributes() {
		attributes = new TunnelAttributes();
		return attributes;
	}
	
	@Override
	public boolean isSetAttributes() {
		return attributes != null;
	}

	@Override
	public TunnelAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(TunnelAttributes attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public void unsetAttributes() {
		attributes = null;
	}

	public boolean isSetChildren() {
		return children != null && !children.isEmpty();
	}

	public void addChild(String child) {
		if (children == null)
			children = new ArrayList<>();

		children.add(child);
	}

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> Children) {
		this.children = Children;
	}

	public void unsetChildren() {
		children = null;
	}

	@Override
	public boolean isValidGeometryType(GeometryTypeName type) {
		return type == GeometryTypeName.MULTI_SURFACE
				|| type == GeometryTypeName.SOLID
				|| type == GeometryTypeName.COMPOSITE_SOLID
				|| type == GeometryTypeName.GEOMETRY_INSTANCE;
	}
}
