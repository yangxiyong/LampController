package com.yj.intranet.lampcontroller.web.util;

import com.yj.core.crypto.HMAC;
import com.yj.core.util.EncodingUtils;

/**
 * @author mort
 */
public class HashUtil {
    private static final String SECRET_KEY = "comyj+secret+key+for+intranet+only";

    public static String algorithmMD5(String plainText) {
        HMAC hmac = getHMAC(HMAC.Hash.MD5);
        return EncodingUtils.base64(hmac.digest(plainText));
    }

    public static String algorithmSHA256(String plainText) {
        HMAC hmac = getHMAC(HMAC.Hash.SHA256);
        return EncodingUtils.base64(hmac.digest(plainText));
    }

    private static final HMAC getHMAC(HMAC.Hash algorithm) {
        HMAC hmac = new HMAC();
        hmac.setHash(algorithm);
        hmac.setSecretKey(EncodingUtils.decodeBase64(SECRET_KEY));
        return hmac;
    }
}
