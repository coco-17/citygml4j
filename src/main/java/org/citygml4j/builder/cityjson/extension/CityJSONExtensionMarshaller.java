/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 *
 * Copyright 2013-2020 Claus Nagel <claus.nagel@gmail.com>
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

package org.citygml4j.builder.cityjson.extension;

import org.citygml4j.builder.cityjson.marshal.citygml.ade.ADEMarshallerHelper;
import org.citygml4j.cityjson.CityJSON;
import org.citygml4j.cityjson.extension.Extension;
import org.citygml4j.cityjson.feature.AbstractCityObjectType;
import org.citygml4j.cityjson.geometry.SemanticsType;
import org.citygml4j.model.citygml.ade.binding.ADEModelObject;

public interface CityJSONExtensionMarshaller {
    void setADEMarshallerHelper(ADEMarshallerHelper helper);
    AbstractCityObjectType marshalCityObject(ADEModelObject src, CityJSON cityJSON);
    SemanticsType marshalSemanticSurface(ADEModelObject src);
    Extension marshalGenericApplicationProperty(ADEModelObject src, ADEPropertyContext context);
}