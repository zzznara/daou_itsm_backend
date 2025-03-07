/*
 * Copyright 2008-2009 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.cmm.filter;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpRequestWithModfiableParameters extends HttpServletRequestWrapper {

	HashMap params;
	public HttpRequestWithModfiableParameters(HttpServletRequest request) {
		super(request);
		this.params = new HashMap(request.getParameterMap());
	}
	
	public void setParameter(String name, String value) {
		//String[] oneParam = { value };
		
		//SQL 인젝션 보완 - 문자열 필터링
		String[] oneParam = { replaceQuery(value) };
		setParameter(name, oneParam);
	}
	
	public void setParameter(String name, String[] values) {
		params.put(name, values);
	}
	
	public String replaceQuery(String value) {
		/* SQL 인젝션 보완 - 문자열 필터링 */
		String returnValue = value.toLowerCase();

		returnValue = returnValue.replaceAll("select", "");
		returnValue = returnValue.replaceAll("update", "");
		returnValue = returnValue.replaceAll("delete", "");
		returnValue = returnValue.replaceAll("insert", "");
		returnValue = returnValue.replaceAll("where", "");
		returnValue = returnValue.replaceAll("from", "");
		
		return returnValue;
	}
}