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

package com.amstech.demo.config;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixInModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	@Override
	public void setupModule(SetupContext context) {

		// context.setMixInAnnotations(User.class, UserIgnore.class);

	}

}
