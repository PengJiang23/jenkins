package com.heima.common.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.heima.model.common.annotation.IdEncrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于过滤序列化时处理的字段
 * 对于bean中的属性带有注解/名字为id属性处理
 */
public class ConfusionSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                     BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        List<BeanPropertyWriter> newWriter = new ArrayList<>();
        for(BeanPropertyWriter writer : beanProperties){
            String name = writer.getType().getTypeName();
            if(null == writer.getAnnotation(IdEncrypt.class) && !writer.getName().equalsIgnoreCase("id")){
                newWriter.add(writer);
            } else {
                writer.assignSerializer(new ConfusionSerializer());
                newWriter.add(writer);
            }
        }
        return newWriter;
    }
}
