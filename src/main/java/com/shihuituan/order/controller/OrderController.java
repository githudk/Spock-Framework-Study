package com.shihuituan.order.controller;


import com.shihuituan.order.entity.OrderEntity;
import com.shihuituan.order.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：Spock单元测试示例
 *
 * @author hudongkang
 * @date 2021-09-07 18:23
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    /**
     * 依赖订单接口
     */
    @Autowired
    private OrderService orderService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        return "hello " + name;
    }

    /**
     * 获取订单信息
     * @param id 订单ID
     * @return 订单信息
     */
    @GetMapping("/getOrderById")
    @ResponseBody
    public OrderEntity hello(Long id) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(id);
        return orderEntity;
    }

    /**
     * 创建订单数据
     * @param orderEntity 订单
     * @return ok or error
     */
    @PostMapping("/createOrder")
    @ResponseBody
    public String createOrder(@RequestBody OrderEntity orderEntity) {
        if (CollectionUtils.isEmpty(orderEntity.getGoodsIdList())) {
            return "error";
        }
        if (CollectionUtils.isEmpty(orderEntity.getGoodsNumbMap())) {
            return "error";
        }
        if (orderEntity.getGoodsNumbMap().size() != orderEntity.getGoodsIdList().size()) {
            return "error";
        }
        orderService.createOrder(orderEntity);
        return "ok";
    }


}
