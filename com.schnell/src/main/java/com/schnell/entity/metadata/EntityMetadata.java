package com.schnell.entity.metadata;

import java.util.List;

public class EntityMetadata {
	public String entityName;
	
	public List<EntityAttribute> entityAttributes;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public List<EntityAttribute> getEntityAttributes() {
		return entityAttributes;
	}
	public void setEntityAttributes(List<EntityAttribute> entityAttributes) {
		this.entityAttributes = entityAttributes;
	}
}
