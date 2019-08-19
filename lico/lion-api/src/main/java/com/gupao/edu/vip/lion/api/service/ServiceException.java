package com.gupao.edu.vip.lion.api.service;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-05
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
