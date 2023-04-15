package com.aidl.myutils.thread;

import android.util.Log;

/**
 * 使用重入锁后金额正确:<java核心技术卷1>12版p585
 2023-04-15 10:00:12.601 21677-21834 System.out              com.aidl.myutils                     I  Thread[Thread-101,5,main]
 2023-04-15 10:00:12.601 21677-21834 System.out              com.aidl.myutils                     I      497.42 from 99 to 97 Total Balance:  100000.00
 2023-04-15 10:00:12.601 21677-21753 System.out              com.aidl.myutils                     I  Thread[Thread-21,5,main]
 2023-04-15 10:00:12.601 21677-21753 System.out              com.aidl.myutils                     I      380.31 from 19 to 48 Total Balance:  100000.00
 2023-04-15 10:00:12.601 21677-21740 System.out              com.aidl.myutils                     I  Thread[Thread-8,5,main]
 2023-04-15 10:00:12.601 21677-21740 System.out              com.aidl.myutils                     I      114.36 from 6 to 41 Total Balance:  100000.00
 *
 * 非同步线程的模拟取钱操作,竞态同步操作,<java核心技术卷1>12版p582
 * 输出的金额不对:
 * 2023-04-15 09:53:22.414 17410-17504 System.out              com.aidl.myutils                     I  Thread[Thread-50,5,main]
 * 2023-04-15 09:53:22.414 17410-17546 System.out              com.aidl.myutils                     I  Thread[Thread-92,5,main]
 * 2023-04-15 09:53:22.414 17410-17466 System.out              com.aidl.myutils                     I  Thread[Thread-12,5,main]
 * 2023-04-15 09:53:22.414 17410-17527 System.out              com.aidl.myutils                     I      591.36 from 10 to 7Thread[Thread-73,5,main]
 * 2023-04-15 09:53:22.414 17410-17535 System.out              com.aidl.myutils                     I  Thread[Thread-81,5,main]
 * 2023-04-15 09:53:22.414 17410-17535 System.out              com.aidl.myutils                     I      793.98 from 79 to 46 Total Balance:   94186.19
 * 2023-04-15 09:53:22.414 17410-17480 System.out              com.aidl.myutils                     I  Thread[Thread-26,5,main]
 * 2023-04-15 09:53:22.414 17410-17512 System.out              com.aidl.myutils                     I       33.80 from 24 to 41Thread[Thread-58,5,main]
 * 2023-04-15 09:53:22.415 17410-17512 System.out              com.aidl.myutils                     I      913.87 from 56 to 73 Total Balance:   94186.19
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
//        var bank = new Bank(NACCOUNTS,INITIAL_BALANCE);
        var bank = new BankReentrantLock(NACCOUNTS,INITIAL_BALANCE);
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
