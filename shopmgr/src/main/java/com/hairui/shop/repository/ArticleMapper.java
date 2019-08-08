package com.hairui.shop.repository;

import com.hairui.shop.bean.Article;
import com.hairui.shop.utils.Pager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {

    List<Article> searchArticles(@Param("typeCode") String typeCode, @Param("secondType") String secondType,
                                 @Param("title") String title,@Param("pager") Pager pager);

    int count(@Param("typeCode") String typeCode, @Param("secondType") String secondType,
              @Param("title") String title);

    @Delete("delete from ec_article where id = #{xxxx}")
    void deleteById(int id);

    @Select("select * from ec_article where id = #{XX}")
    @ResultMap("articleResultMap")
    Article getArticleById(String id);

    void update(Article article);

    void save(Article article);
}
