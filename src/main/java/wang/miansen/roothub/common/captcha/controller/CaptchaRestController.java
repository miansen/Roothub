package wang.miansen.roothub.common.captcha.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import wang.miansen.roothub.common.beans.BaseResult;
import wang.miansen.roothub.common.captcha.enums.CaptchaCodeTypeEnum;
import wang.miansen.roothub.common.captcha.enums.CaptchaRelTypeEnum;
import wang.miansen.roothub.common.captcha.param.CaptchaSendInput;
import wang.miansen.roothub.common.captcha.service.CaptchaService;
import wang.miansen.roothub.common.util.IpUtils;

/**
 * Captcha Rest Controller
 *
 * @author miansen.wang
 * @date 2021-05-09 17:49
 */
@Slf4j
@RestController
@RequestMapping("/api/captcha")
public class CaptchaRestController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 发送短信验证码
     *
     * @param input 参数
     * @param request HTTP 请求
     * @return BaseResult
     */
    @RequestMapping(value = "/sms/send", method = RequestMethod.POST)
    public BaseResult smsSend(@RequestBody @Valid CaptchaSendInput input, HttpServletRequest request) {
        String numberCode = captchaService.generateNumberCode(input.getMobile(), CaptchaRelTypeEnum.MOBILE,
            CaptchaCodeTypeEnum.valueOf(input.getType()), 4);
        captchaService.sendNumberCode(numberCode, input.getMobile(), input.getTicket(), input.getRandStr(), IpUtils.getIpAddr(request));
        return BaseResult.success();
    }

}
