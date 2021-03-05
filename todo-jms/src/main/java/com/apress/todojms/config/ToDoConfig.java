package com.apress.todojms.config;

import javax.jms.ConnectionFactory;

import com.apress.todojms.error.ToDoErrorHandler;
import com.apress.todojms.validator.ToDoValidator;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

/**
 * Now we have to configure the App in order to send and receive ToDo's Both in
 * Consumer and Producer you're using a ToDo instance meaning that it is
 * necessary to do serialization. Most of the Java frameworks that work with
 * serialization require that your classes implement from java.io.Serializable.
 * It is an easy way to convert those classes to bytes but this approach has
 * been deleted for years because implementing Serializable decreases the
 * flexibility to modify a class' implementation once it's released for usage.
 * 
 * The spring Framework offers an alternative way to do serialization without
 * implementing Serializable through a MessageConverter interface. This
 * interface offers the toMessage and fromMessage methods
 * 
 * This app is using the in-memory broker. This is probably good for demos or
 * testing, but in reality you use a remote ActiveMQ server.
 * 
 * 
 * 
 */
@Configuration
public class ToDoConfig {

  /**
   * this will return the MessageConverter interface. This interface promotes the
   * implementation of toMessage and fromMessage method that helps plug in any
   * serialization/conversion that you want to use. In this case you are using a
   * JSON converter by using the MappingJackson2MessageConverter class
   * implementation. This class is one of the default implmentation that you can
   * find in the Spring framework, It uses the Jackson library so you need to
   * specify a target type in this case text means string so my object is taken as
   * a string. And a typeId property name which is used to found from/to the
   * producer and the consumer
   */
  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_class_");
    return converter;
  }

  /**
   * The JmsListenerContainerFactory the jmsFactory method returns
   * JmsListenerContainerFactory and it creates a DefaultJmsListenerFacory which
   * set up an Error Handler. This bean is used in the in the jmsListener
   * annotation by setting the containerFactory attribute
   */
  @Bean
  public JmsListenerContainerFactory<?> jmsFactory(ConnectionFactory connectionFactory,
      DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setErrorHandler(new ToDoErrorHandler());
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  /**
   * If you don't want to validate yet, you can remove this part
   */
  @Configuration
  static class MethodListenerConfig implements JmsListenerConfigurer {

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
      registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());

    }

    @Bean
    public MessageHandlerMethodFactory myHandlerMethodFactory() {
      DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
      factory.setValidator(new ToDoValidator());
      return factory;
    }

  }

}
