package com.aidl.myutils.json;

import android.util.Log;

import com.aidl.myutils.reflect.ReflectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonTest {
    /**
     * 拷贝对象实例
     */
    public static void copyTest(){
        /**
         *  public List<String> knxAddress = new ArrayList<>();  //knx地址
         *     public int dataType;     //knx数据类型
         *     public int dataLength;  //knx数据长度
         *     public int rs485port;   //485端口号
         *     public int rs485Address; //485从机地址
         *     public int rs485DeviceIndex;    //设备编号
         *     public String type;    //命令的功能类型
         *     public int operation=-1;     //knx操作类型 读还是写
         *     public String value;        //值
         *     public String exp;     //预留参数
         *     public String protocolType;
         *     //    evan add,rs485config2 新增字段:["1","2","3"]
         *     public List<String> dataValueList;
         */

        RS485DeviceFunction function = new RS485DeviceFunction();

        function.id = 1;
        function.knxAddress.add("1/1/1");
        function.dataType = 1;
        function.dataLength = 2;
        function.rs485port = 1;
        function.rs485Address = 2;
        function.rs485DeviceIndex = 3;
        function.type = "Test";
        function.operation = 0; // You can set the operation to any value you want, as it has a default value of -1
        function.value = "Hello";
        function.exp = "World";
        function.protocolType = "Modbus";
        function.dataValueList = new ArrayList<>();
        function.dataValueList.add("1");
        function.dataValueList.add("2");
        function.dataValueList.add("3");

        RS485DeviceFunction copiedFunction = new RS485DeviceFunction(function);
        copiedFunction.setValue("new value");
        Log.i("xwg","function------------------------------");
        ReflectUtils.reflect(function,"xwg");
        Log.i("xwg","copiedFunction------------------------------");
        ReflectUtils.reflect(copiedFunction,"xwg");
    }


    public static void main(String[] args) throws JSONException {
        // 构造一个JSONObject对象
        JSONObject jsonObject = new JSONObject();

        RS485DeviceFunction rs = new RS485DeviceFunction();
        rs.rs485port = 22;
        ArrayList<String> addList = new ArrayList<String>(Arrays.asList("add1", "add2", "add3"));
        rs.knxAddress =  addList;
        rs.rs485DeviceIndex = 1;
        rs.value = "value";
        rs.exp = "exp";
        rs.operation = 0;

        // 添加一些键值对
        jsonObject.put("port", rs.rs485port);

        // 将另一个JSONObject对象作为值添加进去
        JSONObject addressObject = new JSONObject();
        addressObject.put("add1", addList.get(0));
        addressObject.put("add2", addList.get(1));
        addressObject.put("add3", addList.get(2));
        jsonObject.put("add", addressObject);



        // 将JSONObject对象转换成字符串
        String jsonString = jsonObject.toString();
//        System.out.println(jsonString);
        Log.i("xwg","jsonStr:" + jsonString);

        // 解析字符串
        JSONObject newJsonObject = new JSONObject(jsonString);
        int port = newJsonObject.getInt("port");
        JSONObject jo = newJsonObject.getJSONObject("add");
        String s1 = jo.getString("add1");
        String s2 = jo.getString("add2");

        Log.i("xwg","port:" + port);
        Log.i("xwg","s1:" + s1);
        Log.i("xwg","s2:" + s2);
    }
}
