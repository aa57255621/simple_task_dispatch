package com.example.demo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.dto.TaskInfoDto;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author lp225484
 */
@Configuration
public class TaskInfo {
    public static List<TaskInfoDto> taskInfoConfig;

    @PostConstruct
    public void initTask() {
        try {
            File file = ResourceUtils.getFile("classpath:task-config.json");
            String json = FileUtils.readFileToString(file);
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONArray taskList = jsonObject.getJSONArray("taskList");
            taskInfoConfig = taskList.toJavaList(TaskInfoDto.class);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
