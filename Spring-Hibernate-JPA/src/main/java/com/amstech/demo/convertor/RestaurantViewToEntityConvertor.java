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
import com.amstech.demo.entity.Restaurant;
import com.amstech.demo.service.CityService;
import com.amstech.demo.view.RestaurantView;

@Component
public class RestaurantViewToEntityConvertor implements Converter<RestaurantView, Restaurant> {
	private static final Logger logger = LoggerFactory.getLogger(RestaurantViewToEntityConvertor.class);

	@Autowired
	private CityService cityService;

	@Autowired
	@Qualifier("modelMapper")
	private ModelMapper modelMapper;

	@Override
	public Restaurant convert(MappingContext<RestaurantView, Restaurant> context) {
		Restaurant destination = null;
		try {
			RestaurantView source = context.getSource();
			logger.info("source - RestaurantView's : restaurant-id={} ", source.getId());

			logger.info("Fetching entity for city-id:{}", source.getCityId());
			City city = cityService.findOne(source.getCityId());
			logger.info("Fetched entity city-id={}", city.getId());

			destination = modelMapper.map(source, Restaurant.class);
			destination.setCity(city);

		} catch (Exception e) {
			logger.error("failed to convert RestaurantViewToEntity due to :{}", e, e);
		}
		return destination;
	}

}
