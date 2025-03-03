package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;


public interface WmMaterialService extends IService<WmMaterial> {

    ResponseResult uploadPicture(MultipartFile file);

    ResponseResult findList( WmMaterialDto dto);

    ResponseResult deletePicture(Integer id);

    ResponseResult collectPicture(Integer id);

    ResponseResult cancelCollectPicture(Integer id);
}
