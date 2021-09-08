package com.shihuituan.order.controller

import com.shihuituan.order.StudySpockUnitTestApplication
import com.shihuituan.order.service.api.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification
import spock.mock.DetachedMockFactory

/**
 * 描述：Spock 在 Spring Boog 环境下的测试
 *
 * @author hudongkang
 * @date 2021-09-07 18:28
 */
//指定要测试的Controller
//如果不写controllers属性，会尝试将所有扫描到的Controller都加载到Ioc容器中
@WebMvcTest(controllers = OrderController.class)
//激活测试配置
@ActiveProfiles("mvc")
//指定启动类
@ContextConfiguration(classes = StudySpockUnitTestApplication.class)
class OrderControllerSpec extends Specification {
    /**
     * Spring官方提供
     * 用于测试 Contoller API
     * 无需手动注册Bean,会自动注入进来
     */
    @Autowired
    MockMvc mvc
    /**
     * 这是一个模拟对象实例的引用
     */
    @Autowired
    OrderService orderService

    def "测试一个简单的 Http API" () {
        expect: "controller是否可用"
        MvcResult mvcResult = mvc.perform(
                //构造请求
                MockMvcRequestBuilders.get("/order/hello")
                //添加参数
                .param("name","张三")
        )
                //期望HTTP调用结果状态为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //打印格式化的结果报告，方便控制台查看
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
        //打印返回的数据
        println(mvcResult.response.getContentAsString())
    }

    def "测试一个 Http API" () {

        expect: "测试创建订单接口，期望如下"
        MvcResult mvcResult = mvc.perform(
                //构造请求
                MockMvcRequestBuilders.post("/order/createOrder")
                        .contentType("application/json").content(order)
        )
                //期望HTTP调用结果状态为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //打印格式化的结果报告，方便控制台查看
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
        //打印返回的数据
        println(mvcResult.response.getContentAsString())
        //判断结果是否符合条件
        result == mvcResult.response.getContentAsString()

        where: ""
        order | result
        getCompleteOrder() | "ok"
        getNoGoodsOrder() | "error"
        getNoGoodsNumbOrder() | "error"
        getDataCountErrorOrder() | "error"
    }

    /**
     * 提供测试环境所需的配置
     */
    @TestConfiguration
    static class MockConfig {
        /**
         * 该工厂允许在Specification之外创建模拟，例如在 Spring 配置中。
         */
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        OrderService orderService() {
            return detachedMockFactory.Mock(OrderService)
        }
    }


    /**
     * 辅助方法
     * @return
     */
    def "getCompleteOrder"() {
        "{\n" +
                "    \"orderId\": null,\n" +
                "    \"orderCode\": null,\n" +
                "    \"orderDate\": null,\n" +
                "    \"goodsIdList\": [123,456,852],\n" +
                "    \"goodsNumbMap\": {\n" +
                "        \"123\":10,\n" +
                "        \"456\":2,\n" +
                "        \"852\":6\n" +
                "        },\n" +
                "    \"orderType\": 3\n" +
                "}"

    }

    def "getNoGoodsOrder"() {
        "{\n" +
                "    \"orderId\": null,\n" +
                "    \"orderCode\": null,\n" +
                "    \"orderDate\": null,\n" +
                "    \"goodsIdList\": [],\n" +
                "    \"goodsNumbMap\": {\n" +
                "        \"123\":10,\n" +
                "        \"456\":2,\n" +
                "        \"852\":6\n" +
                "        },\n" +
                "    \"orderType\": 3\n" +
                "}"

    }

    def getNoGoodsNumbOrder() {
        "{\n" +
                "    \"orderId\": null,\n" +
                "    \"orderCode\": null,\n" +
                "    \"orderDate\": null,\n" +
                "    \"goodsIdList\": [123,456,852],\n" +
                "    \"goodsNumbMap\": {},\n" +
                "    \"orderType\": 3\n" +
                "}"

    }

    def "getDataCountErrorOrder"() {
        "{\n" +
                "    \"orderId\": null,\n" +
                "    \"orderCode\": null,\n" +
                "    \"orderDate\": null,\n" +
                "    \"goodsIdList\": [123,456,852,444],\n" +
                "    \"goodsNumbMap\": {\n" +
                "        \"123\":10,\n" +
                "        \"456\":2,\n" +
                "        \"852\":6\n" +
                "        },\n" +
                "    \"orderType\": 3\n" +
                "}"

    }
}
