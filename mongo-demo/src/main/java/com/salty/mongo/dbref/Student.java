package com.salty.mongo.dbref;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="student")
public class Student {
	@Id
	private String id;
	private String name;
	@DBRef
	private ClassInfo classInfo;
	
	public Student(String name, ClassInfo classInfo) {
		super();
		this.name = name;
		this.classInfo = classInfo;
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
	public ClassInfo getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", classInfo=" + classInfo +
				'}';
	}
}
