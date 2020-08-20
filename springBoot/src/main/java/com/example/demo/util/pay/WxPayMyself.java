package com.example.demo.util.pay;

import com.example.demo.service.JsonConverter;
import lombok.extern.log4j.Log4j;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Log4j
public class WxPayMyself {


    @Resource
    private JsonConverter jsonConverter ;

    public void doPay(){
        Map<String, String> map = new HashMap<String, String>();
        String parAppid = "";
        String mchid = "";
        String parKey = "";
        String parMchid = "";
        String activityFlg = "";
        String activityInfo = "";
        String appid = "";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        map.put("appid", parAppid);
        map.put("mch_id", parMchid);
        map.put("sub_mch_id", mchid);
        map.put("device_info", "");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        map.put("nonce_str", uuid);
        map.put("body", String.valueOf("" + "-扫码付-" + "第三方订单号"));
        map.put("out_trade_no", uuid);
        if (!StringUtils.isEmpty(appid) && (!"null".equals(appid))) {
            map.put("sub_appid", appid);
        }

         map.put("goods_tag", activityInfo);

        BigDecimal money = new BigDecimal(100).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
        map.put("total_fee", String.valueOf(money.intValue()));
        String ip = getLocalIpAddress();
        map.put("spbill_create_ip", ip);
        map.put("auth_code", "120061098828009406");//付款码


        Map<String, Object> singleDisc = new HashMap<>();

            singleDisc.put("cost_price", String.valueOf(money.intValue()));
            List<Map<String, Object>> goodsDetailList = new ArrayList<Map<String, Object>>();

            // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail

            Map<String, Object> goodDetail = new HashMap<String, Object>();
            goodDetail.put("goods_id", "213");
            goodDetail.put("wxpay_goods_id", "123");
            goodDetail.put("goods_name", "苹果");
            goodDetail.put("quantity",10);
            goodDetail.put("price", 100);
            goodsDetailList.add(goodDetail);

            singleDisc.put("goods_detail", goodsDetailList);


        if (singleDisc != null && singleDisc.size() > 0) {
            map.put("detail", "<![CDATA[" + jsonConverter.toJson(singleDisc) + "]]>");
        }

        String data = "";
        try {
            data = WXPayUtil.generateSignedXml(map, parKey);
        } catch (Exception e) {
            log.info("付款码支付请求异常 :", e);
        }

        String result = "";
        try {
            WXPayRequest request ;
            log.info("付款码支付请求数据 :" + data);
            //result = request.requestWithCert("/pay/micropay", "", data, false);
        } catch (Exception e) {
        }

        stopWatch.stop();

        log.info("微信付款码支付请求返回所需时间 :" + stopWatch.getTotalTimeMillis());
    }


    /**
     * @Description: 根据网卡获取本机配置的IP地址
     * @Param: []
     * @return: java.lang.String
     * @Author: zhangchao
     * @Date: 2020/8/20 21:30
     */
    private static String getLocalIpAddress() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inetAddress.getHostAddress();
    }

}
