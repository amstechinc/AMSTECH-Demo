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

import com.amstech.demo.entity.Country;
import com.amstech.demo.entity.State;
import com.amstech.demo.service.CountryService;
import com.amstech.demo.view.StateView;

@Component
public class StateViewToEntityConvertor implements Converter<StateView, State> {
	private static final Logger logger = LoggerFactory.getLogger(StateViewToEntityConvertor.class);

	@Autowired
	private CountryService countryService;

	@Autowired
	@Qualifier("modelMapper")
	private ModelMapper modelMapper;

	@Override
	public State convert(MappingContext<StateView, State> context) {
		State destination = null;
		try {
			StateView source = context.getSource();
			logger.info("source - CountryView's : country-id={}, country-name={} ", source.getId(), source.getName());

			logger.info("Fetching entity for country-id:{}", source.getCountryId());
			Country country = countryService.findOne(source.getCountryId());
			logger.info("Fetched entity country-id={}, country-name={} ", country.getId(), country.getName());

			destination = modelMapper.map(source, State.class);
			destination.setCountry(country);
		}

		catch (Exception e) {
			logger.error("failed to convert StateViewToEntity due to :{}", e, e);
		}
		return destination;

	}
}
