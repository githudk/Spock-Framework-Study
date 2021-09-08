package com.shihuituan.order.service.api;

import com.shihuituan.order.entity.StockEntity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 描述：学习Spock框架示例
 *
 * @author hudongkang
 * @date 2021-09-07 16:03
 */
@Service
public interface StockService {

    /**
     * 获取库存数量
     * @param goodsIdList 商品IdList
     * @return
     */
    public List<StockEntity> getStockNumb(List<Long> goodsIdList);

    /**
     * 更新库存
     * @param stockEntity 库存
     */
    public void updateStock(StockEntity stockEntity);

}
