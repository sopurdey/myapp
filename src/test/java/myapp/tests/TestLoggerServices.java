package myapp.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myapp.imp.BeanFileLogger;
import myapp.imp.FileLogger;
import myapp.imp.SimpleCalculator;
import myapp.imp.StderrLogger;
import myapp.services.ICalculator;
import myapp.services.ILogger;

public class TestLoggerServices {

	@Before
	public void beforeEachTest() {
		System.err.println("======================");
	}

	@After
	public void afterEachTest() {
	}

	// utiiser un logger
	void use(ILogger logger) {
		logger.log("Voilà le résultat = hello");
	}

	@Test
	public void testStderrLogger() {
		// créer le service
		StderrLogger logger = new StderrLogger();
		// lancer le service
		logger.start();
		// utiliser le service
		use(logger);
		// arrêter le service
		logger.stop();
	}

	@Test
	public void testFileLogger() {
		// créer le service
		FileLogger logger = new FileLogger("tmp/myapp.log");
		// lancer le service
		logger.start();
		// utiliser le service
		use(logger);
		// arrêter le service
		logger.stop();
	}

	@Test
	public void testBeanFileLogger() {
		// créer le service
		BeanFileLogger logger = new BeanFileLogger();
		// set le paramètre
		logger.setFileName("tmp/myapp.log");
		// lancer le service
		logger.start();
		// utiliser le service
		use(logger);
		// arrêter le service
		logger.stop();
	}

	void use(ICalculator calc) {
		calc.add(100, 200);
	}

	@Test
	public void testCalculatorAndStderrLogger() {
		// créer et lancer le logger service (stderr)
		StderrLogger logger = new StderrLogger();
		logger.start();
		// créer; injecter et lancer le calculator service
		SimpleCalculator calculator = new SimpleCalculator();
		calculator.setLogger(logger);
		calculator.start();
		// utiliser lle calculator service
		use(calculator);
		// arrêter le calculator service
		calculator.stop();
		// arrêter le logger service
		logger.stop();
	}

}
