package com.example.demo.domain;

/**
 * 商品追加情報を格納するドメイン.
 * 
 * @author kenji.suzuki
 */
public class AddItem {
	 /** id */
	private Integer id;
	/** 名前 */
	private String name;
	/** 値段 */
	private double price;
	/** カテゴリ */
	private Integer category;
	/** ブランド */
	private String brand;
	/** 状態 */
	private Integer condition;
	/** 出荷 */
	private Integer shipping;
	/** 説明 */
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AddItem [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", brand="
				+ brand + ", condition=" + condition + ", shipping=" + shipping + ", description=" + description + "]";
	}

}
