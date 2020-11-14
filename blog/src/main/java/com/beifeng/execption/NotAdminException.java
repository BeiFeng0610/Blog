package com.beifeng.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/10/26 16:55
 */

/*不是管理员异常*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotAdminException extends RuntimeException{
    public NotAdminException() {
        super();
    }

    public NotAdminException(String message) {
        super(message);
    }

    public NotAdminException(String message, Throwable cause) {
        super(message, cause);
    }

}
