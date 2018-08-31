package com.zilanghuo.utils;

        import cn.hutool.core.bean.BeanUtil;
        import cn.hutool.core.codec.Base64;
        import org.springframework.util.StringUtils;

        import java.security.KeyFactory;
        import java.security.PrivateKey;
        import java.security.PublicKey;
        import java.security.Signature;
        import java.security.spec.PKCS8EncodedKeySpec;
        import java.security.spec.X509EncodedKeySpec;
        import java.util.Iterator;
        import java.util.Map;
        import java.util.TreeMap;

/**
 * 1.商户的私钥初始化
 * 2.商户验签的公钥初始化
 * 3.根据商户号加密数据
 * 4.验签富友返回数据
 *
 * @author 2441240397
 */

public class SecurityUtils {
    /**
     * 私钥 ,富友分配给商户的
     */
    public static PrivateKey privateKey;
    /**
     * 公钥，富友的公钥
     */
    public static PublicKey publicKey;

    private static String base64PublicKeyValue = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcp9NU29EfqPReJLGBS0WZwCKxORrc4IQpKbup1cF4KzQnpMCwcJXF9KW1vJ/ZzOMwAlGfhq2V96MGPOO6T/Zkesasjdmy19wnOdzDxGXu2pEMbFMDOonYxf1m5/VNs2+TZ18eyW585XefXoNlYCzg6RJmXK0fZ1UPAU9ZxgocEQIDAQAB";

    private static String base64PrivateKeyValue = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAO2u+9vkuQGYwe2Yw0XFXRdhCkvwY4328H2STJjeW2LGDJqYYQVbpw1CNwJ0hKKcpk1/APENbdv84RP7x3YEkQVNoB0uSj8qnpUsnuyBdxLdToohikvOrNRWgQx/ZvgFE+rWjka9wVaKqLUbUWxpq9GiUAPFa78kYOABD8dIMtg9AgMBAAECgYEAgpNzQiaxjLMDNyiJfrcioUlqgrWZu9BB5nqNIh5mTilHm1bDVlI3wAz0c6DXjQ5KPqDbP5KFHCoc7QGRXsC7egNBX9kNtL7ZCuYw78pE5sNM4+885fgoqaBCbnc+PxgyAqQ+ZIO5u6QKXQpEoe7PpvxCVBAGyn/1klaQVidUivECQQD87PnV05v8ibOv0N6cSpEZ8s/mdFVDSw0sFBdxMseFGY/WjDl1g9ZQCuwjrcT5S/mnYgb6MzRJn+s0rfCFlImLAkEA8JKURMVg6GqIleQq4e03uqEZ6AgErBlh2e+1/T9vgij6n/ueZysamHydZAupk3Wsfn1bkmdA4zqOCf7UZueOVwJAHDwIF8qrmyF0IahbcW8Ri6gDdWJ/MifqrIUBqO1WQJF98SFuOKQjBIRzn/gCCSJmGD1lMgENUTq88wCH3SGbyQJBAJzEuDAUe3EZM0aSOEufvQg2QV6OExVfOP+/ENYmB3FHaQLmAjRyx1MFKb9vRiMctLp80DaYaJVqq/Lhh+JDFOMCQQCXrBhjTx4KfLzfUhOVzm5D8w5sAn9Sg1jDeMwe8tyiyUBbbkw+k9qK0YLOfnwKuC3MNI5URjaKyLzilPDZZkrs";


    static {
        try {
            java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
    }

    /**
     * init:初始化私钥
     */
    public static void initPrivateKey(String base64PrivateKey) {
        try {
            if (privateKey == null) {
                privateKey = getPrivateKey(base64PrivateKey);
            }
        } catch (Exception e) {
            System.out.println("SecurityUtils初始化失败" + e.getMessage());
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
    }

    /**
     * 初始化公钥
     */
    public static void initPublicKey(String base64PublicKey) {
        try {
            if (publicKey == null) {
                publicKey = getPublicKey(base64PublicKey);
            }
        } catch (Exception e) {
            System.out.println("SecurityUtils初始化失败" + e.getMessage());
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
    }

    /**
     * map 排序加密
     *
     * @param bean
     * @return
     */
    public static String signByBean(Object bean) {
        Map<String, Object> map = BeanUtil.beanToMap(bean);
        TreeMap<String, Object> treeMap = new TreeMap();
        Iterator<Map.Entry<String, Object>> iteratorMap = map.entrySet().iterator();
        while (iteratorMap.hasNext()) {
            Map.Entry<String, Object> next = iteratorMap.next();
            treeMap.put(next.getKey(), next.getValue());
        }
        return sign(getInputStr(treeMap), base64PrivateKeyValue);
    }

    /**
     * map 排序加密
     *
     * @param treeMap
     * @return
     */
    public static Boolean verifyByTreeMap(TreeMap<String, Object> treeMap, String signValue) {
        return verifySign(getInputStr(treeMap), signValue, base64PublicKeyValue);
    }

    private static String getInputStr(TreeMap<String, Object> treeMap) {
        Iterator<Map.Entry<String, Object>> iterator = treeMap.entrySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            if (!StringUtils.isEmpty(next.getKey()) && !StringUtils.isEmpty(next.getValue())
                    && !"signature".equals(next.getKey())) {
                sb.append(next.getValue()).append("|");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 对传入字符串进行签名
     *
     * @param inputStr
     * @return
     * @
     */
    public static String sign(String inputStr, String base64PrivateKey) {
        String result = null;
        try {
            if (privateKey == null) {
                //初始化
                initPrivateKey(base64PrivateKey);
            }
            byte[] tByte;
            Signature signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initSign(privateKey);
            signature.update(inputStr.getBytes("UTF-8"));
            tByte = signature.sign();
            result = Base64.encode(tByte);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
        return result;
    }

    /**
     * 对富友返回的数据进行验签
     *
     * @param src       返回数据明文
     * @param signValue 返回数据签名
     * @return
     */
    public static boolean verifySign(String src, String signValue, String base64PublicKey) {
        boolean bool = false;
        try {
            if (publicKey == null) {
                initPublicKey(base64PublicKey);
            }
            Signature signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initVerify(publicKey);
            signature.update(src.getBytes("UTF-8"));
            byte[] decode = Base64.decode(signValue);
            System.out.println(new String(decode));
            bool = signature.verify(decode);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
        return bool;
    }

    private static PrivateKey getPrivateKey(String base64PrivateKey) {
        KeyFactory kf;
        PrivateKey privateKey = null;
        try {
            kf = KeyFactory.getInstance("RSA", "BC");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(base64PrivateKey));
            privateKey = kf.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
        return privateKey;
    }

    private static PublicKey getPublicKey(String base64PublicKey) {
        KeyFactory kf;
        PublicKey publickey = null;
        try {
            kf = KeyFactory.getInstance("RSA", "BC");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(base64PublicKey));
            publickey = kf.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("密钥初始化失败");
        }
        return publickey;
    }


    public static void main(String[] args) {
        String preSign = "Z+sKSMg0Dn93Lw9obEuLtDsiHh35RBwFpq4tVIswDTOvEKE85HkA7ViBmTPYNwDeXDFoUjOiUkp35wL/BdBFr/0eeWNULonJ8JMlibBrTjzPL41LjwyxhQPr/h1rFcM+Kx/vvQj/eSsFhFLv5FCxcNkc5hprNkkQIxThoUYeu7o=";
        String str = "1000|0002900F0352200|20180816|20180816115501000073147|18525863602|";
        String sign = sign(str, base64PrivateKeyValue);
        System.out.println(verifySign("0002900F0352200|20180816134504900073148|0000|成功", "oWqBaDw/5044t88pAjAlKhjXglRM67OTXGSOuwzfQPlKrtXHg85ZtswITYYeAA+O6lzsUGp4ZA9JYnCn0w1XdaumlNqRxC5l5yS5U/kHrxaKFC625VX6ZnogPOBgyUq6pG28BwcY5KaMm/lSS0L7AIuDy95cyhyL5+xhK5f0OJI=", base64PublicKeyValue));
        System.out.println(verifySign("1000|0002900F0352200|20180816|20180816134504900073148|18525863602|", "IJvh8ToqonKZ6tC4sd4NnusT9aIk2nW723WwQWExIN3UE+hnY7hNHoRU6Kqm51Mvl+fz6RpMORZu1v6saPTB0nS47gZnXzsIGxeVhDWu4uKQIeoQXSVc/8zFfz+llzUNkZzwKyPT7cUX0MlbUiNKkmQAnuXleJQBPnGiG8DZbB0=", base64PublicKeyValue));
        System.out.println(verifySign("1000|0002900F0352200|20180816|20180816134504900073148|18525863602|", "IJvh8ToqonKZ6tC4sd4NnusT9aIk2nW723WwQWExIN3UE+hnY7hNHoRU6Kqm51Mvl+fz6RpMORZu1v6saPTB0nS47gZnXzsIGxeVhDWu4uKQIeoQXSVc/8zFfz+llzUNkZzwKyPT7cUX0MlbUiNKkmQAnuXleJQBPnGiG8DZbB0=", base64PublicKeyValue));
        System.out.println(sign);
    }

}
