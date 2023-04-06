package com.aidl.myutils.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuweibo on 2020/9/9.
 * 命令的类,保持每一条命令需要的信息，自定义的function类
 */

public class RS485DeviceFunction {
    public int id;
    public List<String> knxAddress = new ArrayList<>();  //knx地址
    public int dataType;     //knx数据类型
    public int dataLength;  //knx数据长度
    public int rs485port;   //485端口号
    public int rs485Address; //485从机地址
    public int rs485DeviceIndex;    //设备编号
    public String type;    //命令的功能类型
    public int operation=-1;     //knx操作类型 读还是写
    public String value;        //值
    public String exp;     //预留参数
    public String protocolType;
    //    evan add,rs485config2 新增字段:["1","2","3"]
    public List<String> dataValueList;
}
