package domain;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable, Comparable<Article> {

	private long id;
	private String name;
	private String author;
	private Date releaseDate;
	private int length;
	
	public Article(String name, String author, int length) {
		this.name = name;
		this.author = author;
		this.length = length;
		this.releaseDate = new Date();
	}
	
	public Article(long id, String name, String author, Date releaseDate, int length) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.length = length;
		this.releaseDate = releaseDate;
	}
	
	public long getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public int compareTo(Article article){  
		return releaseDate.compareTo(article.releaseDate); 
	}
	
	public String toString() {
		return "Article \"" + name + "\":\n ID: " + id + "; Name: " + name + "; Author: " + author + "; Length: " + length + "; Release Date: " + releaseDate.toString() + "\n";
	}
}
