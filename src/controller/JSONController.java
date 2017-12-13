package controller;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import domain.Magazine;
import domain.Article;

public class JSONController {

    public static List<Magazine> readJSON(String JSONPath) {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        
        try {
        	jsonArray = (JSONArray) parser.parse(new FileReader(JSONPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return JSONToMagazines(jsonArray);
    }
    
    public static List<Magazine> JSONToMagazines(JSONArray jsonArray) {
    	List<Magazine> magazines = new ArrayList<>();
        Iterator<JSONObject> iterator = jsonArray.iterator();
        
        while (iterator.hasNext()) {
        	JSONObject jsonObject = iterator.next();
        	
            long id = (long) jsonObject.get("id");
            String name = (String) jsonObject.get("name");
            int number = ((Long) jsonObject.get("number")).intValue();

            JSONArray articles = (JSONArray) jsonObject.get("articles");

            magazines.add(new Magazine(id, name, number, JSONToArticles(articles)));
        }
        
        return magazines;
    }
    
    public static List<Article> JSONToArticles(JSONArray jsonArray) {
    	List<Article> articles = new ArrayList<>();
        Iterator<JSONObject> iterator = jsonArray.iterator();
        
        while (iterator.hasNext()) {
        	JSONObject jsonObject = iterator.next();
        	
            long id = (long) jsonObject.get("id");
            String name = (String) jsonObject.get("name");
            String author = (String) jsonObject.get("author");
            int length = ((Long) jsonObject.get("length")).intValue();

            DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
            Date releaseDate = null;
			try {
				releaseDate = df.parse((String) jsonObject.get("releaseDate"));
			} catch (ParseException e) {
				e.printStackTrace();
			} 
            
            articles.add(new Article(id, name, author, releaseDate, length));
        }
    	
    	return articles;
    }
}
