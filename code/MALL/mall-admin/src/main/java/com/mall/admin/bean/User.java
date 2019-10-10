package com.mall.admin.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 用户参数
 *Created by macro on 2019/8/21.
 */
@Getter
@Setter
public class User {
    @ApiModelProperty(value = "证件号",required = true)
    @NotEmpty(message = "身份证件号不能为空")
    private String idCard;

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    private  String  password;

    @ApiModelProperty(value = "联系电话")
    @NotEmpty(message = "联系电话不能为空")
    private  String  telethone;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱不合法")
    private  String email;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "性别")
    private  String sex;

    @ApiModelProperty(value = "真实姓名")
    private String realname;
}
