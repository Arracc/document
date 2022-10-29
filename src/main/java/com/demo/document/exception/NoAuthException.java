package com.demo.document.exception;

/**
 * @ClassName NoAuthException
 * @Date 2022/10/28 22:20
 * @Version 1.0
 */
public class NoAuthException extends Exception {

    private static final long serialVersionUID = 621588961001950120L;

    public NoAuthException(String message) {
        super(message);
    }

}
