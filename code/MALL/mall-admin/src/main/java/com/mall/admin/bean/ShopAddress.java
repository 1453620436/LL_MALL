package com.mall.admin.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户收货地址
 * Created by macro on 2019/8/27.
 */
@Getter
@Setter
public class ShopAddress implements Serializable {
    @ApiModelProperty(value = "主键ID",required = true)
    @NotEmpty(message = "主键ID不能为空")
    private Long id;

    @ApiModelProperty(value = "收货人")
    @NotEmpty(message = "收货人不能为空")
    private String addressee;

    @ApiModelProperty(value = "收货人联系电话")
    @NotEmpty(message = "收货人联系电话不能为空")
    private String telethone;

    @ApiModelProperty(value = "收货地址所属账号")
    private String idCard;

    @ApiModelProperty(value = "邮编")
    private Integer zipCode;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "所属城市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;

    @ApiModelProperty(value = "默认收货地址")
    private String defaultAddress;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", addressee=").append(addressee);
        sb.append(", telethone=").append(telethone);
        sb.append(", idCard=").append(idCard);
        sb.append(", zipCode=").append(zipCode);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", area=").append(area);
        sb.append(", address=").append(address);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", defaultAddress").append(defaultAddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}