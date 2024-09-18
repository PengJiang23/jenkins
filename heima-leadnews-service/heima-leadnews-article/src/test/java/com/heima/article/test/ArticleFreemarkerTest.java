package com.heima.article.test;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.ArticleApplication;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleFreemarkerTest {

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;


    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Test
    public void createStaticUrlTest() throws Exception {

        // 1.获取文章内容
        // 2.freemarker生成静态文件
        // 3.上传文件，将访问url存储在数据库中

        ApArticleContent articleContent = apArticleContentMapper.selectOne(new LambdaQueryWrapper<ApArticleContent>()
                .eq(ApArticleContent::getArticleId, 1404705243362627586L));

        if(articleContent != null && StringUtils.isNotBlank(articleContent.getContent())){

            Template template = configuration.getTemplate("article.ftl");

            HashMap<Object, Object> params = new HashMap<>();
            params.put("content", JSONArray.parseArray(articleContent.getContent()));
            StringWriter out = new StringWriter();
            template.process(params,out);

            ByteArrayInputStream is = new ByteArrayInputStream(out.toString().getBytes());

            String path = fileStorageService.uploadHtmlFile("", articleContent.getArticleId() + ".html", is);
            ApArticle article = new ApArticle();
            article.setId(articleContent.getArticleId());
            article.setStaticUrl(path);
            apArticleMapper.updateById(article);

        }
    }
}