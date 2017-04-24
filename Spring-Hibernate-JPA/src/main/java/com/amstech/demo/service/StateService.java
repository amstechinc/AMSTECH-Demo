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

import com.amstech.demo.entity.State;
import com.amstech.demo.repo.StateRepo;
import com.amstech.demo.util.DateHelper;
import com.amstech.demo.view.StateView;

@Service

public class StateService {
	private static final Logger logger = LoggerFactory.getLogger(StateService.class);

	@Autowired(required = true)
	private StateRepo stateRepo;

	@Autowired(required = true)
	private StateService stateService;

	@Autowired
	@Qualifier("modelMapperWithConverter")
	private ModelMapper modelMapper;

	public void save(StateView stateView) {
		try {
			State state = modelMapper.map(stateView, State.class);
			state.setCreateTime(DateHelper.getDate(DateHelper.dateFormat));
			stateRepo.save(state);
		} catch (Exception e) {
			logger.error("Failed to save state due to:{}", e.toString(), e);
		}
	}

	public void update(StateView stateView) {
		try {
			State state = modelMapper.map(stateView, State.class);

			state.setUpdateTime(DateHelper.getDate(DateHelper.dateFormat));

			State ref = stateService.findOne(state.getId());
			state.setCreateTime(ref.getCreateTime());
			state.setIsDeleted(ref.getIsDeleted());
			stateRepo.save(state);
		} catch (ParseException e) {
			logger.error("Failed to save state due to:{}", e.toString(), e);
		}
	}

	public List<State> findAll() {

		return stateRepo.findAll();
	}

	public State findOne(Integer id) {
		return stateRepo.findOne(id);
	}

	public int markDelete(Integer id) {
		return stateRepo.markDeleted(id);
	}

	public int count() {
		return (int) stateRepo.count();
	}

}
