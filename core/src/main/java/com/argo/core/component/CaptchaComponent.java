package com.argo.core.component;

import com.argo.core.web.session.SessionCookieHolder;
import com.github.cage.Cage;
import com.github.cage.token.RandomTokenGenerator;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Yaming
 * Date: 2014/12/23
 * Time: 14:51
 */
public class CaptchaComponent {

    private static Cage cage = null;
    static {
        RandomTokenGenerator generator = new RandomTokenGenerator(new Random(), 4);
        cage = new Cage(null, null, null, null, Cage.DEFAULT_COMPRESS_RATIO, generator, null);
    }

    /**
     * 读取Cookie Token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request){
        Cookie cookie = SessionCookieHolder.getCookie(request, "c");
        if (cookie != null){
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 校验验证码
     * @param request
     * @param token
     * @return
     */
    public static boolean verifyToken(HttpServletRequest request, String token){
        String token0 = getToken(request);
        if (token0 == null){
            return false;
        }
        token = DigestUtils.shaHex(token);
        return token0.equalsIgnoreCase(token);
    }

    /**
     * Generates a captcha token and stores it in the session.
     *
     * @param response
     *            where to store the captcha.
     */
    public static String generateToken(HttpServletResponse response) {
        String token = cage.getTokenGenerator().next();
        String token0 = DigestUtils.shaHex(token);
        SessionCookieHolder.setCookie(response, "c", token0);
        return token;
    }

    public static void draw(String token, OutputStream outputStream) throws IOException {
        cage.draw(token, outputStream);
    }

    public static String getFormat(){
        return cage.getFormat();
    }
}
