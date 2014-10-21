package ch.hsr.uint1.whitespace.library.client.swing;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SwingApplicationTestRunner {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		SwingApplicationRunner.run("META-INF/spring/spring-test-context.xml");
	}

}
