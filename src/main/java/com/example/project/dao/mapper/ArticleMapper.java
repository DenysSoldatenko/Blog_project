package com.example.project.dao.mapper;

import com.example.project.entities.Article;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper class for mapping a ResultSet row to an Article entity.
 */
public class ArticleMapper extends AbstractMapper<Article> {

  @Override
  public Article handleItem(ResultSet rs) throws SQLException {
    Article a = convert.toBean(rs, Article.class);
    a.setIdCategory(rs.getInt("id_category"));
    return a;
  }
}
