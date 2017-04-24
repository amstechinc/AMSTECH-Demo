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

package com.amstech.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.demo.entity.Restaurant;
import com.amstech.demo.service.RestaurantService;
import com.amstech.demo.util.RestResponse;
import com.amstech.demo.view.RestaurantView;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	@Autowired(required = true)
	private RestaurantService restaurantService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public RestResponse save(@RequestBody RestaurantView restaurantView) {
		try {
			logger.info("save restaurant:{}", restaurantView);
			restaurantService.save(restaurantView);

		} catch (Exception e) {
			return RestResponse.build().withError("Failed to add restaurant due to :" + e);
		}
		return RestResponse.build().withSuccess("Data added successfully.");

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResponse find(@PathVariable("id") Integer id) {
		try {

			logger.info("find restaurant for Id:{}", id);
			Restaurant restaurant = restaurantService.findOne(id);
			if (restaurant != null) {
				return RestResponse.build().withSuccess().withData(restaurant);
			} else {
				return RestResponse.build().withError("No data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find restaurant due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find Data due to :" + e);
		}

	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public RestResponse update(@RequestBody RestaurantView restaurantView) {
		try {
			logger.info("update restaurant:{}", restaurantView);

			restaurantService.update(restaurantView);
		} catch (Exception e) {
			logger.error("Failed to update restaurant due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to update restaurant due to :" + e);
		}
		return RestResponse.build().withSuccess("Data updated successfully.");

	}

	@RequestMapping(method = RequestMethod.GET)
	public RestResponse filterBy(@RequestParam(value = "page", required = false, defaultValue = "-1") int page,
			@RequestParam(value = "size", required = false, defaultValue = "-1") int size,
			@RequestParam(value = "cityId", required = false, defaultValue = "-1") Integer cityId,
			@RequestParam(value = "search", required = false, defaultValue = "-1") String search) {

		try {
			logger.info("find Filtered restaurant using pagination cityId:{}, search:{}", cityId, search);
			long totalRecords = restaurantService.countByFilter(cityId, search);
			List<Restaurant> restaurant = restaurantService.filterBy(page, size, cityId, search);
			if (restaurant != null) {
				return RestResponse.build().withSuccess().withData(restaurant).withTotalRecords(totalRecords);
			} else {
				return RestResponse.build().withError("No data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find restaurant due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find restaurant due to :" + e);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public RestResponse findAll() {

		try {
			List<Restaurant> restaurant = restaurantService.findAll();
			if (restaurant != null) {
				return RestResponse.build().withSuccess().withData(restaurant);
			} else {
				return RestResponse.build().withError("No data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find restaurant due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find restaurant due to :" + e);
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public RestResponse count() {
		try {
			Map<String, Integer> map = new HashMap<>(1);
			map.put(RestResponse.KEY_ROW_COUNT, restaurantService.count());
			return RestResponse.build().withSuccess().withData(map);

		} catch (Exception e) {
			logger.error("Failed to find restaurant due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find restaurant due to :" + e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestResponse delete(@PathVariable("id") Integer id) {
		try {
			logger.info("delete restaurant for Id:{}", id);
			int rowCount = restaurantService.markDelete(id);
			if (rowCount > 0) {
				return RestResponse.build().withSuccess("Data deleted successfully.");
			} else {
				return RestResponse.build().withError("No record deleted.");
			}
		} catch (Exception e) {
			logger.error("Failed to delete restaurant due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete restaurant due to :" + e);
		}
	}

}
