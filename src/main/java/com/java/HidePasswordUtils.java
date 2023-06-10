package com.java;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HidePasswordUtils {

    public static String hidePassword(Object obj) {
        try {
            if(ObjectUtil.isNull(obj)) {
                return "";
            }
            String text = String.valueOf(obj);
            if(StringUtils.isBlank(text)) {
                return text;
            }
            Map<String, String> map = new HashMap<String, String>(){{
                put(" --password (\\S+)\\s", " --password ");

                put("--set passwd=(\\S+)\\s", "--set passwd=");
                put("--set 'passwd=(\\S+)\\s", "--set 'passwd='");

                put("--set rootPassword=(\\S+)\\s", "--set rootPassword=");
                put("--set 'rootPassword=(\\S+)\\s", "--set 'rootPassword=");

                put("--set tenants.secrets.secretKey=(\\S+)\\s", "--set tenants.secrets.secretKey=");
                put("--set 'tenants.secrets.secretKey=(\\S+)\\s", "--set 'tenants.secrets.secretKey=");

                put("-p 6379 -a\\s([^\"]\\S+)\"", "-p 6379 -a ");
//            put("-p 6379 -a (\\S+)\\s", "-p 6379 -a ");
//            put("-p 6379 -a\\s([^\"]\\S+)\"", "-p 6379 -a ");
            }};

            for(Map.Entry<String, String> m: map.entrySet()) {
                String p = m.getKey();
                Pattern pp = Pattern.compile(p);
                Matcher matcher = pp.matcher(text);
                if(matcher.find()) {
                    String password = matcher.group(1);
                    System.out.println("***********测试打印的数据：" + m.getKey() + "********" + m.getValue() + "*************" + password);
                    if(m.getValue().contains("'")) {
                        String replace = m.getValue().replace("'", "");
                        String sss = m.getValue() + password;

                        text = text.replaceAll(m.getValue() + password, replace+ "******");
                    } else {
                        text = text.replaceAll(m.getValue() + password, m.getValue()+ "******");
                    }
                }
            }
            return text;
        } catch (Exception e) {
//            log.error("隐藏密码出现异常", e);
        }
        return "";
    }
}
