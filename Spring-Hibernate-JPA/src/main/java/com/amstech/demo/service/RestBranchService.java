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

import com.amstech.demo.entity.RestBranch;
import com.amstech.demo.repo.RestBranchRepo;
import com.amstech.demo.util.DateHelper;
import com.amstech.demo.view.RestBranchView;

@Service
public class RestBranchService {

	private static final Logger logger = LoggerFactory.getLogger(RestBranchService.class);

	@Autowired(required = true)
	private RestBranchRepo restBranchRepo;

	@Autowired(required = true)
	private RestBranchService restBranchService;

	@Autowired
	@Qualifier("modelMapperWithConverter")
	private ModelMapper modelMapper;

	public void save(RestBranchView restBranchView) {
		try {
			RestBranch restBranch = modelMapper.map(restBranchView, RestBranch.class);
			restBranch.setCreateTime(DateHelper.getDate(DateHelper.dateFormat));
			restBranch.setIsDeleted(0);
			restBranchRepo.save(restBranch);
		} catch (Exception e) {
			logger.error("Failed to save restaurant branch due to:{}", e.toString(), e);
		}
	}

	public void update(RestBranchView restBranchView) {
		try {
			RestBranch restBranch = modelMapper.map(restBranchView, RestBranch.class);

			restBranch.setUpdateTime(DateHelper.getDate(DateHelper.dateFormat));

			RestBranch ref = restBranchService.findOne(restBranch.getId());
			restBranch.setCreateTime(ref.getCreateTime());
			restBranch.setIsDeleted(ref.getIsDeleted());
			restBranchRepo.save(restBranch);
		} catch (ParseException e) {
			logger.error("Failed to update restaurant branch due to:{}", e.toString(), e);
		}
	}

	public List<RestBranch> findAll() {

		return restBranchRepo.findAll();
	}

	public List<RestBranch> findAllByRId(Integer rId) {
		return restBranchRepo.findAllByRId(rId);
	}

	public int markDelete(Integer id) {
		return restBranchRepo.markDeleted(id);
	}

	public RestBranch findOne(Integer id) {
		return restBranchRepo.findOne(id);
	}

	public int count() {
		return (int) restBranchRepo.count();
	}

}
