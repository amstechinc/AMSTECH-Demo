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

package com.amstech.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	public static String dateFormat = "dd/mm/yyyy";
	public static String dateTimeFormat = "dd/mm/yyyy hh/mm/ss a";

	public static Date getDate(String format) throws ParseException {

		DateFormat dateformat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateformat.parse(dateformat.format(date));

	}

	public static void main(String args[]) {

		try {
			Date date = getDate(dateTimeFormat);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
