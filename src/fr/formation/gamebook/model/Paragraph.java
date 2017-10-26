package fr.formation.gamebook.model;

import java.util.ArrayList;
import java.util.List;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
	
}
