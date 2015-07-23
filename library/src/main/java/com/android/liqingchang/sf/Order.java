package com.android.liqingchang.sf;

import android.util.Log;

import java.util.List;

/**
 * 订单
 * 文档定义了将近50个属性
 * 这里根据业务需求只选择了必要属性增加
 * Created by terry on 15-7-22.
 */
public class Order {

    /**
     * 订单号
     */
    public String orderId;
    /**
     * 顺丰运单号,一个订单只能有一个母单号,如果是子母单的情况,以半角逗号分隔,
     * 主单号在第一个位置,如 “755123456789,001123456789,00212 3456789”,
     * 对于路由推送注册,此字段为必填
     */
//    public String mailno;
    /**
     * 是否要求返回顺丰运单号
     * 1 - 要求
     * is_gen_bill_no
     */
    public int isGenBillNo = 1;
    /**
     * 寄件方公司名称
     * j_company String(100)
     */
    public String sendCompany;
    /**
     * 寄件方联系人
     * j_contact String(100)
     */
    public String sendConact;
    /**
     * 寄件方电话
     * j_tel String(20)
     */
    public String sendTel;
    /**
     * 寄件方省份
     * j_province
     */
    public String sendProvince;
    /**
     * 寄件方城市
     * j_city
     */
    public String sendCity;
    /**
     * 寄件方县区
     * j_county
     */
    public String sendCounty;
    /**
     * 寄件方详细地址
     * j_address String(200)
     */
    public String sendAddress;
    /**
     * 收件方公司名称
     * d_company String(100）
     */
    public String receiverCompany;
    /**
     * 收件方联系人
     * d_contact String(100)
     */
    public String receiverContact;
    /**
     * 收件方联系电话
     * d_tel String(20)
     */
    public String receiverTel;
    /**
     * 到件方所在省份,必须是标准的省名称称谓如:广东省,如果是直辖市, 请直接传北京、上海等。
     * d_province String(30)
     */
    public String receiverProvince;
    /**
     * 到件方所在城市名称,必须是标准的 城市称谓如:深圳市,如果是直辖市,请直接传北京(或北京市)、上海(或上海市)等。
     * d_city String(100)
     */
    public String receiverCity;
    /**
     * 收件方县区
     * d_county
     */
    public String receiverCounty;
    /**
     * 到件方详细地址
     */
    public String receiverAddress;
    /**
     * 付款方式
     * 1 - 寄件方付
     * 2 - 收件方付
     * 3 - 第三方付
     */
    public int payMethod;
    /**
     * 发送货物信息
     */
    public Cargo cargo;

    public Order(String orderId, String sendCompany, String sendConact, String sendTel, String sendProvince, String sendCity, String sendCounty, String sendAddress,
                 String receiverCompany, String receiverContact, String receiverTel, String receiverProvince, String receiverCity, String receiverCounty, String receiverAddress,
                 int payMethod, Cargo cargo) {
        this.orderId = orderId;
        this.sendCompany = sendCompany;
        this.sendConact = sendConact;
        this.sendTel = sendTel;
        this.sendProvince = sendProvince;
        this.sendCity = sendCity;
        this.sendCounty = sendCounty;
        this.sendAddress = sendAddress;
        this.receiverCompany = receiverCompany;
        this.receiverContact = receiverContact;
        this.receiverTel = receiverTel;
        this.receiverProvince = receiverProvince;
        this.receiverCity = receiverCity;
        this.receiverAddress = receiverAddress;
        this.receiverCounty = receiverCounty;
        this.payMethod = payMethod;
        this.cargo = cargo;
    }


    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<Request service='OrderService' lang='zh-CN'>");
        sb.append("<Head>").append(SFConstants.APICODE).append("</Head>");
        sb.append("<Body>");
        sb.append("<Order ");
        sb.append(" orderid='").append(orderId).append("' ");
        sb.append(" j_company='").append(sendCompany).append("' ");
        sb.append(" j_contact='").append(sendConact).append("' ");
        sb.append(" j_tel='").append(sendTel).append("' ");
        sb.append(" j_province='").append(sendProvince).append("' ");
        sb.append(" j_city='").append(sendCity).append("' ");
        sb.append(" j_county='").append(sendCounty).append("' ");
        sb.append(" j_address='").append(sendAddress).append("' ");
        sb.append(" d_company='").append(receiverCompany).append("' ");
        sb.append(" d_contact='").append(receiverContact).append("' ");
        sb.append(" d_tel='").append(receiverTel).append("' ");
        sb.append(" d_province='").append(receiverProvince).append("' ");
        sb.append(" d_city='").append(receiverCity).append("' ");
        sb.append(" d_county='").append(receiverCounty).append("' ");
        sb.append(" d_address='").append(receiverAddress).append("' ");
        sb.append(" pay_method='").append(payMethod).append("' ");
        sb.append(">");
        sb.append(cargo.toXml());
        sb.append("</Order>");
        sb.append("</Body>");
        sb.append("</Request>");
        Log.i("terry", sb.toString());
        return sb.toString();
    }

}
