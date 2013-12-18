package com.argo.core.web.Interceptor;

import com.argo.core.base.BaseUser;
import com.argo.core.configuration.SiteConfig;
import com.argo.core.exception.PermissionDeniedException;
import com.argo.core.exception.UserNotAuthorizationException;
import com.argo.core.security.AuthorizationService;
import com.argo.core.service.factory.ServiceLocator;
import com.argo.core.utils.IpUtil;
import com.argo.core.web.WebContext;
import com.argo.core.web.session.SessionCookieHolder;
import com.argo.core.web.session.SessionUserHolder;
import com.google.common.io.BaseEncoding;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yamingdeng
 * Date: 13-11-17
 * Time: 上午9:56
 */
public class HandlerPrepareAdapter extends HandlerInterceptorAdapter {

    private final String cookieId = "_after";

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        if (logger.isDebugEnabled()){
            logger.debug("preHandle incoming request.");
        }

        WebContext.getContext().setRequestIp(IpUtil.getIpAddress(request));
        Map app = SiteConfig.instance.getApp();

        AuthorizationService authorizationService = null;
        String loginUrl = ObjectUtils.toString(app.get("login"));
        String currentUid = null;
        try {
            currentUid = SessionCookieHolder.getCurrentUID(request);
        } catch (UserNotAuthorizationException e) {
            if (logger.isDebugEnabled()){
                logger.debug("preHandle currentUid="+currentUid, e);
            }
        }
        if (StringUtils.isNotBlank(currentUid)){
            // 若是远程服务，则需要配置bean.authorizationService的实现
            // 若是本地服务，不需要配置
            if (logger.isDebugEnabled()){
                logger.debug("preHandle currentUid="+currentUid);
            }
            authorizationService = ServiceLocator.instance.get(AuthorizationService.class);
            if (authorizationService != null){
                try{
                    BaseUser user = authorizationService.verifyCookie(currentUid);
                    SessionUserHolder.set(user);
                    if (logger.isDebugEnabled()){
                        logger.debug("preHandle verifyCookie is OK. BaseUser=" + user.getUserName());
                    }
                    String lastAccessUrl = this.getLastAccessUrl(request);
                    if (lastAccessUrl != null){
                        response.sendRedirect(lastAccessUrl);
                        return false;
                    }
                }catch (UserNotAuthorizationException ex){
                    saveLastAccessUrl(request, response);
                    response.sendRedirect(loginUrl);
                    return false;
                }
            }
        }
        if (logger.isDebugEnabled()){
            logger.debug("preHandle currentUid="+currentUid);
        }
        if (authorizationService == null){
            authorizationService = ServiceLocator.instance.get(AuthorizationService.class);
        }
        if (authorizationService != null){
            try {
                authorizationService.verifyAccess(request.getRequestURI());
                if (logger.isDebugEnabled()){
                    logger.debug("preHandle verifyAccess is OK.");
                }
            } catch (PermissionDeniedException e) {
                logger.warn("You do not have permission to access. " + request.getRequestURI());
                String deniedUrl = (String)app.get("denied");
                response.sendRedirect(deniedUrl);
                return false;
            }
        }
        return true;
    }

    private void saveLastAccessUrl(HttpServletRequest request, HttpServletResponse response){
        String lastAccessUrl = request.getRequestURL() + "?" + request.getQueryString();
        lastAccessUrl = BaseEncoding.base64Url().encode(lastAccessUrl.getBytes());
        SessionCookieHolder.setCookie(response, cookieId, lastAccessUrl, 3600*8);
    }
    private String getLastAccessUrl(HttpServletRequest request){
        Cookie cookie = SessionCookieHolder.getCookie(request, cookieId);
        if (cookie == null){
            return null;
        }
        byte[] lastAccessUrl = BaseEncoding.base64Url().decode(cookie.getValue());
        return new String(lastAccessUrl);
    }
}
