/*
 * Copyright 2008-2009 the original author or authors.
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
package egovframework.rte.cmmn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;//전자정부 4.0 미만
import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;//전자정부 4.0 이상
/**  
 * @Class Name : EgovSampleExcepHndlr.java
 * @Description : EgovSampleExcepHndlr Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * 
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */
public class EgovExcepHndlr implements ExceptionHandler {

    protected Log log = LogFactory.getLog(this.getClass());
    
    /**
    * @param ex
    * @param packageName
    * @see 개발프레임웍크 실행환경 개발팀
    */
    public void occur(Exception ex, String packageName) {
		log.error(" EgovExcepHndlr run...............");
		log.error(packageName);
		log.error(ex.toString());
		ex.printStackTrace();
		ex.getMessage();
		
		/* 시큐어 코딩 : 별도 처리가 없어서 주석처리로 해결
		try {
			log.debug(" EgovServiceExceptionHandler try ");
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		*/
    }

}
