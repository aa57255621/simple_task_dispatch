package com.example.demo.config.hotdeploy;

import com.example.demo.utils.SpringContextUtils;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

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
        if(!HotDeploy.agentWorkerHotDeploy) {
            return false;
        }
        boolean excludeScan = annotationMetadata.hasAnnotation("ExcludeScan");
        if(excludeScan){
            return true;
        }
        return false;
    }
}
