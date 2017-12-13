package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Magazine implements Serializable, Comparable<Magazine> {
	
	private long id;
	private String name;
	private int number;
	private List<Article> articles;

	public Magazine(String name, int number) {
		this.name = name;
		this.number = number;
		this.articles = new ArrayList<>();
	}
	
	public Magazine(String name, int number, List<Article> articles) {
		this.name = name;
		this.number = number;
		this.articles = articles;
	}
	
	public Magazine(long id, String name, int number, List<Article> articles) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.articles = articles;
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
	
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public List<Article> getArtciles() {
		return this.articles;
	}
	
	public void addArticle(Article article) {
		this.articles.add(article);
	}
	
	public void addArticles(List<Article> articles) {
		for(Article article : articles) {
			this.articles.add(article);
		}
	}
	
	public void removeArticle(int index) {
		this.articles.remove(index);
	}
	
	public int compareTo(Magazine magazine){  
		return name.compareTo(magazine.name); 
	}
	
	public String toString() {
		String articlesString = "";
		for(Article article : articles) {
			articlesString += article;
		}
		return " --- Magazine \"" + name + "\"--- \n ID: " + id + "; Name: " + name + "; Number: " + number + 
				"\n --- Articles for \"" + name + "\" magazine --- \n" + articlesString;
	}
}
