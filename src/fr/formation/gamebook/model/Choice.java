package fr.formation.gamebook.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Choice {

	private Integer id;
	
	private String content;
	
	private Integer paragraphId;
	
	public Choice() {
		
	}

	public Choice(int id, String content, int paragraphId) {
		this.id = id;
		this.content = content;
		this.paragraphId = paragraphId;
	}

	@XmlAttribute
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlValue
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@XmlAttribute(name="gotostep")
	public Integer getParagraphId() {
		return paragraphId;
	}

	public void setParagraphId(Integer paragraphId) {
		this.paragraphId = paragraphId;
	}
	
}
