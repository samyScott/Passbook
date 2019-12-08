package cn.edu.hdu.passbook.advice;

import cn.edu.hdu.passbook.bo.ErrorInf;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>全局异常处理</h1>
 * @author samy
 * @date 2019/12/1 14:57
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ErrorInf<String> errorHandler(HttpServletRequest request, Exception ex) {

        ErrorInf<String> info = new ErrorInf<>();
        info.setCode(ErrorInf.ERROR);
        info.setMessage(ex.getMessage());
        info.setData("Do Not Have Return Data");
        info.setUrl(request.getRequestURI());

        return info;
    }
}
