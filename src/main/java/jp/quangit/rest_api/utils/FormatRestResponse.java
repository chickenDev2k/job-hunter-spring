package jp.quangit.rest_api.utils;

import jakarta.servlet.http.HttpServletResponse;
import jp.quangit.rest_api.domain.RestResponse;
import jp.quangit.rest_api.utils.annotation.ApiMessage;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = servletResponse.getStatus();

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(status);
        if (body instanceof String) {
            return body;
        }
        if (status >= 400) {
            // // case error
            // // res.setError("call api failed");
            // res.setMessage("tai khoan da ton tai");
            return body;

        } else {
            // case success
            res.setData(body);
            ApiMessage message = returnType.getMethodAnnotation(ApiMessage.class);
            res.setMessage(message != null ? message.value() : "Call Api Success");
        }
        return res;
    }
}
