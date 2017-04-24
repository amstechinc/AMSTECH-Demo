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

package com.amstech.demo.service;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amstech.demo.entity.Restaurant;
import com.amstech.demo.repo.RestaurantRepo;
import com.amstech.demo.repo.custom.RestaurantRepoCustom;
import com.amstech.demo.util.DateHelper;
import com.amstech.demo.view.RestaurantView;

@Service
@Transactional
public class RestaurantService {

	private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

	@Autowired(required = true)
	private RestaurantRepo restaurantRepo;

	@Autowired(required = true)
	private RestaurantService restaurantService;

	@Autowired(required = true)
	private RestaurantRepoCustom restaurantRepoCustom;

	@Autowired
	@Qualifier("modelMapperWithConverter")
	private ModelMapper modelMapper;

	public void save(RestaurantView restaurantView) {
		try {
			Restaurant restaurant = modelMapper.map(restaurantView, Restaurant.class);
			restaurant.setCreateTime(DateHelper.getDate(DateHelper.dateFormat));
			restaurant.setIsDeleted(0);
			Restaurant ref = restaurantRepo.save(restaurant);
			System.out.println(ref.getId());
		} catch (Exception e) {
			logger.error("Failed to save restaurant due to:{}", e.toString(), e);
		}
	}

	public void update(RestaurantView restaurantView) {
		try {
			Restaurant restaurant = modelMapper.map(restaurantView, Restaurant.class);

			restaurant.setUpdateTime(DateHelper.getDate(DateHelper.dateFormat));

			Restaurant ref = restaurantService.findOne(restaurant.getId());
			restaurant.setCreateTime(ref.getCreateTime());
			restaurant.setIsDeleted(ref.getIsDeleted());
			restaurantRepo.save(restaurant);
		} catch (ParseException e) {
			logger.error("Failed to update restaurant due to:{}", e.toString(), e);
		}
	}

	public List<Restaurant> filterBy(int page, int size, Integer cityId, String search) {

		return restaurantRepoCustom.findByFilter(page, size, cityId, search);
	}

	public long countByFilter(Integer cityId, String search) {

		return restaurantRepoCustom.countByFilter(cityId, search);
	}

	public List<Restaurant> findAll() {

		return restaurantRepo.findAll();
	}

	public int markDelete(Integer id) {
		return restaurantRepo.markDeleted(id);
	}

	public Restaurant findOne(Integer id) {
		return restaurantRepo.findOne(id);
	}

	public int count() {
		return (int) restaurantRepo.count();
	}

}
