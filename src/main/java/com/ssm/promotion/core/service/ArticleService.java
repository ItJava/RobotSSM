package com.ssm.promotion.core.service;

import com.ssm.promotion.core.entity.Article;

import java.util.List;
import java.util.Map;


public interface ArticleService {

	public List<Article> findArticle(Map<String, Object> map);
	public Long getTotalArticle(Map<String, Object> map);
	public int addArticle(Article article);
	public int updateArticle(Article article);
	public int deleteArticle(String id);
	public Article findById(String id);
}
