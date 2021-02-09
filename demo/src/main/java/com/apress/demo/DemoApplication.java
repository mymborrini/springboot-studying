package com.apress.demo;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @SpringBootApplication annotation is equivalent to declaring 
 * @Configuration, @ComponentScan, @EnableAutoConfiguration.
 * If you want you can disable an AutoConfiguration excluding it
 * calling in @SpringbootApplication Exclude since the sprigbootannotation wrap 
 * all three you can call exclude which originally belongs to @EnableAutoConfiguration.
 * 
 * One of the most important annoration is of course @EnableAutoConfiguration 
 * The autoconfiguration classes are applied based on the classpath and which beans your app
 * has defined, but what this makes more powerful is AutoConfigurationImportSelector.class, this finds all
 * necessary configuration classes. This class has several methods but one of the most important is the
 * getCandidateConfigurations method. This method looks for the META-INF/spring.factories 
 * defined in the spring-boot-autoconfigure.jar. This file defined all the auto-configuration classes that
 * are used to set up any configuration that your application needs for running. 
 * 
 * Let's take a better look at one of this auto-configuration class: the CloudAutoConfiguration.class.
 * 
 * @Configuration
 * @Profile("cloud")
 * @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 20) Cloud configuration needs to be happen earlier
 * @ConditionalOnClass(CloudScanConfiguration.class)
 * @ConditionalOnMissingBean(Cloud.class)
 * @ConditionalOnProperty(prefix="spring.cloud", name="enabled", havingValue="true", matchIfMissing=true)
 * @Import(CloudScanConfiguration.class)
 * public class CloudAutoConfiguration
 * 
 * How does it works? It uses two annotations @ConditionalOnClass and @ConditionalOnMissingBean to decide
 * if the application is Cloud App or not. Let's see this in more detail when extending springboot.
 * Another annotation to consider for now is the @ConditionalOnProperty that only applies if the property spring.cloud
 * is enabled. The @Profile("cloud") means that this autoconfiguration is executed in a cloud profile. 
 * The Import is applied only if the other annotations met their conditions (@Conditional*).
 * The most important think of all this is that auto-configuration uses your class path to figure out what to
 * configure for your app. That what it means that spring boot is an OPINIONATED RUNTIME
 * 
 * 
 */

@SpringBootApplication(exclude={ActiveMQAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class DemoApplication {

	public static void main(String[] args) {
		/**
		 * SpringApplication this class provides the bootstrap for the 
		 * Spring application that is executed in the main method. 
		 * The run method return the application context where you can 
		 * customize all the beans you need for this application. But you 
		 * can create a lot of things. For example one of the simplest thing
		 * is to customize the Application Banner instead of Spring.
		 */

		 // SpringApplication.run(DemoApplication.class, args)
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBanner(new Banner());
		app.run(args);

	}

	/**
	 * Let's take a look at the SpringApplicationBuilder. It provides a fluent API ans is a builder
	 * for SpringApplication and the ApplicationContext instances. For example if you want to customize
	 * the banner. Or set profiles. Or attach listener for some application Events.
	 * There could be several event types:
	 * 
	 * ApplicationStartedEvent: this is sent at the start
	 * 
	 * ApplicationEnvironmentPreparedEvent: this is sent when the environment is known
	 * 
	 * ApplicationPreparedEvent: this is sent after the bean definitions 
	 * 
	 * ApplicationReadyEvent: this is sent when the application is ready
	 * 
	 * ApplicationFailedEvent: this is sent in case of exception during the startup
	 * 
	 * You can also configure if the app is a web app or not by setting the WebApplicationType.
	 * WebApplicationType can be NONE, SERVLET, REACTIVE.
	 */
	public static void mainBuilder(String[] args) {
		new SpringApplicationBuilder()
		.bannerMode(Banner.Mode.OFF)
		.sources(SpringBootSimpleApplication.class)
		.run(args);

		new SpringApplicationBuilder(SpringBootSimpleApplication.class)
		.profiles("prod", "cloud")
		.run(args);


		Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);

		new SpringApplicationBuilder(SpringBootSimpleApplication.class)
		.listeners(new ApplicationListener<ApplicationEvent>() {
			
			@Override
			public void onApplicationEvent(ApplicationEvent event){
				log.info("### > " + event.getClass().getCanonicalName());
			}

		} )
		.run(args);

		new SpringApplicationBuilder(SpringBootSimpleApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);

	}

}
