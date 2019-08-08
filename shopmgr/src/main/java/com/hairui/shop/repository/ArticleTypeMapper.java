package com.hairui.shop.repository;

import com.hairui.shop.bean.ArticleType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ArticleTypeMapper 数据访问类
 * @author hairui @qq 970557230
 * @Email 970557230@qq.com
 * @date 2019-08-06 07:39:22
 * @version 1.0
 */
public interface ArticleTypeMapper {
    @Select("select * from ec_article_type")
    List<ArticleType> getArticleTypes();

    @Select("select * from ec_article_type where length(code) = 4")
    List<ArticleType> getFirstArticleTypes();

    @Select("select * from ec_article_type where code like #{typeCode} and length(code) = #{len}")
    List<ArticleType> loadSecondTypes(@Param("typeCode") String typeCode, @Param("len") int i);

    @Select("select * from ec_article_type where code = #{XXXX}")
    ArticleType getTypeByCode(String typeCode);

}