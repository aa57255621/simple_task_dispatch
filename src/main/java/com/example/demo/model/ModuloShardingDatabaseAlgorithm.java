package com.example.demo.model;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;
import groovy.util.logging.Slf4j;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author lp225484
 */
@Slf4j
public final class ModuloShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {

    private Logger log = LoggerFactory.getLogger(ModuloShardingDatabaseAlgorithm.class);

    /**
     * 选库
     */
    @Override
    public String doSharding(final Collection<String> databaseNames, final PreciseShardingValue<Long> shardingValue) {

        log.info("databaseNames:{}", JSON.toJSONString(databaseNames));
        log.info("shardingValue:{}", JSON.toJSONString(shardingValue));

        String databaseName = "";

        // 只做单库分表
        if ("t_user".equalsIgnoreCase(shardingValue.getLogicTableName())) {
            databaseName = (String) databaseNames.toArray()[0];
        }
        // 分库分表
        if ("t_order".equalsIgnoreCase(shardingValue.getLogicTableName()) ||
                "t_order_item".equalsIgnoreCase(shardingValue.getLogicTableName())) {
            for (String each : databaseNames) {
                if (each.endsWith(shardingValue.getValue() % databaseNames.size() + "")) {
                    databaseName = each;
                    break;
                }
            }
        }
        log.info("databaseName:{}", databaseName);
        if (StringUtils.isNotEmpty(databaseName)) {
            return databaseName;
        }
        throw new UnsupportedOperationException();
    }


    /**
     * 实现between and查询
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        log.info("collection:{}", JSON.toJSONString(collection));
        log.info("rangeShardingValue:{}", JSON.toJSONString(rangeShardingValue));

        Collection<String> collect = new ArrayList<>();
        Range<Long> valueRange = rangeShardingValue.getValueRange();

        log.info("valueRange:{}", JSON.toJSONString(valueRange));
        log.info("lowerEndpoint:{}", valueRange.lowerEndpoint());
        log.info("upperEndpoint:{}", valueRange.upperEndpoint());


        for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % collection.size() + "")) {
                    collect.add(each);
                }
            }
        }

        log.info("collect:{}", JSON.toJSONString(collect));

        return collect;
    }
}
