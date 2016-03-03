package com.ttj.lmk;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class RetrieveProcessBO implements Runnable {

	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	final static Logger logger = Logger.getLogger(RetrieveProcessBO.class);
	private RegistrationDO regData;
	private ScheduledFuture<?> scheduled;
	
	public RetrieveProcessBO(){
		
	}
	public RetrieveProcessBO(RegistrationDO regData) {
		this.regData = regData;
	}

	@Override
	public void run() {
		try {

			Document doc = Jsoup.connect(regData.getRegurl()).get();
			String result = doc.select(regData.getSelection()).text();
			logger.debug("retrieved: " + System.currentTimeMillis() + " : value: " + result);

			switch(regData.getConditions()) {
			case DIFFERENT:
				if(!regData.getCurrentVal().equals(result)) {
					if(regData.getRegtype() == RegistrationDO.RegType.NOTIFY_STOP || regData.getRegtype() == RegistrationDO.RegType.NOTIFY_CONTINUE ) {
						//TODO: Notify
						logger.debug("DIFFERENT");
						EmailUtil.sendNotificationEmail(regData.getEmail(), result, regData.getCurrentVal(),regData.getName());
						switch(regData.getRegtype()) {
						case NOTIFY_CONTINUE:
							break;
						case NOTIFY_STOP:
							logger.debug("In NOTIFY_STOP");
							this.scheduled.cancel(false);
							break;
						case STORE:
							break;
						case STOR_NOTIFY:
							break;
						default:
							break;

						}
					}
				}
			case MORE:
				break;
			case LESS:
				break;
			default:
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void execute() {
		this.scheduled = scheduler.scheduleAtFixedRate(this, 1, this.regData.getInterval(), TimeUnit.MINUTES);
	}

	@PreDestroy
	public void stopExecution() {
		logger.debug("METHOD stopExecution");
		scheduler.shutdown();
	}

}
