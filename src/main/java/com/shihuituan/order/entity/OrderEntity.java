package com.shihuituan.order.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述：学习Spock框架示例
 *
 * @author hudongkang
 * @date 2021-09-08 10:32
 */
public class OrderEntity {

    private Long orderId;

    private String orderCode;

    private Date orderDate;

    private List<Long> goodsIdList;

    private Map<Long,Integer> goodsNumbMap;

    private Integer orderType;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Map<Long, Integer> getGoodsNumbMap() {
        return goodsNumbMap;
    }

    public void setGoodsNumbMap(Map<Long, Integer> goodsNumbMap) {
        this.goodsNumbMap = goodsNumbMap;
    }

    public List<Long> getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(List<Long> goodsIdList) {
        this.goodsIdList = goodsIdList;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
            "orderId=" + orderId +
            ", orderCode='" + orderCode + '\'' +
            ", orderDate=" + orderDate +
            ", goodsIdList=" + goodsIdList +
            ", goodsNumbMap=" + goodsNumbMap +
            ", orderType=" + orderType +
            '}';
    }
}
