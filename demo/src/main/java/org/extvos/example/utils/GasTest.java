package org.extvos.example.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GasTest {
    private static final String serverUrl1 = "http://gascylinder.inodes.cn/platform/thirdParty/reportGasBottles";
    private static final String serverUrl2 = "http://localhost:8083/dev/example/test1";
    private static final String serverUrl3 = "http://localhost:9083/thirdParty/reportGasBottles";

    public static void httpPost(String token, List<Map<Object, Object>> data) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpRequest req = HttpUtil.createPost(serverUrl3 + "?access_token=" + token);
        req.body(objectMapper.writeValueAsString(data));
        HttpResponse resp = req.execute();
        System.out.println("Ret Status:> " + resp.getStatus());
        System.out.println("Ret Body:> " + resp.body());
    }

    public static void main(String[] args) {
        try {
            Map<Object, Object> m = new LinkedHashMap<>();
            m.put("EQP_BTYPE", "1");
            m.put("EQP_TYPE", "车⻋载");
            m.put("EQP_SORT", "液化天然⽓⽓瓶");
            m.put("EQP_VART", "15L⻋载⽓瓶");
            m.put("QR_COD", "82210518000015C");
            m.put("USE_COD", "562003002021LNG047");
            m.put("CAR_COD", "⽢C56710");
            m.put("RFID_COD", "RFID1131");
            m.put("PRO_NAME", "⽢肃省甘⽢");
            m.put("CIT_NAME", "兰州市");
            m.put("DIS_NAME", "红古区");
            m.put("BOT_STA", "1");
            m.put("MADE_DATE", "2021-05-17");
            m.put("FAC_COD", "气⽓瓶出⼚编号1");
            m.put("USE_UNT", "兰州液化石⽯油⽓有限公司");
            m.put("Manufacturer", "武汉中天特种设备有限公司");
            m.put("VAL_MAN", "阀门⻔制造⼚商1");
            List<Map<Object, Object>> bottles = new LinkedList<>();
            bottles.add(m);
            httpPost("05ea505cf0bbe4a6764ca71cdbb30985", bottles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
