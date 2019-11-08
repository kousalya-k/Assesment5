package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Product {
	
	@Id
	private String id;
	private String name;
	private String desc;
	private String price;
	private String quality;
	static  ArrayList<String> names=new ArrayList<>();
	public Product() {
	}

	
	public Product(String id, String name, String desc, String price, String quality) {
		this.id = id;
		try {
			if(names.contains(name)) {throw new Exception();}
			else {this.name=name;}
			}
			catch(Exception e) {
				System.err.println("Product with this name already exists");
				return;
			}

		this.desc = desc;
		this.price = price;
		this.quality = quality;
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
		try {
			if(names.contains(name)) {throw new Exception();}
			else {this.name=name;}
			}
			catch(Exception e) {
				System.err.println("Product with this name already exists");
				return;
			}

	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getQuality() {
		return quality;
	}


	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	
	
	
	
	

}
