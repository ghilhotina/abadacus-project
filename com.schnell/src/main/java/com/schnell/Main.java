package com.schnell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.schnell.entity.metadata.EntityAttribute;
import com.schnell.entity.metadata.EntityMetadata;
import com.schnell.generator.ArtifactFactory;

public class Main {

	public static void main(String[] args) {
		Properties config = new Properties();
       	try {
			config.load(Main.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
       	
       	ArtifactFactory artifactFactory = new ArtifactFactory(config);
       	
       	EntityMetadata entityMetadata = createEntitiesMetadata();
       	
       	artifactFactory.generate(entityMetadata);
	}

	//@TODO criar classe para extrair metadados
	private static EntityMetadata createEntitiesMetadata() {
		EntityMetadata entityMetadata = new EntityMetadata();
       	entityMetadata.setEntityName("Person");
       	List<EntityAttribute> entityAtributtes = new ArrayList<EntityAttribute>();
       	
       	EntityAttribute entityAtributteName = new EntityAttribute();
       	EntityAttribute entityAtributteAge = new EntityAttribute();
       	entityAtributteName.setAttributeName("name");
       	entityAtributteName.setAttributeType("String");
       	
       	entityAtributteAge.setAttributeName("age");
       	entityAtributteAge.setAttributeType("Integer");
       	
       	entityAtributtes.add(entityAtributteName);
       	entityAtributtes.add(entityAtributteAge);
       	entityMetadata.setEntityAttributes(entityAtributtes);
		return entityMetadata;
	}

}
