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

package com.amstech.demo.convertor;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amstech.demo.entity.City;
import com.amstech.demo.entity.State;
import com.amstech.demo.service.StateService;
import com.amstech.demo.view.CityView;

@Component
public class CityViewToEntityConvertor implements Converter<CityView, City> {
	private static final Logger logger = LoggerFactory.getLogger(CityViewToEntityConvertor.class);

	@Autowired
	private StateService stateService;

	@Autowired
	@Qualifier("modelMapper")
	private ModelMapper modelMapper;

	@Override
	public City convert(MappingContext<CityView, City> context) {
		City destination = null;
		try {
			CityView source = context.getSource();
			logger.info("source - CityView's : city-id={}, city-name={} ", source.getId(), source.getName());

			logger.info("Fetching entity for state-id:{}", source.getStateId());
			State state = stateService.findOne(source.getStateId());
			logger.info("Fetched entity state-id={}, state-name={} ", state.getId(), state.getName());

			destination = modelMapper.map(source, City.class);
			destination.setState(state);
		}

		catch (Exception e) {
			logger.error("failed to convert CityViewToEntity due to :{}", e, e);
		}
		return destination;

	}

}
