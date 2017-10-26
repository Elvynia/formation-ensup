package fr.formation.gamebook.model;

import java.util.ArrayList;
import java.util.List;

public class GameData {

	private String id;
	
	private List<Paragraph> paragraphs;
	
	public GameData() {
		this.paragraphs = new ArrayList<>();
	}
	
	public GameData(String id) {
		this();
		this.id = id;
	}
	
	public Paragraph getParagraph(Integer paragraphId) {
		Paragraph result = null;
		for (final Paragraph p : this.getParagraphs()) {
			if (p.getId().equals(paragraphId)) {
				result = p;
				break;
			}
		}
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
	
}
