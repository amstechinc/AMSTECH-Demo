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

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amstech.demo.convertor.CityViewToEntityConvertor;
import com.amstech.demo.convertor.RestBranchViewToEntityConvertor;
import com.amstech.demo.convertor.RestaurantViewToEntityConvertor;
import com.amstech.demo.convertor.StateViewToEntityConvertor;

@Configuration
public class ModelMapperConverterConfig {

	@Autowired
	@Qualifier("modelMapperWithConverter")
	private ModelMapper modelMapper;

	@Bean
	public CityViewToEntityConvertor getCityViewToEntityConvertor() {
		CityViewToEntityConvertor convertor = new CityViewToEntityConvertor();
		modelMapper.addConverter(convertor);
		return convertor;
	}

	@Bean
	public StateViewToEntityConvertor getStateViewToEntityConvertor() {
		StateViewToEntityConvertor convertor = new StateViewToEntityConvertor();
		modelMapper.addConverter(convertor);
		return convertor;
	}

	@Bean
	public RestaurantViewToEntityConvertor getRestaurantViewToEntityConvertor() {
		RestaurantViewToEntityConvertor convertor = new RestaurantViewToEntityConvertor();
		modelMapper.addConverter(convertor);
		return convertor;
	}

	@Bean
	public RestBranchViewToEntityConvertor getRestBranchViewToEntityConvertor() {
		RestBranchViewToEntityConvertor convertor = new RestBranchViewToEntityConvertor();
		modelMapper.addConverter(convertor);
		return convertor;
	}

}
