package com.schnell.generator;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.schnell.entity.metadata.EntityMetadata;

public class ArtifactFactory {
	
	private static final Logger log = LoggerFactory.getLogger(ArtifactFactory.class); 
	
	private Properties config;
	private VelocityEngine velocityEngine;

	public ArtifactFactory(Properties config) {
		velocityEngine = new VelocityEngine();
		velocityEngine.init(config);
		this.config = config;
	}
	
	public void generate(EntityMetadata entityMetadata) {
		try {
			generateController(entityMetadata);
			generateJavaPojos(entityMetadata);
		} catch (ArtifactFactoryException e) {
			log.error(e.getMessage());
		}
	}
	
	protected void generateController(EntityMetadata entityMetadata) throws ArtifactFactoryException {
		Template template;
		
		log.info("Start creating controller java file: {}", entityMetadata.getEntityName());
		
		String templatePath = config.getProperty("controller.template.path");
		if (StringUtils.isNotBlank(templatePath)) {
			log.trace("templatePath -> {}", templatePath);
			template = velocityEngine.getTemplate(templatePath);
		} else {
			throw new ArtifactFactoryException("Controller template path not found");
		}
		
		VelocityContext context = new VelocityContext();
        context.put("entityName", entityMetadata.getEntityName());
        
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        String javaFileName;
        try {
        	javaFileName = getJavaFileName(entityMetadata.getEntityName(), "controller");
        	log.trace("javaFileName -> {}", javaFileName);
        	File javaFile = new File(javaFileName);
			FileUtils.writeStringToFile(javaFile, writer.toString());
		} catch (IOException e) {
			throw new ArtifactFactoryException(e.getMessage());
		}
        
        log.info("Controller java file created: {}", javaFileName);
	}
	
	protected void generateJavaPojos(EntityMetadata entityMetadata) throws ArtifactFactoryException {
		Template template;
		
		log.info("Start creating java pojos: {}", entityMetadata.getEntityName());
		
		String templatePath = config.getProperty("pojo.template.path");
		if (StringUtils.isNotBlank(templatePath)) {
			log.trace("templatePath -> {}", templatePath);
			template = velocityEngine.getTemplate(templatePath);
		} else {
			throw new ArtifactFactoryException("Pojo template path not found");
		}
		
		VelocityContext context = new VelocityContext();
        context.put("entityMetaData", entityMetadata);
        
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        String javaFileName;
        try {
        	javaFileName = getJavaFileName(entityMetadata.getEntityName(), "pojo");
        	log.trace("javaFileName -> {}", javaFileName);
        	File javaFile = new File(javaFileName);
			FileUtils.writeStringToFile(javaFile, writer.toString());
		} catch (IOException e) {
			throw new ArtifactFactoryException(e.getMessage());
		}
        
        log.info("Java pojos created: {}", javaFileName);
	}
	
	protected String getJavaFileName(String entityName, String type) {
		return config.getProperty(type + ".file.path") + 
				entityName +
				config.getProperty("java.file.extension");
	}
	
}
