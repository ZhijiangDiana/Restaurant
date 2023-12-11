package com.restaurant.restaurant.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.restaurant.restaurant.pojo.R;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Base64;

/**
 * 考虑到某人在JavaIO方面有知识漏洞，同时也简化重复的操作
 * 现将Controller层数据交互功能封装成utils供大家使用
 * <p>
 * 今后可能有更多功能，未完待续。。。
 */
public class FrontEndUtils {
    // TODO: 2023/12/10 未全面测试，需要测试 
    private FrontEndUtils(){}
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 将json格式的请求体转成JSONObject，可以使用getXxx来获取参数
     * @param req
     * @return
     * @throws IOException
     */
    public static JSONObject bodyToJson(HttpServletRequest req) throws IOException {
        BufferedReader reqBr = req.getReader();
        String reqText = IOUtils.toString(reqBr);
        IOUtils.closeQuietly(reqBr);

        return JSON.parseObject(reqText);
    }

    /**
     * 将返回对象及返回参数封装成json
     * 要求除了Object以外其他String参数全部不为null，不填的参数用空字符串代替
     * @param message
     * @param flag
     * @param obj
     * @return 符合标准的返回数据
     */
    public static String objectToBody(String message, String flag, Object obj) {
        R respObj;
        if (obj == null)
            respObj = new R(message, flag, "");
        else
            respObj = new R(message, flag, obj);
        return JSON.toJSONString(respObj);// 大坑，fastjson乱用转义字符!!!.replace("\\\"", "\"")
    }

    /**
     * 将Base64（一种用字符串表示二进制流的方式）数据转为byte数组的函数
     * 通常用于接收前端数据
     * @param b64File
     * @return
     */
    public static byte[] b64ToBytes(String b64File) {
        return decoder.decode(b64File);
    }

    /**
     * 将byte数组转为Base64数据的函数
     * 通常用于给前端发送数据
     * @param byteFile
     * @return
     */
    public static String bytesToB64(byte[] byteFile) {
        return encoder.encodeToString(byteFile);
    }
}
