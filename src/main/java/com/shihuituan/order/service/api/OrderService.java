package com.shihuituan.order.service.api;

import com.shihuituan.order.entity.OrderEntity;

/**
 * 描述：学习Spock框架示例
 *
 * @author hudongkang
 * @date 2021-09-07 16:01
 */
public interface OrderService {


    /**
     * 创建订单
     * @param orderEntity 订单信息
     * @return
     */
    public void createOrder(OrderEntity orderEntity);

}
