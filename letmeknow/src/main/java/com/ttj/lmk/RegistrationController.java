package com.ttj.lmk;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
	
	final static Logger logger = Logger.getLogger(RegistrationController.class);
	
	@RequestMapping(value = "/register/v1", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public Object registerInterest(@RequestBody RegistrationDO regData) {
		logger.debug("ENTERING METHOD=registerInterest");
		//do sanity check
		//register
		RegistrationBO registration = new RegistrationBO();
		return registration.registerIntereset(regData);
	}
}
