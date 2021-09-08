package com.shihuituan.order.service;

import com.shihuituan.order.entity.OrderEntity;
import com.shihuituan.order.entity.StockEntity;
import com.shihuituan.order.service.api.OrderService;
import com.shihuituan.order.service.api.StockService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 描述：学习Spock框架示例
 *
 * @author hudongkang
 * @date 2021-09-07 16:05
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 依赖库存接口
     */
    @Autowired
    private StockService stockService;

    @Override
    public void createOrder(OrderEntity orderEntity) {

        //取库存量
        List<StockEntity> stockEntityList =
            stockService.getStockNumb(orderEntity.getGoodsIdList());

        //校验库存个数
        if (CollectionUtils.isEmpty(stockEntityList)
            || stockEntityList.size() != orderEntity.getGoodsIdList().size()) {
            throw new RuntimeException("库存信息错误");
        }

        //校验库存是否够用
        Map<Long, Integer> goodsNumbMap = orderEntity.getGoodsNumbMap();
        for (StockEntity stockEntity : stockEntityList) {
            Integer count = stockEntity.getCount();
            Integer goodsNumb = goodsNumbMap.get(stockEntity.getGoodsId());
            if (count < goodsNumb) {
                throw new RuntimeException("商品数量不足");
            }
        }

        //遍历更新库存
        for (StockEntity stockEntity : stockEntityList) {
            //更新库存
            stockService.updateStock(stockEntity);
        }

        return;
    }
}
