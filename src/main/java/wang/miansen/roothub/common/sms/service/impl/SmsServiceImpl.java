package wang.miansen.roothub.common.sms.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import lombok.extern.slf4j.Slf4j;

import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.common.sms.service.SmsService;
import wang.miansen.roothub.common.util.Assert;
import wang.miansen.roothub.common.util.StringUtils;
import wang.miansen.roothub.modules.system.service.SystemConfigService;

/**
 * 短信服务接口实现类
 *
 * @author miansen.wang
 * @since 2021-04-27 20:37
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService, InitializingBean {

    @Autowired
    private SystemConfigService systemConfigService;

    private IAcsClient client;

    @Override
    public void sendCode(String mobile, String code) {
        Assert.notEmpty(mobile, "The mobile is empty");
        Assert.notEmpty(code, "The code is empty");

        try {
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", systemConfigService.getValue("sms_region_id"));
            request.putQueryParameter("PhoneNumbers", mobile);
            request.putQueryParameter("SignName", systemConfigService.getValue("sms_sign_name"));
            request.putQueryParameter("TemplateCode", systemConfigService.getValue("sms_template_code"));
            request.putQueryParameter("TemplateParam", String.format("{\"code\": \"%s\"}", code));
            CommonResponse response = client.getCommonResponse(request);
            log.info("sendCode response: {}", response.getData());
        } catch (ClientException e) {
            log.error("Exception in SmsService.sendCode", e);
            throw new BaseException("Exception in SmsService.sendCode", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String accessKeyId = systemConfigService.getValue("sms_access_key_id");
        String secret = systemConfigService.getValue("sms_access_key_secret");
        String regionId = systemConfigService.getValue("sms_region_id");
        String signName = systemConfigService.getValue("sms_sign_name");
        String templateCode = systemConfigService.getValue("sms_template_code");

        Assert.notEmpty(accessKeyId, "sms_access_key_id is empty");
        Assert.notEmpty(secret, "sms_access_key_secret is empty");
        Assert.notEmpty(regionId, "sms_region_id is empty");
        Assert.notEmpty(signName, "sms_sign_name is empty");
        Assert.notEmpty(templateCode, "sms_template_code is empty");

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        client = new DefaultAcsClient(profile);
    }
}
