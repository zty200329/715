package com.example.demo.form;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;


/**
 * @author: zty
 * @date 2019/10/12 下午7:52
 * @description:
 */
@Data
public class UserRegisterForm {
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String passwd;

    @ApiModelProperty("权限")
    private Integer role;
}
