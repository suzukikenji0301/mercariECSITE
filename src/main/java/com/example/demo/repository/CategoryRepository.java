package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;

/**
 * カテゴリー情報を操作するリポシトリ.
 * 
 * @author kenji.suzuki
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

//	private static final RowMapper<Category> CATEGORY_ROW_MAPPER =(rs, i) -> {
//		Category category = new Category();
//		category.setId(rs.Int("id"));
//		category.setParent(rs.Int("parent"));
//		category.setName(rs.String("name"));
//		category.setName_All(rs.String("name_all"));
//		return category;
//	};

	/**
	 * 大カテゴリー検索を行います.
	 * 
	 * @return categoryデータ情報
	 */
	public List<Category> findByBigCategory() {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent IS NULL AND name_all IS NULL ORDER BY id ASC;";
		List<Category> bigcategoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return bigcategoryList;
	}

	/**
	 * 中カテゴリー検索を行います.
	 * 
	 * @return categoryデータ情報
	 */
	public List<Category> findByChildCategory(Integer id) {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent = :id AND name_all IS NULL ORDER BY id ASC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Category> childcategoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return childcategoryList;
	}

	/**
	 * 小カテゴリー検索を行います.
	 * 
	 * @return categoryデータ情報
	 */
	public List<Category> findByGrandCategory(Integer id) {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent = :id AND name_all IS NOT NULL ORDER BY id ASC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Category> grandcategoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return grandcategoryList;
	}

	/**
	 * Categoryを１件登録するメソッド.
	 * 
	 * @param category カテゴリー
	 */
	public void insertCategory(Category category) {
		String sql = "INSERT INTO category (parent,name,name_all) VALUES (:parent, :name, :nameAll);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		template.update(sql, param);
	}

	/**
	 * Categoryテーブルのnameで検索し、該当するレコードのidを返すメソッド.
	 * 
	 * @param name 商品名
	 * @return categoryList
	 */
	public Integer findIdCategory(String name) {
		String sql = "SELECT id, name FROM category WHERE name = :name ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return categoryList.get(0).getId();
	}

	/**
	 * Category全件検索.
	 * 
	 * @return カテゴリーリスト
	 */
	public List<Category> findAllCategory() {
		String sql = "SELECT id,parent,name,name_all FORM category;";
		List<Category> categoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return categoryList;
	}

	/**
	 * 大カテゴリを名前で検索し、idを取得するメソッド.
	 * 
	 * @param bigCategoryName
	 * @return categoryList
	 */
	public List<Category> findBigId(String bigCategoryName) {
		String sql = "SELECT id,name FROM category WHERE parent IS NULL AND name_all IS NULL AND name = :bigCategoryName;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("bigCategoryName", bigCategoryName);
		List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		categoryList.get(0);
		return categoryList;
	}

}
