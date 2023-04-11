package com.aidl.myutils.thread;

import android.util.Log;

/**
 * 非同步线程的模拟取钱操作
 */
public class UnsynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    /**
     * 多线程转账操作,余额总量不对,求和不为1000 * 10
     * @param args  命令行参数
     */
    public static void main(String[] args) {
        var bank = new Bank(NACCOUNTS,INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++){
            int fromAccount = i;
            Runnable r = () ->{
                try{
                    while (true){   //随机转账
                        int toAccount = (int)(bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount,toAccount,amount);
                        Thread.sleep((int)(DELAY * Math.random()));
                    }
                }catch (InterruptedException e){
                    Log.i("xwg", "InterruptedException:" + e);
                }
            };
            var t = new Thread(r);
            t.start();
        }
    }
}
