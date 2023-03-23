package com.example.demo.form;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;

/**
 * 商品編集時に受け取るフォーム.
 * 
 * @author kenji.suzuki
 *
 */
public class EditForm {

	@NotBlank(message = "名前の入力は必須です")
	private String name;

	@Range(min = 1, max = 100, message = "priceの入力は必須です")
	private String price;

	@NotBlank(message = "bigCategoryを選択してください")
	private String bigcategory;

	@NotBlank(message = "childCategoryを選択してください")
	private String childcategory;

	@NotBlank(message = "grandCategoryを選択してください")
	private String grandcategory;

	@NotBlank(message = "brandの入力は必須です")
	private String brand;

	private Integer condition;

	private Integer shipping;

	@NotBlank(message = "descriptionの入力は必須です")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBigcategory() {
		return bigcategory;
	}

	public void setBigcategory(String bigcategory) {
		this.bigcategory = bigcategory;
	}

	public String getChildcategory() {
		return childcategory;
	}

	public void setChildcategory(String childcategory) {
		this.childcategory = childcategory;
	}

	public String getGrandcategory() {
		return grandcategory;
	}

	public void setGrandcategory(String grandcategory) {
		this.grandcategory = grandcategory;
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
		return "EditForm [name=" + name + ", price=" + price + ", bigcategory=" + bigcategory + ", childcategory="
				+ childcategory + ", grandcategory=" + grandcategory + ", brand=" + brand + ", condition=" + condition
				+ ", shipping=" + shipping + ", description=" + description + "]";
	}

}
