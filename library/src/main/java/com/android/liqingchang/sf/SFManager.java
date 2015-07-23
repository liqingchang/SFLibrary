package com.android.liqingchang.sf;

/**
 * 提供各种
 * Created by terry on 15-7-22.
 */
public class SFManager {

    private static SFManager instance = new SFManager();

    private ISFMethod isfMethod;

    public static SFManager getInstance() {
        return instance;
    }

    private SFManager() {
        isfMethod = new DefaultSFMethod();
    }

    public void order(Order order) {
        isfMethod.order(order);
    }

    public void query(String mailno) {
        isfMethod.query(mailno);
    }

}
