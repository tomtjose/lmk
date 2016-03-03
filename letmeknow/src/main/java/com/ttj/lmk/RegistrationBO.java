package com.ttj.lmk;


public class RegistrationBO {
	
	
	public boolean registerIntereset(RegistrationDO regData) {
		//TODO:store the info in registration table
		
		//Schedule periodic execution
		RetrieveProcessBO task = new RetrieveProcessBO(regData);
		task.execute();
		return true;
	}

}


