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

import com.amstech.demo.entity.RestBranch;
import com.amstech.demo.service.RestBranchService;
import com.amstech.demo.util.RestResponse;
import com.amstech.demo.view.RestBranchView;

@RestController
@RequestMapping("/restBranch")
public class RestBranchController {
	private static final Logger logger = LoggerFactory.getLogger(RestBranchController.class);

	@Autowired(required = true)
	private RestBranchService restBranchService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public RestResponse save(@RequestBody RestBranchView restBranchView) {
		try {
			logger.info("save restBranch:{}", restBranchView);
			restBranchService.save(restBranchView);
		} catch (Exception e) {
			return RestResponse.build().withError("Failed to add restBranch due to :" + e);
		}
		return RestResponse.build().withSuccess("Data added successfully.");

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResponse find(@PathVariable("id") Integer id) {
		try {

			logger.info("find restBranch for Id:{}", id);
			RestBranch restBranch = restBranchService.findOne(id);
			if (restBranch != null) {
				return RestResponse.build().withSuccess().withData(restBranch);
			} else {
				return RestResponse.build().withError("No data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find restBranch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find Data due to :" + e);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public RestResponse findAll() {

		try {
			List<RestBranch> restBranch = restBranchService.findAll();
			if (restBranch != null) {
				return RestResponse.build().withSuccess().withData(restBranch);
			} else {
				return RestResponse.build().withError("No data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find restBranch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find restBranch due to :" + e);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public RestResponse update(@RequestBody RestBranchView restBranchView) {
		try {
			logger.info("update restBranch:{}", restBranchView);

			restBranchService.update(restBranchView);
		} catch (Exception e) {
			logger.error("Failed to update restBranch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to update restBranch due to :" + e);
		}
		return RestResponse.build().withSuccess("Data updated successfully.");

	}

	@RequestMapping(value = "/restBranch/{rid}", method = RequestMethod.GET)
	public RestResponse findAllByRId(@RequestParam("rId") Integer rId) {

		try {
			List<RestBranch> restBranch = restBranchService.findAllByRId(rId);
			if (restBranch != null) {
				return RestResponse.build().withSuccess().withData(restBranch);
			} else {
				return RestResponse.build().withError("No data found");
			}

		} catch (Exception e) {
			logger.error("Failed to find RestBranch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find RestBranch due to :" + e);
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public RestResponse count() {
		try {
			Map<String, Integer> map = new HashMap<>(1);
			map.put(RestResponse.KEY_ROW_COUNT, restBranchService.count());
			return RestResponse.build().withSuccess().withData(map);

		} catch (Exception e) {
			logger.error("Failed to find restBranch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to find restBranch due to :" + e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestResponse delete(@PathVariable("id") Integer id) {
		try {
			logger.info("delete restBranch for Id:{}", id);
			int rowCount = restBranchService.markDelete(id);
			if (rowCount > 0) {
				return RestResponse.build().withSuccess("Data deleted successfully.");
			} else {
				return RestResponse.build().withError("No record deleted.");
			}
		} catch (Exception e) {
			logger.error("Failed to delete restBranch due to:{}", e.toString(), e);
			return RestResponse.build().withError("Failed to delete restBranch due to :" + e);
		}
	}

}
