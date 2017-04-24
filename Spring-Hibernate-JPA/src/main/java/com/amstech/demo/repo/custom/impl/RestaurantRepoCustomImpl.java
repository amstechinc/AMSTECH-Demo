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

package com.amstech.demo.repo.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amstech.demo.entity.Restaurant;
import com.amstech.demo.repo.custom.RestaurantRepoCustom;

public class RestaurantRepoCustomImpl implements RestaurantRepoCustom {
	private static final Logger logger = LoggerFactory.getLogger(RestaurantRepoCustomImpl.class);

	private EntityManager entityManager = null;

	@PersistenceContext
	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Restaurant> findByFilter(int page, int size, Integer cityId, String search) {
		String tempQuery = createQuery("Restaurant", cityId, search);
		TypedQuery<Restaurant> query = entityManager.createQuery(tempQuery.toString(), Restaurant.class);
		logger.info("Custom Final Query{}", tempQuery.toString());
		setParameter(query, page, size, cityId, search);
		return query.getResultList();
	}

	@Override
	public long countByFilter(Integer cityId, String search) {
		String tempQuery = createQuery("count(*)", cityId, search);
		TypedQuery<Long> query = entityManager.createQuery(tempQuery.toString(), Long.class);
		logger.info("Custom Final Query{}", tempQuery.toString());
		setParameter(query, -1, -1, cityId, search);
		return query.getSingleResult();
	}

	private String createQuery(String select, Integer cityId, String search) {
		StringBuilder tempQuery = new StringBuilder(
				"select " + select + " from Restaurant restaurant where restaurant.isDeleted = 0 ");

		// cityId
		if (cityId != -1) {
			tempQuery.append(" and ");
			tempQuery.append(" restaurant.City.id = :cityId");

		}
		// search
		if (search.equals("-1")) {
			tempQuery.append(" and ");
			tempQuery.append(" UPPER(restaurant.name) like = UPPER(:search)");

		}

		return tempQuery.toString();
	}

	public void setParameter(Query query, int page, int size, Integer cityId, String search) {

		// cId
		if (cityId != -1) {
			query.setParameter("cityId", cityId);
		}
		// search
		if (search.equals("-1")) {
			query.setParameter("maxPrice", "%" + search + "search");
		}

	}

}
