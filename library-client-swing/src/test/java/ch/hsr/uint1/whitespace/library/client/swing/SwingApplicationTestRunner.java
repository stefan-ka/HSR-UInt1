package ch.hsr.uint1.whitespace.library.client.swing;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.hsr.uint1.whitespace.library.client.swing.gui.SwingApplication;

public class SwingApplicationTestRunner {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-test-context.xml");
		SwingApplication app = context.getBean(SwingApplication.class);
		app.startApp();
		context.close();
	}

}
