package ch.hsr.uint1.whitespace.library.client.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.hsr.uint1.whitespace.library.client.swing.gui.SwingApplication;

public class SwingApplicationRunner {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		SwingApplicationRunner.run("META-INF/spring/spring-context.xml");
	}

	public static void run(String contextConfiguration) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextConfiguration);
		SwingApplication app = context.getBean(SwingApplication.class);
		app.startApp();
		context.registerShutdownHook();
		app.addCloseApplicationListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				context.close();
				System.exit(0);
			}
		});
	}

}
