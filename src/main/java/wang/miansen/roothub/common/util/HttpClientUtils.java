package wang.miansen.roothub.common.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONObject;

import wang.miansen.roothub.common.enums.BaseErrorCodeEnum;
import wang.miansen.roothub.common.exception.BaseException;

/**
 * 封装 HTTP 请求
 *
 * @author miansen.wang
 * @date 2021-05-02 16:11
 */
public abstract class HttpClientUtils {

    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * HTTP GET 请求
     *
     * @param url 请求地址
     * @param queryParams 请求参数
     * @return JSONObject
     */
    public static JSONObject get(String url, MultiValueMap<String, String> queryParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        url = UriComponentsBuilder.fromUriString(url).queryParams(queryParams).build().toUriString();
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new BaseException(BaseErrorCodeEnum.INTERFACE_SYSTEM_ERROR);
        }
        return exchange.getBody();
    }
}
