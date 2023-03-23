package com.example.demo.domain;

/**
 * カテゴリー情報を格納するドメイン.
 * 
 * @author kenji.suzuki
 *
 */
public class Category {

	/**　id */
	private Integer id;
	/**　親項目 */
	private Integer parent;
	/**　名前 */
	private String name;
	/**　すべての名前 */
	private String name_all;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_all() {
		return name_all;
	}

	public void setName_all(String name_all) {
		this.name_all = name_all;
	}

}
