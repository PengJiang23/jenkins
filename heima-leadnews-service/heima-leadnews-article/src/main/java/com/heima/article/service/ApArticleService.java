package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.dtos.ArticleInfoDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mess.ArticleVisitStreamMess;

public interface ApArticleService extends IService<ApArticle> {

    ResponseResult load(Short loadType, ArticleHomeDto articleHomeDto);

    ResponseResult saveArticle(ArticleDto dto);

    ResponseResult loadBehavior(ArticleInfoDto dto);

    ResponseResult load2(Short loadType, ArticleHomeDto dto, boolean  firstPage);

    void updateScore(ArticleVisitStreamMess articleVisitStreamMess);

}
