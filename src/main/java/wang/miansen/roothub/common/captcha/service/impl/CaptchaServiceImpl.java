package wang.miansen.roothub.common.captcha.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

import wang.miansen.roothub.common.captcha.dao.CaptchaDAO;
import wang.miansen.roothub.common.captcha.entity.CaptchaDO;
import wang.miansen.roothub.common.captcha.enums.CaptchaCodeTypeEnum;
import wang.miansen.roothub.common.captcha.enums.CaptchaRelTypeEnum;
import wang.miansen.roothub.common.captcha.service.CaptchaService;
import wang.miansen.roothub.common.dao.mapper.wrapper.query.QueryWrapper;
import wang.miansen.roothub.common.sms.service.SmsService;
import wang.miansen.roothub.common.util.DateUtils;
import wang.miansen.roothub.common.util.HttpClientUtils;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.system.service.SystemConfigService;


/**
 * 验证码 Service 接口实现类
 *
 * @author miansen.wang
 * @date 2021-05-02 15:37
 */
@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaDAO captchaDao;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private SmsService smsService;

    /**
     * 腾讯云验证码票据校验接口地址，参考文档：https://007.qq.com/java-access.html?ADTAG=acces.start
     */
    private static final String VERIFY_URI = "https://ssl.captcha.qq.com/ticket/verify";

    /**
     * 验证码 5 分钟后失效
     */
    private static final int EXPIRE_TIME = 5;

    @Override
    public String generateNumberCode(String captchaRel, CaptchaRelTypeEnum captchaRelType, CaptchaCodeTypeEnum captchaCodeType,
        int length) {
        log.info("invoke method CaptchaServiceImpl#generateNumberCode. captchaRel: {}, captchaRelType: {}, captchaCodeType: {}, length: {}",
            captchaRel, captchaRelType, captchaCodeType, length);
        String code = generateNumberCode0(length);
        CaptchaDO captchaDo = new CaptchaDO();
        captchaDo.setCaptchaRel(captchaRel);
        captchaDo.setCaptchaRelType(captchaRelType.getCaptchaRelTypeId().byteValue());
        captchaDo.setCaptchaCode(code);
        captchaDo.setCaptchaCodeType(captchaCodeType.getCaptchaCodeTypeId().byteValue());
        captchaDo.setExpireTime(DateUtils.getMinuteAfter(new Date(), EXPIRE_TIME));
        captchaDao.insertNotNull(captchaDo);
        return code;
    }

    @Override
    public void sendNumberCode(String numberCode, String mobile, String ticket, String randStr, String ip) {
        if (!verifyTicket(ticket, randStr, ip)) {
            throw new IllegalArgumentException("验证码错误");
        }
        smsService.sendCode(mobile, numberCode);
    }

    @Override
    public boolean verifyCode(String captchaRel, CaptchaRelTypeEnum captchaRelType, String captchaCode,
        CaptchaCodeTypeEnum captchaCodeType) {
        log.info("invoke method CaptchaServiceImpl#verifyCode. captchaRel: {}, captchaRelType: {}, captchaCode: {}, captchaCodeType: {}",
            captchaRel, captchaRelType, captchaCode, captchaCodeType);
        QueryWrapper<CaptchaDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(CaptchaDO::getCaptchaRel, captchaRel)
            .eq(CaptchaDO::getCaptchaRelType, captchaRelType.getCaptchaRelTypeId())
            .eq(CaptchaDO::getCaptchaCode, captchaCode)
            .eq(CaptchaDO::getCaptchaCodeType, captchaCodeType.getCaptchaCodeTypeId())
            .eq(CaptchaDO::getIsUsed, Boolean.FALSE)
            .eq(CaptchaDO::getIsDeleted, Boolean.FALSE)
            .gt(CaptchaDO::getExpireTime, new Date());
        CaptchaDO captchaDo = captchaDao.selectOne(wrapper);
        log.info("captchaDo: {}", captchaDo);
        if (captchaDo != null) {
            captchaDo.setIsUsed(Boolean.TRUE);
            captchaDo.setUpdateTime(new Date());
            captchaDao.updateById(captchaDo);
            log.info("Update captcha used. id: {}", captchaDo.getCaptchaId());
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyTicket(String ticket, String rand, String userIp) {
        log.info("invoke method CaptchaServiceImpl#verifyTicket. ticket: {}, rand: {}, userIp: {}", ticket, rand, userIp);
        String appId = systemConfigService.getValue("captcha_app_id");
        if (StringUtils.isEmpty(appId)) {
            throw new IllegalArgumentException("captcha_app_id is empty.");
        }
        String appSecretKey = systemConfigService.getValue("captcha_app_secret_key");
        if (StringUtils.isEmpty(appSecretKey)) {
            throw new IllegalArgumentException("captcha_app_secret_key is empty.");
        }
        try {
            MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
            queryParams.add("aid", appId);
            queryParams.add("AppSecretKey", appSecretKey);
            queryParams.add("Ticket", URLEncoder.encode(ticket, "UTF-8"));
            queryParams.add("Randstr", URLEncoder.encode(rand, "UTF-8"));
            queryParams.add("UserIP", URLEncoder.encode(userIp, "UTF-8"));
            JSONObject response = HttpClientUtils.get(VERIFY_URI, queryParams);
            log.info("response: {}", response);
            Integer code = response.getInteger("response");
            return code == 1;
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException in CaptchaService.verifyTicket", e);
        } catch (Exception e) {
            log.error("Exception in CaptchaService.verifyTicket", e);
        }
        return false;
    }

    private String generateNumberCode0(int length) {
        String code = StringUtils.randomNumber(length);
        QueryWrapper<CaptchaDO> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(CaptchaDO::getCaptchaCode, code)
            .eq(CaptchaDO::getIsUsed, Boolean.FALSE)
            .eq(CaptchaDO::getIsDeleted, Boolean.FALSE)
            .gt(CaptchaDO::getExpireTime, new Date());
        CaptchaDO captchaDo = captchaDao.selectOne(wrapper);
        if (captchaDo != null) {
            return generateNumberCode0(length);
        }
        log.info("CaptchaServiceImpl#generateNumberCode0 result: {}", code);
        return code;
    }
}
