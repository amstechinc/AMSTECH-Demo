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

import com.amstech.demo.entity.Country;
import com.amstech.demo.repo.CountryRepo;
import com.amstech.demo.util.DateHelper;
import com.amstech.demo.view.CountryView;

@Service

public class CountryService {
	private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

	@Autowired(required = true)
	private CountryRepo countryRepo;

	@Autowired(required = true)
	private CountryService countryService;

	@Autowired
	@Qualifier("modelMapperWithConverter")
	private ModelMapper modelMapper;

	public void save(CountryView countryView) {
		try {
			Country country = modelMapper.map(countryView, Country.class);
			country.setCreateTime(DateHelper.getDate(DateHelper.dateFormat));
			countryRepo.save(country);
		} catch (Exception e) {
			logger.error("Failed to save country due to:{}", e.toString(), e);

		}
	}

	public void update(CountryView countryView) {
		try {
			Country country = modelMapper.map(countryView, Country.class);

			country.setUpdateTime(DateHelper.getDate(DateHelper.dateFormat));

			Country ref = countryService.findOne(country.getId());
			country.setCreateTime(ref.getCreateTime());
			country.setIsDeleted(ref.getIsDeleted());
			countryRepo.save(country);
		} catch (ParseException e) {
			logger.error("Failed to update country due to:{}", e.toString(), e);

		}
	}

	public List<Country> findAll() {

		return countryRepo.findAll();
	}

	public Country findOne(Integer id) {
		return countryRepo.findOne(id);
	}

	public int markDelete(Integer id) {
		return countryRepo.markDeleted(id);
	}

	public int count() {
		return (int) countryRepo.count();
	}

}
