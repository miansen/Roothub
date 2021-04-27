package wang.miansen.roothub.common.sms.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import lombok.extern.slf4j.Slf4j;

import wang.miansen.roothub.common.exception.BaseException;
import wang.miansen.roothub.common.sms.service.SmsService;
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
        if (StringUtils.isEmpty(mobile)) {
            throw new IllegalArgumentException("mobile is empty.");
        }
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code is empty.");
        }

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
            client.getCommonResponse(request);
        } catch (ClientException e) {
            log.error("Exception in SmsService.sendCode", e);
            throw new BaseException("Exception in SmsService.sendCode", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String accessKeyId = systemConfigService.getValue("sms_access_key_id");
        if (StringUtils.isEmpty(accessKeyId)) {
            throw new IllegalArgumentException("sms_access_key_id is empty.");
        }
        String secret = systemConfigService.getValue("sms_access_key_secret");
        if (StringUtils.isEmpty(secret)) {
            throw new IllegalArgumentException("sms_access_key_secret is empty.");
        }
        String regionId = systemConfigService.getValue("sms_region_id");
        if (StringUtils.isEmpty(regionId)) {
            throw new IllegalArgumentException("sms_region_id is empty.");
        }
        String signName = systemConfigService.getValue("sms_sign_name");
        if (StringUtils.isEmpty(signName)) {
            throw new IllegalArgumentException("sms_sign_name is empty.");
        }
        String templateCode = systemConfigService.getValue("sms_template_code");
        if (StringUtils.isEmpty(templateCode)) {
            throw new IllegalArgumentException("sms_template_code is empty.");
        }

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        client = new DefaultAcsClient(profile);
    }
}
