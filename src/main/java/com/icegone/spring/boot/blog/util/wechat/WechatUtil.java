package com.icegone.spring.boot.blog.util.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icegone.spring.boot.blog.util.HttpClientUtil;
import com.icegone.spring.boot.blog.util.HttpUtil;
import com.icegone.spring.boot.blog.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.util.WebUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 微信常用工具类
 * @author Icegone
 * @date 2019/6/1
 */
public class WechatUtil {
    private static final Logger logger = LoggerFactory.getLogger(WechatUtil.class.getName());

    /** 创建读取配置文件对象 **/
    private static PropertiesUtil pu = new PropertiesUtil("/weChat.properties");

    /** 微信公众号设置的token **/
    private static String token;
    /** 微信公众号的appID **/
    private static  String appID;
    /** 微信公众号的appsecret **/
    private static  String appsecret;
    /** 微信公众号的access_token **/
    private static String access_token;
    /** 微信公众号的access_token的有效期，目前为2个小时，即7200秒 **/
    private static long access_token_expires_in;
    /** 微信公众号的jsapi_ticket **/
    private static String jsapi_ticket;
    /** 微信公众账号的jsapi_ticket的有效期，目前为2个小时，即7200秒 **/
    private static long jsapi_ticket_expires_in;
    /** 模板消息的模板id **/
    private static String template_id;

    /** 有效期常量，单位为：毫秒 **/
    private static final long EFFECTIVE_TIME = 7200000;
    /** 获取access_token时请求的url **/
    private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /** 获取jsapi_ticket时请求的url **/
    private static final String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    /** 发送模板消息的url **/
    public static final String SEND_TEMPLATEMESS_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
    /** 通过全局的access_token获取用户信息的url **/
    public static final String GET_UDERINFO_BY_GLOBALTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s";
    /** 网页授权获取用户基本信息时请求的url（获取code） **/
    public static final String GET_CODE_OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
    /** 网页授权获取用户基本信息时请求的url（获取access_token） **/
    public static final String GET_ACCESS_TOKEN_OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    /** 获取创建自定义菜单时要用的access_token的url **/
    public static final String GET_CREATE_MENU_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /** 获取创建自定义菜单的url **/
    public static final String GET_CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    /**网页授权获取用户基本信息时请求的url **/
    public static final String GET_USERINFO_UEL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
    /**上传图片（新增临时素材）的url  **/
    public static final String UPLOAD_IMAGE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";
    /**下载图片（获取临时素材）的url   */
    public static final String GET_IMAGE_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    /** (客服接口)发送图片给用户  **/
    public static final String SENDIMAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
    /** 上传永久素材接口url（上传语音消息）**/
    public static final String UPLOAD_VOICE_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";
    /** 获取永久素材列表的URL **/
    public static final String GET_VOICE_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";

    /** 网页授权作用域，snsapi_base **/
    public static final String SCOPE_SNSAPI_BASE = "snsapi_base";
    /** 网页授权作用域，snsapi_userinfo **/
    public static final String SCOPE_SNSAPI_USERINFO = "snsapi_userinfo";


    /** jsapi_ticket所存放的map的key名称 **/
    public static final String KEY_URL = "url";
    public static final String KEY_JS_TICKET = "jsapi_ticket";
    public static final String KEY_NONCESTR = "noncestr";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_SIGNATURE = "signature";
    public static final String KEY_APPID = "appId";

    /** 获取微信公众号设置的token **/
    public static String getToken() {
        if(isEmpty(token)){
            token = isEmpty(pu.getProValue("weChat.token")) ? "" : pu.getProValue("weChat.token");
        }
        return token;
    }

    /** 获取微信公众号的appID **/
    public static String getAppID() {
        if(isEmpty(appID)){
            appID = isEmpty(pu.getProValue("weChat.appID")) ? "" : pu.getProValue("weChat.appID");
        }
        return appID;
    }

    /** 获取微信公众号的appsecret**/
    public static String getAppsecret() {
        if(isEmpty(appsecret)){
            appsecret = isEmpty(pu.getProValue("weChat.appsecret")) ? "" : pu.getProValue("weChat.appsecret");
        }
        return appsecret;
    }

    /** 获取模板消息的模板id **/
    public static String getTemplateId() {
        if(isEmpty(template_id)) {
            template_id = isEmpty(pu.getProValue("weChat.template_id")) ? "" : pu.getProValue("weChat.template_id");
        }
        return template_id;
    }

    /**
     * 检验服务器地址的有效性，确保请求的来源是微信服务器
     * 步骤如下：
     * (1）将token、timestamp、nonce三个参数进行字典序排序
     * (2）将三个参数字符串拼接成一个字符串进行sha1加密
     * (3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @return 如果根据参数进行判断，是来自微信服务器，那么返回值为true，否则返回值为false
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        if (isEmpty(signature) || isEmpty(timestamp) || isEmpty(nonce)) {
            return false;
        }

        String[] arr = new String[]{getToken(), timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典顺序进行排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest md;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符拼接成一个字符串进行SHA1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将加密后的字符串与signature进行比较，如果不相同，那么说明请求的来源不是微信服务器
        return (tmpStr != null) && tmpStr.equals(signature.toUpperCase());
    }

    /**
     * 使用自动生成的字符串和时间戳获得对应的签名
     *
     * @param url         需要使用js Api的页面url，需要动态获得
     * @return 返回在页面中可用的jsapi_ticket
     */
    public static Map<String, String> getJsSignature(String url) {
        return getJsSignature(getJsTicket(), url, create_nonce_str(), create_timestamp());
    }

    /**
     * 使用自动生成的字符串和时间戳获得对应的签名
     *
     * @param jsApiTicket js Api 票据
     * @param url         需要使用js Api的页面url，需要动态获得
     * @return 返回在页面中可用的jsapi_ticket
     */
    public static Map<String, String> getJsSignature(String jsApiTicket, String url) {
        return getJsSignature(jsApiTicket, url, create_nonce_str(), create_timestamp());
    }

    /**
     * 获取JS Signature 签名的加密方法
     * 步骤1. 对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串
     * 步骤2. 对拼接后的字符串进行sha1签名，得到signature
     *
     * @param jsApiTicket js Api 票据
     * @param url         需要使用js Api的页面url，需要动态获得
     * @return 返回在页面中可用的jsapi_ticket
     */
    public static Map<String, String> getJsSignature(String jsApiTicket, String url, String nonceStr, String timestamp) {
        Map<String, String> ret = new HashMap<String, String>();
        String tmpStr;
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        tmpStr = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(tmpStr.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ret.put(KEY_URL, url);
        ret.put(KEY_JS_TICKET, jsApiTicket);
        ret.put(KEY_NONCESTR, nonceStr);
        ret.put(KEY_TIMESTAMP, timestamp);
        ret.put(KEY_SIGNATURE, signature);
        ret.put(KEY_APPID, getAppID());
        return ret;
    }

    /**
     * 获取微信公众号access_token
     *
     * @return 返回微信公众号的全局唯一接口调用凭据access_token
     */
    public static String getAccessToken(){
        // 发送http get请求access_token，解析返回的结果,
        // 并把"access_token"赋值给access_token，把(System.currentTimeMillis() + ("expires_in" - 60) * 1000)赋值给access_token_expires_in
        if(access_token == null || System.currentTimeMillis() > access_token_expires_in){
            String url = String.format(GET_ACCESS_TOKEN_URL,getAppID(),getAppsecret());
            String respData = HttpClientUtil.reqClient(url,"GET",null);
            JSONObject jObject = JSONObject.parseObject(respData);
            if(jObject != null){
                access_token_expires_in = (jObject.get("expires_in") != null) ?
                        (System.currentTimeMillis() + (Long.parseLong(jObject.get("expires_in").toString())-60) * 1000) : System.currentTimeMillis();
                access_token = (jObject.get("access_token") != null) ? jObject.get("access_token").toString() : "";
            }
        }
        return access_token;
    }

    /**
     * 获取用access_token请求到的jsapi_ticket
     *
     * @return 返回微信公众号用access_token发送http GET请求到的jsapi_ticket
     */
    public static String getJsTicket(){
        // 发送http get请求jsapi_ticket，解析返回的结果，
        // 并把"ticket"赋值给jsapi_ticket，把(System.currentTimeMillis() + ("expires_in"-60) * 1000)赋值给jsapi_ticket_expires_in
        if(jsapi_ticket == null || System.currentTimeMillis() > jsapi_ticket_expires_in){
            String url = String.format(GET_JSAPI_TICKET_URL,getAccessToken());
            String respData = HttpClientUtil.reqClient(url,"GET",null);
            JSONObject jObject = JSONObject.parseObject(respData);
            if(jObject != null){
                jsapi_ticket_expires_in = (jObject.get("expires_in") != null) ?
                        (System.currentTimeMillis() + (Long.parseLong(jObject.get("expires_in").toString())-60) * 1000) : System.currentTimeMillis();
                jsapi_ticket = (jObject.get("ticket") != null) ? jObject.get("ticket").toString() : "";
            }
        }
        return jsapi_ticket;
    }

    /**
     * 使用全局的access_token与用户openid获取用户基本信息
     *
     * @param openId
     * @return 返回用户信息
     */
    public static Map<String, Object> getUserInfoByGlobal(String openId){
        Map<String, Object> resMap = new HashMap<String, Object>();
        String url = String.format(GET_UDERINFO_BY_GLOBALTOKEN_URL,getAccessToken(),openId);
        String respData = HttpClientUtil.reqClient(url,"GET",null);
        if(!isEmpty(respData)){
            resMap = (Map<String,Object>) JSON.parse(respData);
        }
        return resMap;
    }

    /**
     * 使用网页授权取到的code来获取用户基本信息
     * @param code
     * @return 返回用户信息
     */
    public static Map<String, Object> getUserInfoByOauth2(String code){
        // 第一步：根据code获取openid
        String url = String.format(WechatUtil.GET_ACCESS_TOKEN_OAUTH2_URL,getAppID(),getAppsecret(),code);
        String respData = HttpClientUtil.reqClient(url,"GET",null);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if(!isEmpty(respData)){
            resMap = (Map<String,Object>) JSON.parse(respData);
            String openid = (resMap.get("openid") != null) ? resMap.get("openid").toString() : null;
            if(openid != null){
                //第二步：根据openid获取用户信息
                resMap = getUserInfoByGlobal(openid);
            }
        }
        return resMap;
    }


    /**
     * 将字节数组转化为字符串
     *
     * @param byteArray 需要进行转换的字节数组
     * @return 转换后的十六进制的字符串
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (byte aByteArray : byteArray) {
            strDigest += byteToHexStr(aByteArray);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制的字符串
     *
     * @param mByte 需要进行转化的字节
     * @return 返回对应的十六进制字符
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return new String(tempArr);
    }

    /**
     * 判断参数是否为空
     *
     * @param tst 需要进行判断的参数
     * @return 如果参数是null或者是空字符串，那么返回值为true，否则返回值为false
     */
    private static boolean isEmpty(String tst) {
        return tst == null || tst.length() == 0;
    }


    /**
     * 将字节数组编码为十六进制
     *
     * @param hash 需要进行编码的字节数组
     * @return 返回编码后的字符串
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 获得随机字符串
     *
     * @return 随机字符串
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获得时间戳
     *
     * @return 返回时间戳
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 微信模板信息公用方法
     *模板样例:color也是自己传过来，默认的为：#173177.
     * 以下是模板消息的json数据格式。
     *{
     * 		"yxq": {
     * 			"color": "#173177",
     * 			"value": "2018-12-10当天"
     *                },
     * 		"bldt": {
     * 			"color": "#173177",
     * 			"value": "广州市天河区建中路12号"
     *        },
     * 		"yysj": {
     * 			"color": "#173177",
     * 			"value": "2018-12-10 10:51:05"
     *        }
     *        }
     * @param openId         接收消息的用户openid
     * @param template_id   模板id
     * @param paraUrl       点击后跳转的详情地址(设置值为""时，微信用户接收到的消息中，"详情"一行则不显示)
     * @param dataJson      模板里要显示的数据变量
     * @return
     */
    @SuppressWarnings("all")
    public static JSONObject sendTemplateMessage(String openId ,String template_id,String paraUrl,JSONObject dataJson){
        JSONObject  jsonObject = new JSONObject();
        if (StringUtils.isEmpty(openId)){
            jsonObject.put("errcode",40001);
            jsonObject.put("errmsg","openId为空");
            jsonObject.put("msgid","");
            return jsonObject;
        }
        if (StringUtils.isEmpty(template_id)){
            jsonObject.put("errcode",40001);
            jsonObject.put("errmsg","template_id为空");
            jsonObject.put("msgid","");
            return jsonObject;
        }
//        if (StringUtils.isEmpty(paraUrl)){
//            jsonObject.put("errcode",40001);
//            jsonObject.put("errmsg","paraUrl为空");
//            jsonObject.put("msgid","");
//            return jsonObject;
//        }
        if (null == dataJson){
            jsonObject.put("errcode",40001);
            jsonObject.put("errmsg","模板数据json对象为空");
            jsonObject.put("msgid","");
            return jsonObject;
        }
        JSONObject postJson = new JSONObject();
        postJson.put("touser",openId);    //接收消息的用户openid
        postJson.put("template_id",template_id);    //模板id
        postJson.put("url",paraUrl);    //点击后跳转的详情地址(设置值为""时，微信用户接收到的消息中，"详情"一行则不显示)
        postJson.put("data",dataJson);    //模板里要显示的数据变量
        logger.info("模板json数据："+dataJson.toJSONString());
        String postData = postJson.toJSONString();
        logger.info("发送模板消息参数为："+postData);
        String url = String.format(WechatUtil.SEND_TEMPLATEMESS_URL, WechatUtil.getAccessToken());
        logger.info(postData);
        String resData = HttpClientUtil.reqClient(url,"POST",postData);
        logger.info("发送模板消息结果为："+resData);
        return JSONObject.parseObject(resData);
    }

    /**
     * 创建自定义菜单
     *
     * @return
     */
    public static String create_menu(){
        String url = String.format(GET_CREATE_MENU_TOKEN_URL,getAppID(),getAppsecret());
        String respData = HttpClientUtil.reqClient(url,"GET",null);
        if(!isEmpty(respData)){
            Map<String, Object> resMap = (Map<String,Object>) JSON.parse(respData);
            String access_token = (resMap.get("access_token") != null) ? resMap.get("access_token").toString() : null;
            if(access_token != null){
                url = String.format(GET_CREATE_MENU_URL,access_token);
                JSONObject postJsonValue = new JSONObject();
                postJsonValue.put("type","view");
                postJsonValue.put("name","点击进入去冰商城");
                postJsonValue.put("url","http://www.icegone.cn");
                JSONObject postJson = new JSONObject();
                postJson.put("button",new JSONObject[]{postJsonValue});

                String postData = postJson.toJSONString();
                respData = HttpClientUtil.reqClient(url,"POST",postData);
                System.out.println("创建菜单结果：" + respData);
            }
        }
        return respData;
    }

    /**
     * 在code值已经存在的前提下，获取openID和access_Token
     * @param appid
     * @param code
     * @return
     */
    public static JSONObject getOpenIdAndaccessToken(String appid,String code){
        String url = String.format(WechatUtil.GET_ACCESS_TOKEN_OAUTH2_URL, appid, WechatUtil.getAppsecret(), code);
        String result = HttpClientUtil.reqClient(url, "GET", "");
        return JSONObject.parseObject(result);
    }

    /**
     * 根据accessToken和openid 获取用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    public static String getUserInfo(String accessToken,String openid){
        String getUserInfoUrl = String.format(WechatUtil.GET_USERINFO_UEL, accessToken, openid);
        String userInfo = HttpClientUtil.reqClient(getUserInfoUrl, "GET", "");
        return userInfo;
    }

    /**
     *   获取用户信息的第一步--获取code
     * @param redirectUri 重定向的url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getCode(String redirectUri) throws UnsupportedEncodingException {
        return String.format(WechatUtil.GET_CODE_OAUTH2_URL, WechatUtil.getAppID(),
                java.net.URLEncoder.encode(redirectUri, "utf-8"), WechatUtil.SCOPE_SNSAPI_USERINFO, 123);

    }

    public static JSONObject UploadMeida(String fileType,String filePath,String token) throws Exception{
        //返回结果
        String result=null;
        File file=new File(filePath);
        if(!file.exists()||!file.isFile()){
            throw new IOException("文件不存在");
        }
        //String token=WechatUtil.getToken();
        String urlString="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+token+"&type="+fileType;
        URL url=new URL(urlString);
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        //以POST方式提交表单
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //POST方式不能使用缓存
        conn.setUseCaches(false);
        //设置请求头信息
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        //设置边界
        String BOUNDARY="----------"+System.currentTimeMillis();
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        //请求正文信息
        //第一部分
        StringBuilder sb=new StringBuilder();
        //必须多两条道
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\"; filename=\"" + file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        System.out.println("sb:"+sb);
        //获得输出流
        OutputStream out=new DataOutputStream(conn.getOutputStream());
        //输出表头
        out.write(sb.toString().getBytes("UTF-8"));
        //文件正文部分
        //把文件以流的方式 推送道URL中
        DataInputStream din = null;
        try {
            din = new DataInputStream(new FileInputStream(file));
            int bytes=0;
            byte[] buffer=new byte[1024];
            while((bytes=din.read(buffer))!=-1){
                out.write(buffer,0,bytes);
            }
        }catch (Exception e) {
            throw e;
        }finally {
            if(din != null) {
                din.close();
            }
        }

        try {
            //结尾部分                                                 //定义数据最后分割线
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
            out.write(foot);
            out.flush();
        }catch (Exception e) {
            throw e;
        } finally {
            if(out != null) {
                out.close();
            }
        }

        if(HttpsURLConnection.HTTP_OK==conn.getResponseCode()){
            StringBuffer strbuffer=null;
            BufferedReader reader=null;
            try {
                strbuffer=new StringBuffer();
                reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String lineString=null;
                while((lineString=reader.readLine())!=null){
                    strbuffer.append(lineString);

                }
                if(result==null){
                    result=strbuffer.toString();
                }
            } catch (IOException e) {
                System.out.println("发送POST请求出现异常！"+e);
                e.printStackTrace();
            }finally{
                if(reader!=null){
                    reader.close();
                }
            }
        }
        JSONObject jsonObject=JSONObject.parseObject(result);
        return jsonObject;
    }

    public static void main(String[] args) {
        try {
            JSONObject jsonObject = WechatUtil.UploadMeida("voice", "D:/指纹录入.mp3", WechatUtil.getAccessToken());
            logger.info(jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","voice");
        jsonObject.put("offset","0");
        jsonObject.put("count","10");
        String format = String.format(WechatUtil.GET_VOICE_URL, WechatUtil.getAccessToken());
        String post = HttpClientUtil.reqClient(format, "POST", jsonObject.toJSONString());
        logger.info(post);*/
    }

    /**
     * 封装的方法：通过客服接口--发送消息
     * @param url      点击后跳转的地址
     * @param picurl   图片地址
     * @param openid   用户唯一标识
     */
   /* public static JSONObject sendImageToUser(String url, String picurl, String openid, Yywdb yywdb,String zjzl){
        //客服接口发送图片信息的url
        String sendImageUrl = String.format(WechatUtil.SENDIMAGE_URL, WechatUtil.getAccessToken());
        JSONObject sendJson = new JSONObject();
        JSONObject imageId = new JSONObject();
        JSONArray articles = new JSONArray();
        JSONObject imageJson = new JSONObject();
        imageJson.put("title",yywdb.getWdmc()+"现场实时照片");
        imageJson.put("description",
                "【预约提醒】请您于xxxx"+"，" +
                        "前往"+yywdb.getWdmc()+"，办理您所预约的业务："+zjzl+"，地址："+yywdb.getWddz()+"。");
        imageJson.put("url",url);
        imageJson.put("picurl",picurl);
        articles.add(imageJson);
        imageId.put("articles",articles);
        sendJson.put("touser",openid);
        sendJson.put("msgtype","news");
        sendJson.put("news",imageId);
        String post = HttpClientUtil.reqClient(sendImageUrl, "POST", sendJson.toJSONString());
        JSONObject jsonPost = JSONObject.parseObject(post);
        return jsonPost;
    }*/

    /**
     * 获取session中的微信用户openid（在app项目中使用）
     *
     * @return openid
     */
    public static String getSessionOpenid(HttpServletRequest request){
        Object openid = WebUtils.getSessionAttribute(request,"openid");
        if(openid != null){
            return openid.toString();
        }
        return null;
    }

    /**
     * 获取微信配置
     */
    public static Model getWechatSetting(HttpServletRequest request, Model model){
        String url = HttpUtil.getRootNotPort();
        String paramString = request.getQueryString();
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(paramString)) {
            url = url + "?" + paramString;
        }
        Map<String, String> jsSignatureMap = WechatUtil.getJsSignature(url);
        //微信配置
        model.addAttribute("url",jsSignatureMap.get("url"));
        model.addAttribute("noncestr",jsSignatureMap.get("noncestr"));
        model.addAttribute("signature",jsSignatureMap.get("signature"));
        model.addAttribute("timestamp",jsSignatureMap.get("timestamp"));
        model.addAttribute("appId",jsSignatureMap.get("appId"));
        return model;
    }


}
