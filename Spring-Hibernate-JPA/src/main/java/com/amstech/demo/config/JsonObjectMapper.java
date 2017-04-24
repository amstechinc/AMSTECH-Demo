/*
* Copyright 2017 AMSTECH INCORPORATION PRIVATE LIMITED.
* (www.amstechinc.com)
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

package com.amstech.demo.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JsonObjectMapper extends ObjectMapper {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonObjectMapper() {
		JsonSerializer<Double> doubleSerializer = new JsonSerializer<Double>() {

			@Override
			public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider)
					throws IOException, JsonProcessingException {

				jgen.writeNumber(value);

			}

		};

		Map<Class<?>, JsonSerializer<?>> mapping = new HashMap<Class<?>, JsonSerializer<?>>();
		mapping.put(double.class, doubleSerializer);

		this.registerModule(new JodaModule());

		this.registerModule(new JacksonMixInModule());

		Hibernate4Module module = new Hibernate4Module();
		module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
		this.registerModule(module);

		this.enable(SerializationFeature.INDENT_OUTPUT);
	}
}
