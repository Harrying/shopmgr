package com.hairui.shop.service.impl;

import com.hairui.shop.bean.Article;
import com.hairui.shop.bean.ArticleType;
import com.hairui.shop.bean.User;
import com.hairui.shop.repository.ArticleMapper;
import com.hairui.shop.repository.ArticleTypeMapper;
import com.hairui.shop.repository.UserMapper;
import com.hairui.shop.service.ShopService;
import com.hairui.shop.utils.Pager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("shopService")
public class ShopServiceImpl implements ShopService {
    // 得到数据访问层对象
    @Resource
    private ArticleTypeMapper  articleTypeMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ArticleMapper articleMapper;

    public List<ArticleType> getArticleTypes() {
        return articleTypeMapper.getArticleTypes();
    }

    public Map<String, Object> login(String loginName, String passWord) {
        Map<String,Object> results = new HashMap<String, Object>();
        //1.判断参数是否为空
        if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(passWord)){
            //参数为空
            results.put("code",1);
            results.put("msg","参数为空");
        } else{
            User user = userMapper.login(loginName);
            if(user != null){
                //判断密码
                if(user.getPassword().equals(passWord)){
                    //登陆成功了
                    //应该将登陆成功的用户存入到Session中
                    results.put("code",0);
                    results.put("msg",user);
                }else{
                    results.put("code",2);
                    results.put("msg","密码错误了");
                }

            }else{
                results.put("code",3);
                results.put("msg","用户不存在");
            }
        }
        return results;
    }

    public List<ArticleType> loadFirstArticleTypes() {
        List<ArticleType> articleTypes = articleTypeMapper.getFirstArticleTypes();
        return articleTypes;
    }

    public List<ArticleType> loadSecondTypes(String typeCode) {
        List<ArticleType> articleTypes = articleTypeMapper.loadSecondTypes(typeCode+"%",typeCode.length()+4);
        return articleTypes;
    }

    public List<Article> searchArticles(String typeCode, String secondType, String title, Pager pager) {
        //界面需要当前总共有多少条数据
        int count = articleMapper.count(typeCode,secondType,title);
        pager.setTotalCount(count);
        return articleMapper.searchArticles(typeCode,secondType,title,pager);
    }

    public void deleteById(int id) {
        articleMapper.deleteById(id);
    }

    public Article getArticleById(String id) {
        return articleMapper.getArticleById(id);
    }

    public void updateArticle(Article article) {
        articleMapper.update(article);
    }


    public void saveArticle(Article article) {
        article.setCreateDate(new Date());
        articleMapper.save(article);
    }


}