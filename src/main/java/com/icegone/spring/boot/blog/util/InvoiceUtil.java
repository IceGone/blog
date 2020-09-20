package com.icegone.spring.boot.blog.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author Icegone
 * @date 2019-9-13
 */
public class InvoiceUtil {
    //https://einvoice.crv.com.cn/ole/index.html?
    // resouce=OLE
    // &token=bDyYx:kZU6X5g2XQLc*foRGgqw0lhEg8qYNWDNAzdQrWl*mpy*VYW1B2QXR8OZ5J3tYT9x4aWK7R3TErHVgPhEol1hcBxxmE1ZOmidkGLIjGGy863GhE3YD6TN:TsQjouOGquuPnXOA_&
    // resouce=OLE&
    // invoiceNo=31201889&
    // invoiceCode=044001900111


//    private static String REQUEST_URL="https://einvoice.crv.com.cn/einvoice/order/query/id";
    private static String REQUEST_URL="https://einvoice.crv.com.cn";
    private static String RESOURCE="OLE";
    private static String APPKEY="PYT_APPKEY";
    private static String SIGN="c8244c0a05b104759017a6144110d9b071beb0961c01a56bb8bd1c2d723681fd";
    private static String TIMESTAMPT="1568386349083";
    private static String TOCKEN="bDyYx:kZU6X5g2XQLc*foRGgqw0lhEg8qYNWDNAzdQrWl*mpy*VYW1B2QXR8OZ5J3tYT9x4aWK7R3TErHVgPhEol1hcBxxmE1ZOmidkGLIjGGy863GhE3YD6TN:TsQjouOGquuPnXOA_";
    private static String V="1.0";
    private static String SHEEDID="A31DP0231906010001120";
    private static String INVOICENO="31201889";
    private static String INVOICECODE="044001900111";

    public static List<String> getValidInvoice(){
        JSONObject jsonObject =new JSONObject();
        JSONObject jsonObjectSheedId =new JSONObject();
        jsonObjectSheedId.put("sheetId",SHEEDID);
        jsonObject.put("appKey",APPKEY);
        jsonObject.put("resouce",RESOURCE);
        jsonObject.put("sign",SIGN);
        jsonObject.put("timestamp",TIMESTAMPT);
        jsonObject.put("token",TOCKEN);
        jsonObject.put("v",V);
        jsonObject.put("data",jsonObjectSheedId);
        jsonObject.put("invoiceNo",INVOICENO);
        jsonObject.put("invoiceCode",INVOICECODE);
        String postData = jsonObject.toJSONString();
        String postResult = HttpClientUtil.reqClient(REQUEST_URL, "POST", postData);
        return null;
    }





}
