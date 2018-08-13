package com.salty.mongo.dbref;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="class_info")
public class ClassInfo {
	@Id
	private String id;
	private String name;
	
	public ClassInfo(String name) {
		super();
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
