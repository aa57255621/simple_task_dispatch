package com.example.demo;

import com.example.demo.utils.SpringContextUtils;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * @author : lp225484
 * @date : 2021/04/11
 * @description:
 */
@Slf4j
public class AgentCustomLoaderController implements TypeFilter {
    private Boolean agentCustomLoader = Boolean.parseBoolean(SpringContextUtils.getApplicationConfig("app.agentCustomLoader"));
    Logger logger = LoggerFactory.getLogger(AgentCustomLoaderController.class);

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        if(!agentCustomLoader) {
            return false;
        }
        boolean excludeScan = annotationMetadata.hasAnnotation("ExcludeScan");
        if(excludeScan){
            return true;
        }
        return false;
    }
}
