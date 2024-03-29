package com.example.demo.domain;

/**
 * アイテム情報を格納するドメイン.
 * 
 * @author kenji.suzuki
 *
 */
public class Item {

	/** id */
	private Integer id;
	/** 名前 */
	private String name;
	/** 値段 */
	private Integer condition;
	/** カテゴリ */
	private String category;
	/** ブランド */
	private String brand;
	/** 値段 */
	private Integer price;
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

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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

}
