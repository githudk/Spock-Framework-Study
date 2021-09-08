package com.shihuituan.order.entity;

/**
 * 描述：学习Spock框架示例
 *
 * @author hudongkang
 * @date 2021-09-08 10:55
 */
public class StockEntity {

    private Long stockId;

    private Long goodsId;

    private Integer count;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
            "stockId=" + stockId +
            ", goodsId=" + goodsId +
            ", count=" + count +
            '}';
    }
}
