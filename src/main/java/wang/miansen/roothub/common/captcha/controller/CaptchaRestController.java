package wang.miansen.roothub.common.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.miansen.roothub.common.captcha.service.CaptchaService;

/**
 * Captcha Controller
 *
 * @author miansen.wang
 * @date 2021-05-09 17:49
 */
@RestController
@RequestMapping("/api/captcha")
public class CaptchaRestController {

    @Autowired
    private CaptchaService captchaService;



}
