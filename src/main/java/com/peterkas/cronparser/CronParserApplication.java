package com.peterkas.cronparser;

import com.peterkas.cronparser.cron.CronExpression;
import com.peterkas.cronparser.printer.CronExpressionPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CronParserApplication implements CommandLineRunner {

	@Autowired
	CronExpressionPrinter printer;

	public static void main(String[] args) {
		SpringApplication.run(CronParserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length != 1) {
			throw new IllegalArgumentException("Cron expression must be passed as a single argument");
		}

		CronExpression cronExpression = CronExpression.parse(args[0]);

		printer.print(cronExpression);
	}
}
