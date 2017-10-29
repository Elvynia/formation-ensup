package fr.formation.gamebook.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Paragraph {

	private Integer id;

	private String content;

	private List<Choice> choices;

	public Paragraph() {
		this.choices = new ArrayList<>();
	}

	public Paragraph(Integer id) {
		this();
		this.id = id;
	}

	@XmlElement
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement(name = "description")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@XmlElementWrapper(name = "actions")
	@XmlElement(name = "choice")
	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

}
