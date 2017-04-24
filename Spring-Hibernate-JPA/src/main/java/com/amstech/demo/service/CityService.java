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

import com.amstech.demo.entity.City;
import com.amstech.demo.repo.CityRepo;
import com.amstech.demo.util.DateHelper;
import com.amstech.demo.view.CityView;

@Service
@Transactional
public class CityService {

	private static final Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired(required = true)
	private CityRepo cityRepo;

	@Autowired(required = true)
	private CityService cityService;

	@Autowired
	@Qualifier("modelMapperWithConverter")
	private ModelMapper modelMapper;

	public void save(CityView cityView) {
		try {
			City city = modelMapper.map(cityView, City.class);
			city.setCreateTime(DateHelper.getDate(DateHelper.dateFormat));
			cityRepo.save(city);
		} catch (Exception e) {
			logger.error("Failed to save city due to:{}", e.toString(), e);

		}
	}

	public void update(CityView cityView) {
		try {
			City city = modelMapper.map(cityView, City.class);

			city.setUpdateTime(DateHelper.getDate(DateHelper.dateFormat));

			City ref = cityService.findOne(city.getId());
			city.setCreateTime(ref.getCreateTime());
			city.setIsDeleted(ref.getIsDeleted());
			cityRepo.save(city);
		} catch (ParseException e) {
			logger.error("Failed to update city due to:{}", e.toString(), e);

		}
	}

	public List<City> findAll() {
		return cityRepo.findAll();
	}

	public List<City> findAllBySId(Integer sId) {
		return cityRepo.findAllBySId(sId);
	}

	public City findOne(Integer id) {
		return cityRepo.findOne(id);
	}

	public int markDelete(Integer id) {
		return cityRepo.markDeleted(id);
	}

	public int count() {
		return (int) cityRepo.count();
	}
}
