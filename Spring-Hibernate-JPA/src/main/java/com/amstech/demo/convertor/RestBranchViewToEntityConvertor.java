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

import com.amstech.demo.entity.RestBranch;
import com.amstech.demo.entity.Restaurant;
import com.amstech.demo.service.RestaurantService;
import com.amstech.demo.view.RestBranchView;

@Component
public class RestBranchViewToEntityConvertor implements Converter<RestBranchView, RestBranch>

{
	private static final Logger logger = LoggerFactory.getLogger(RestBranchViewToEntityConvertor.class);

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	@Qualifier("modelMapper")
	private ModelMapper modelMapper;

	@Override
	public RestBranch convert(MappingContext<RestBranchView, RestBranch> context) {
		RestBranch destination = null;
		try {
			RestBranchView source = context.getSource();
			logger.info("source - RestBranchView's : branch-id={}, branch-address={} ", source.getId(),
					source.getAddress());

			logger.info("Fetching entity for Restaurant-id:{}", source.getRestaurantId());
			Restaurant restaurant = restaurantService.findOne(source.getRestaurantId());
			logger.info("Fetched entity restaurant-id={}", restaurant.getId());

			destination = modelMapper.map(source, RestBranch.class);
			destination.setRestaurant(restaurant);
		} catch (Exception e) {
			logger.error("failed to convert RestBranchViewToEntity due to :{}", e, e);
		}
		return destination;
	}
}
