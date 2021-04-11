package com.example.demo.utils.dto;

import lombok.Data;
import java.util.List;

/**
 * @author lp225484
 */
@Data
public class TaskInfoDto {
    private String name;
    private List<String> agentList;
}
