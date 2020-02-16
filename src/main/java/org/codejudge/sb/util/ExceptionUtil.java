package org.codejudge.sb.util;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.model.GenericResponseDto;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ExceptionUtil {
	
	public static GenericResponseDto handleException(String msg, Integer statusCode, HttpServletRequest req, Exception ex) {
		log.error(msg, ex);
		return new GenericResponseDto("failure", msg);
	}
}
