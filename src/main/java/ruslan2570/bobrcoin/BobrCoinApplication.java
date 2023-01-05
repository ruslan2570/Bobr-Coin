package ruslan2570.bobrcoin;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@SpringBootApplication
public class BobrCoinApplication {


	public static void main(String[] args) {
		SpringApplication.run(BobrCoinApplication.class, args);
	}

}
