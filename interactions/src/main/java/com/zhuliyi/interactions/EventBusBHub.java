package com.zhuliyi.interactions;

/**
 * Describe : AndroidEventBus 作为本方案提供的另一种跨组件通信方式 (第一种是接口下沉, 在 app 的 MainActivity 中已展示, 通过 ARouter 实现)
 * AndroidEventBus 比 greenrobot 的 EventBus 多了一个 Tag, 在组件化中更容定位和管理事件
 * EventBusHub 用来定义 AndroidEventBus 的 Tag 字符串, 以组件名作为 TAG 前缀, 对每个组件的事件进行分组
 * Tag 的命名规则为 组件名 + 页面名 + 动作
 * 比如需要使用 AndroidEventBus 通知订单组件的订单详情页进行刷新, 可以将这个刷新方法的 Tag 命名为 "app/main/update"
 * Tips: 基础库中的 EventBusHub 仅用来存放需要跨组件通信的事件的 Tag, 如果某个事件只想在组件内使用 AndroidEventBus 进行通信
 * 那就让组件自行管理这个事件的 Tag
 * 发送消息事件用eventBus
 * Author : zhuly
 * Date : 2018-06-15
 */

public interface EventBusBHub {
    /**
     * 组件的名称，用module名命名比较好
     */
    String APP = "app";//宿主app组件
    String BUSINESS1="business1";//业务1组件
    String BUSINESS2="business2";//业务2组件


    /**
     * 宿主app分组
     */

    String TAG_APP_MAIN_UPDATE= APP+"/main/update";


    /**
     * 业务1组件
     */
    String TAG_BUSINESS1_UPDATE=BUSINESS1+"/update";

    /**
     * 业务2组件
     */
    String TAG_BUSINESS2_UPDATE=BUSINESS2+"/update";
}
