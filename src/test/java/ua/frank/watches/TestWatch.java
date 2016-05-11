package ua.frank.watches;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.frank.watch.Watch;

public class TestWatch {

	private static Watch watch;

	@BeforeClass
	public static void setUp() throws Exception {
		watch = new Watch();
		watch.setTimeFormat("hh:mm:ss");
		watch.setTime("12:31:15");
		try {
			watch.convertTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		watch.initializeAngle();
	}

	@Test
	public void testReadPropertyFile() throws IOException {
		String format = watch.readPropertyFile("config.properties", "time");
		assertEquals("hh:mm:ss", format);
	}
	
	@Test
	public void testConvertTimeHour() {
		assertEquals(00, watch.getHour(), 0.001);
	}
	
	@Test
	public void testConvertTimeMinute() {
		assertEquals(31, watch.getMinute(), 0.001);
	}
	
	@Test
	public void testConvertTimeSecond() {
		assertEquals(15, watch.getSecond(), 0.001);
	}
	
	@Test
	public void testInitializeAngleSecond() {
		assertEquals(90, watch.getSecondAngle(), 0.01);
	}
	
	@Test
	public void testInitializeAngleMinute() {
		assertEquals(187.5, watch.getMinuteAngle(), 0.01);
	}
	
	@Test
	public void testInitializeAngleHour() {
		assertEquals(15.5, watch.getHourAngle(), 0.01);
	}
	
	@Test
	public void testGetAngleBetweenHourAndSecond() {
		assertEquals(74.5, watch.getAngleBetweenHourAndSecond(watch.getSecondAngle(), watch.getHourAngle()), 0.0001);
	}
	
	@Test
	public void testGetAngleBetweenMinuteAndHour() {
		assertEquals(172.0, watch.getAngleBetweenMinuteAndHour(watch.getMinuteAngle(), watch.getHourAngle()), 0.0001);
	}
	
	@Test
	public void testGetAngleBetweenMinuteAndSecond() {
		assertEquals(97.5, watch.getAngleBetweenMinuteAndSecond(watch.getMinuteAngle(), watch.getSecondAngle()), 0.0001);
	}
}