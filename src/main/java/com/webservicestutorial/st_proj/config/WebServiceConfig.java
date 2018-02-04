package com.webservicestutorial.st_proj.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 03.02.2018.
 */
@EnableWs
@Configuration
public class WebServiceConfig {

    private static final String NAMESPACE_URI = "http://someBookDetails.com/courses";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet (ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(CommonsXsdSchemaCollection xsdSchemaCollection) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CoursePort");
        definition.setTargetNamespace(NAMESPACE_URI);
        definition.setLocationUri("/ws");
        definition.setSchemaCollection(xsdSchemaCollection);
        return definition;
    }


    @Bean
    public CommonsXsdSchemaCollection coursesSchema(){
        CommonsXsdSchemaCollection xsds = new CommonsXsdSchemaCollection(new ClassPathResource("request.xsd"),
                new ClassPathResource("response.xsd"));
        xsds.setInline(true);
        return xsds;
    }

}
