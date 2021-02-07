package com.taro.exception;

import com.taro.constants.Constant;

/**
 * @Package: com.taro.tdevice.exception
 * @File: BusinessException.java
 * @Description: 系统自定义运行异常
 * @Author: tanquanlong
 * @Date: 2016年12月9日
 * @Copyright taro(c)2010-2016
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 5774039636574319620L;
	// 状态标识0为成功
	protected int status = Constant.STATUS_ERROR_GENERAL;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(int status, String message) {
		super(message);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
}
