package fr.formation.gamebook.model;

public class Choice {

	private Integer id;
	
	private String content;
	
	private Integer paragraphId;

	public Choice(int id, String content, int paragraphId) {
		this.id = id;
		this.content = content;
		this.paragraphId = paragraphId;
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

	public Integer getParagraphId() {
		return paragraphId;
	}

	public void setParagraphId(Integer paragraphId) {
		this.paragraphId = paragraphId;
	}
	
}
