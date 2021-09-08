package com.shihuituan.order.service;

import com.shihuituan.order.entity.StockEntity;
import com.shihuituan.order.service.api.StockService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 描述：学习Spock框架示例
 *
 * @author hudongkang
 * @date 2021-09-08 11:24
 */
@Service
public class StockServiceImpl implements StockService {


    /**
     * 获取商品的库存数据
     * @param goodsIdList 商品IdList
     * @return 库存数据
     */
    @Override
    public List<StockEntity> getStockNumb(List<Long> goodsIdList) {

        List<StockEntity> stockEntityList = new ArrayList<>();

        int numb = 0;
        while (numb < goodsIdList.size()){
            StockEntity stockEntity = new StockEntity();
            stockEntity.setStockId((long)numb);
            stockEntity.setGoodsId(goodsIdList.get(numb));
            stockEntity.setCount((int)(Math.random() * 100));
            stockEntityList.add(stockEntity);
            numb ++;
        }
        return stockEntityList;
    }

    @Override
    public void updateStock(StockEntity stockEntity) {
        //更新逻辑
    }
}
