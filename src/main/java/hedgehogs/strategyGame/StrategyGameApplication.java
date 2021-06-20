package hedgehogs.strategyGame;

import hedgehogs.strategyGame.gameLogic.placeholderLocation.DummyStart;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StrategyGameApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StrategyGameApplication.class, args);
		System.setProperty("java.awt.headless", "false"); //Disables headless

		//context.getBean(DummyStart.class).dummyStart();
		context.getBean(MainWindowFactory.class).makeMainWindow();
	}

}
