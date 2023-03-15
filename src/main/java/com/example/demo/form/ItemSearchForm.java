package com.example.demo.form;

public class ItemSearchForm {

	/** 商品名 */
	private String name;
	/** 大カテゴリー */
	private String bigCategory;
	/** 中カテゴリー */
	private String childCategory;
	/** 小カテゴリー */
	private String grandCategory;
	/** ブランド名 */
	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBigCategory() {
		return bigCategory;
	}

	public void setBigCategory(String bigCategory) {
		this.bigCategory = bigCategory;
	}

	public String getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}

	public String getGrandCategory() {
		return grandCategory;
	}

	public void setGrandCategory(String grandCategory) {
		this.grandCategory = grandCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "ItemSearchForm [name=" + name + ", bigCategory=" + bigCategory + ", childCategory=" + childCategory
				+ ", grandCategory=" + grandCategory + ", brand=" + brand + "]";
	}

}
