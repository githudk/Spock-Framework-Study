package com.shihuituan.order.service

import com.shihuituan.order.entity.OrderEntity
import com.shihuituan.order.entity.StockEntity
import com.shihuituan.order.service.api.StockService
import spock.lang.Specification


/**
 * 描述：学习Spock测试框架
 * Specification英文原意是：规格；规范；明细单；说明书
 * 可见Spock的测试类在语义上就显现出很强的规范约束。
 * 每个测试类都需要直接或者间接继承自Specification。
 * 子类的命名规范，Spock官方建议以Spec后缀结尾
 * @author hudongkang
 * @date 2021-09-08 12:17
 */
class OrderServiceSpec extends Specification {


    def stockService = Mock(StockService)

    def orderService = new OrderServiceImpl(
            stockService: stockService
    )

    /**
     * 模拟库存接口正常返回数据
     * @return
     */
    def "测试订单的创建方法" (){

        given: "给定入参：订单数据"
        def order = getOrder()

        and: "设定库存的模拟调用"
        stockService.getStockNumb(_) >> stock

        when: "测试创建订单"
        orderService.createOrder(order)

        then: "预期如下"
        uptateTimes * stockService.updateStock(_)


        where: "数据表格"
        stock | uptateTimes
        getEnoughStockNumb()   | 3
        getStockEmpty()   | 0
        getStockZero()   | 0

    }



    /**
     * 模拟库存接口正常返回数据
     * @return
     */
    def "测试订单的创建方法2" (){

        given: "给定入参：订单数据"
        def order = getOrder()

        when: "测试创建订单"
        stockService.getStockNumb(_) >> getEnoughStockNumb()
        orderService.createOrder(order)

        then: "预期如下"
        notThrown(Runnable.class)
        3 * stockService.updateStock(_)

        when: "测试创建订单"
        stockService.getStockNumb(_) >> getStockEmpty()
        orderService.createOrder(order)

        then: "预期如下"
        thrown(RuntimeException.class)
        0 * stockService.updateStock(_)

        when: "测试创建订单"
        stockService.getStockNumb(_) >> getStockZero()
        orderService.createOrder(order)

        then: "预期如下"
        thrown(RuntimeException.class)
        0 * stockService.updateStock(_)

    }



    /**
     * 辅助方法
     * @return
     */
    def "getOrder"() {
        def order = new OrderEntity();
        List<Long> goodsIdList = new ArrayList<>();
        goodsIdList.add(123L)
        goodsIdList.add(456L)
        goodsIdList.add(789L)
        order.setGoodsIdList(goodsIdList)
        Map<Long,Integer> goodsNumbMap = new HashMap<>()
        goodsNumbMap.put(123L,7)
        goodsNumbMap.put(456L,6)
        goodsNumbMap.put(789L,4)
        order.setGoodsNumbMap(goodsNumbMap)
        return order

    }

    /**
     * 模拟库存全部够用
     * @param n
     * @return
     */
    private List<StockEntity> "getEnoughStockNumb"() {

        List<StockEntity> stockEntityList = new ArrayList<>()
        def order = getOrder()
        int numb = 0
        while (numb < order.getGoodsIdList().size()){
            StockEntity stockEntity = new StockEntity()
            stockEntity.setStockId((long)numb)
            stockEntity.setGoodsId(order.getGoodsIdList().get(numb))
            stockEntity.setCount((int)(Math.random() * 100) + 100)
            stockEntityList.add(stockEntity)
            numb ++
        }
        return stockEntityList
    }

    def "getStockEmpty"() {
        new ArrayList<>()
    }

    def "getStockZero"() {
        List<StockEntity> stockEntityList = new ArrayList<>()
        def order = getOrder()
        def numb = 0
        while (numb < order.getGoodsIdList().size()){
            StockEntity stockEntity = new StockEntity()
            stockEntity.setStockId(numb)
            stockEntity.setGoodsId(order.getGoodsIdList().get(numb))
            stockEntity.setCount(0)
            stockEntityList.add(stockEntity)
            numb ++
        }
        return stockEntityList
    }

}