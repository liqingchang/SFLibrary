package com.android.liqingchang.sf;

/**
 * 货物类
 * Created by terry on 15-7-22.
 */
public class Cargo {

    /**
     * 货物名称 String(60)
     * 中文的话不超过20字
     */
    public String name;
    /**
     * 货物数量
     */
    public int count;
    /**
     * 货物单位
     * 如：个、台、本
     */
    public String unit;
    /**
     * 重量
     */
    public int weight;
    /**
     * 货物单价
     */
    public float amount;
    /**
     * 币种
     * 缺省为CNY
     */
    public String currency;

    /**
     * @param name 货物名称
     */
    public Cargo(String name) {
        this.name = name;
    }

    public String toXml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<Cargo ");
        sb.append(" name = '").append(name).append("' ");
        if (count != 0) {
            sb.append(" count = '").append(count).append("' ");
        }
        if (unit != null) {
            sb.append(" unit = '").append(unit).append("' ");
        }
        if (weight != 0) {
            sb.append(" weight = '").append(weight).append("' ");
        }
        if (amount != 0) {
            sb.append(" amount = '").append(amount).append("' ");
        }
        if (currency != null) {
            sb.append(" currency = '").append(currency).append("' ");
        }
        sb.append(">");
        sb.append("</Cargo>");
        return sb.toString();
    }

}
