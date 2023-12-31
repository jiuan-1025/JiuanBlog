package com.jiuan.common.exception.user;

import com.jiuan.common.exception.BaseException;

/**
 * @className: UserException
 * @description: user exception
 * @author: Dimple
 * @date: 10/22/19
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
