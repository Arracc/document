package com.demo.document.exception;

/**
 * @ClassName BussinessException
 * @Date 2022/10/28 22:33
 * @Version 1.0
 */
public class BussinessException extends Exception {

    private static final long serialVersionUID = 275155160519728359L;

    public BussinessException(String message) {
        super(message);
    }

}
