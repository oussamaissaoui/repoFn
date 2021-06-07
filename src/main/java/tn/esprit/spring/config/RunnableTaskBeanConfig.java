package tn.esprit.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import tn.esprit.spring.EventListner.RunnableTask;

@Configuration
@EnableScheduling
public class RunnableTaskBeanConfig {
	
	@Bean
	public RunnableTask task() {
		return new RunnableTask();
	}

}
