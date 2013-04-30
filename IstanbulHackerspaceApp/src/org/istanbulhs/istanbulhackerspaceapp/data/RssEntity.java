package org.istanbulhs.istanbulhackerspaceapp.data;

public class RssEntity {
	private String title;
	private String description;
	private String link;
	
	
	public RssEntity(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
