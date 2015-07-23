package com.android.liqingchang.sf;

/**
 * 顺丰接口方法能力声明
 * Created by terry on 15-7-22.
 */
public interface ISFMethod {

    /**
     * 下单
     * @param order 订单内容
     * @return 下单结果
     */
    public String order(Order order);

    /**
     * 订单查询
     * @param type 查询类型 1 - 运单号查询 2 - 订单号查询
     * @param mailno 订单号
     * @return 查询结果
     */
    public String query(int type, String mailno);

    /**
     * 订单查询
     * @param mailno 订单号
     * @return 查询结果
     */
    public String query(String mailno);

}
