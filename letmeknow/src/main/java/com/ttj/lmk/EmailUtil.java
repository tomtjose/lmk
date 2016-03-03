package com.ttj.lmk;

import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

import org.apache.log4j.Logger;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;

public class EmailUtil {
	final static Logger logger = Logger.getLogger(EmailUtil.class);

	public static void sendNotificationEmail(String To,String newVal, String orgVal, String name) {
		Destination destination = new Destination().withToAddresses(To);
		Content subject = new Content().withData(ConfigItems.EMAIL_SUBJECT);
		Content textBody = new Content().withData("Value changed from " + orgVal + "To " + newVal + "for " + name);
		Body body = new Body().withText(textBody);
		Message message = new Message().withSubject(subject).withBody(body);
		SendEmailRequest request = new SendEmailRequest().withSource(ConfigItems.FROM_EMAIL).withDestination(destination).withMessage(message);
		try {
			logger.debug("Attempting to send email to " + To);
			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient();
			Region REGION = Region.getRegion(Regions.US_EAST_1);
			client.setRegion(REGION);
			client.sendEmail(request);  
		} catch (Exception ex) 
        {
            logger.error("The email was not sent.");
            logger.error("Error message: " + ex.getMessage());
        }
	}
}
