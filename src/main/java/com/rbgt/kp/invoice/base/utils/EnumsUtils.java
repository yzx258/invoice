package com.rbgt.kp.invoice.base.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.ClassUtil;
import com.alibaba.fastjson.JSON;
import com.rbgt.kp.invoice.base.dto.enums.EnumsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2020
 * @author： yucw
 * @date： 2020/12/15 18:13
 * @version： 1.0
 * @description:
 */
@Slf4j
@Component
public class EnumsUtils {

    @Autowired
    private CacheUtils cacheUtils;
    /**
     * 需要获取的包路径
     */
    private static final String URL = "com.rbgt.jw.base.enums";

    /**
     * 根据包路径获取所有枚举
     * @return map
     */
    public Object getAllEnumByClassName() {
        if(null != cacheUtils.getCache("EnumsAll")){
            log.info("获取缓存里的枚举");
            String enumsAll = cacheUtils.getCache("EnumsAll");
            Map<String,Object> maps = (Map)JSON.parse(enumsAll);
            return maps;
        }
        Set<Class<?>> classes = ClassUtil.scanPackage(URL);
        Map<String,Object> map = new LinkedHashMap<>();
        for (Class obj : classes) {
            List<EnumsDTO> list = new ArrayList<>();
            try{
                Object[] objects = obj.getEnumConstants();
                Method getCode = obj.getMethod("getCode");
                Method getMessage = obj.getMethod("getMsg");
                for (Object obj1 : objects) {
                    EnumsDTO enumsDTO = new EnumsDTO();
                    enumsDTO.setCode(getCode.invoke(obj1)+"");
                    enumsDTO.setMsg(getMessage.invoke(obj1)+"");
                    list.add(enumsDTO);
                }
            }catch (Exception e){
                log.error("【{}】获取枚举失败",obj.getSimpleName());
            }
            map.put(obj.getSimpleName(),list);
        }
        cacheUtils.saveCache("EnumsAll",JSON.toJSONString(map), DateUnit.SECOND.getMillis() * 5);
        return map;
    }
}
