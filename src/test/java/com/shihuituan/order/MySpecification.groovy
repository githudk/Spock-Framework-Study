package com.shihuituan.order

import spock.lang.Shared
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
class MySpecification extends Specification{

    /**
     * 加上@Shared的属性，会被本类中所有测试方法共享
     */
    @Shared
    def i = 5

    def j = 3

    def object = new Object();

    /**
     * 静态方法会被本类中所有的测试方法共享
     */
    static s = 5

    /**
     * 全局前置方法：在本类中所有测试方法执行之前执行
     * 只有加上 @Shared 或者静态属性可以在这个方法中引用
     * 如果存在子类覆盖父类的 setupSpec()，那么父类的先于子类的执行
     */
    def setupSpec() {
        //执行一些本类中所有测试方法共享的全局数据的初始化工作，比如共享属性数据或静态属性的初始化
        //执行本方法的时机：本类中所有测试方法执行之前，且一共执行一次
    }
    /**
     * 前置方法：在每一个测试方法之前都会执行
     * 如果存在子类覆盖父类的 setup()，那么父类的先于子类的执行
     *
     */
    def setup() {
        // 执行一些非共享数据的初始化工作，每个测试方法执行前都需要执行一次，
        // 执行的结果，提供给接下来即将执行的测试方法使用
    }
    /**
     * 后置方法：在每一个测试方法之后都会执行
     * 如果存在子类覆盖父类的 cleanup()，那么子类的先于父类的执行
     */
    def cleanup() {
        // 执行一些非共享数据的清理工作，每个测试方法执行过后都需要执行一次
        // 清理完必要的数据，防止污染到下一个测试方法，配合setup()方法，可以做到测试方法之间是完全相互隔离的
    }
    /**
     * 全局后置方法：在最后一个测试方法之后执行
     * 只有加上 @Shared 或者静态属性可以在这个方法中引用
     * 如果存在子类覆盖父类的 cleanupSpec()，那么子类的先于父类的执行
     */
    def cleanupSpec() {
        //执行一些本类中所有测试方法共享的全局数据的清理工作，比如共享属性数据或静态属性的初始化
        //执行本方法的时机：本类中所有测试方法执行完后，且一共执行一次
    }

    /**
     * 测试方法的方法名可以是字符串，方便测试者为这个测试起一个更好理解的名字
     * 方法体中必须使用Spock提供的标签来约束一些代码行为，这也是Spock对测试代码规范化的提现
     */
    def "测试一个加法逻辑" (){

        //隐式块
        def e = 11;

        given: "给定数据"
        def i = 5
        def j = 6

        when: "执行需要测试的逻辑"
        def s = studySpockService.sum(i,j)

        then: "预期结果"
        assert s == e
    }

    def "测试加法2" (){

        when: "执行需要测试的逻辑"
        j += 5
        def s = studySpockService.sum(i,j)

        then: "预期结果"
        assert s == 13

    }

    def "用with()封装期望条件" (){

        expect:""
        //当第一个期望条件不成立时，整个测试会认为是失败的，不再检验后面的条件是否成立
        with(){
            1 == 2
            5 == 3
        }
    }

    def "用verifyAll()封装期望条件" (){

        expect:""
        //“软断言”，所有的期望条件都会被检验，并体现在故障报告中
        verifyAll(){
            1 == 2
            5 == 3
        }
    }

    def "数据表格"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c
        1 | 3 | 3
        7 | 4 | 6
        0 | 0 | 0
    }

    def "数据表格_使用双竖线区分出期望值"() {
        expect:
        Math.max(a, b) == c

        //双竖线 || 仅仅是方便区分那个是期望结果，其他和单竖线没什么区别
        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 6
        0 | 0 || 0
    }
}
