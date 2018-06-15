package com.zhuliyi.interactions;


/**
 * Describe : RouterHub 用来定义路由器的路由地址, 以组件名作为前缀, 对每个组件的路由地址进行分组, 可以统一查看和管理所有分组的路由地址
 * RouterHub 存在于基础库, 可以被看作是所有组件都需要遵守的通讯协议, 里面不仅可以放路由地址常量, 还可以放跨组件传递数据时命名的各种 Key 值
 * 路由地址的命名规则为 组件名 + 页面名
 * 组件间的通讯放在这里，如果是组件内通讯建议放在各组件的RouterHub中
 *
 * 页面的跳转用路由
 * Author : zhuly
 * Date : 2018-06-14
 */

public interface RouterHub {
    /**
     * 组件的名称，用module名命名比较好
     */
    String APP = "/app";//宿主app组件
    String BUSINESS1="/business1";//业务1组件
    String BUSINESS2="/business2";//业务2组件

    /**
     * 宿主app分组
     */

    String APP_MAINACTIVITY= APP+"/MainActivity";


    /**
     * 业务1组件
     */
    String BUSINESS1_BUSINESS1ACTIVITY=BUSINESS1+"/Business1Activity";

    /**
     * 业务2组件
     */
    String BUSINESS2_BUSINESS2ACTIVITY=BUSINESS2+"/Business2Activity";


}
