package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Loggers;

import com.aventstack.extentreports.ExtentTest;

public class BasePage {

	protected ExtentTest test;
	protected static Logger logger = LogManager.getLogger(BasePage.class.getName());
}
