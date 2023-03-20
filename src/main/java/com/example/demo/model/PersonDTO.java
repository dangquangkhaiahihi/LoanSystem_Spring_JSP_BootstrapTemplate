package com.example.demo.model;

import com.example.demo.common.StringUtils;
import lombok.Data;

@Data
public class PersonDTO {
    private Long id;

    private String name = "";

    private String address = "";

    private String phone = "";

    private String email = "";

    private Long totalAmount = 0L;

    private String createdDateStr;

    private String lastModifiedDateStr;

    //ADD
    private String namePersonAdd;

    private String addressPersonAdd;

    private String phonePersonAdd;

    private String emailPersonAdd;

    //EDIT
    private String namePersonEdit;

    private String addressPersonEdit;

    private String phonePersonEdit;

    private String emailPersonEdit;

    public void validateRequestAdd () throws Exception{
        if(StringUtils.isEmpty(this.namePersonAdd) || StringUtils.isEmpty(this.phonePersonAdd)){
            throw new Exception("Vui lòng không bỏ trống các trường bắt buộc.");
        }
        this.name = this.namePersonAdd;
        this.phone = this.phonePersonAdd;
        this.address = this.addressPersonAdd;
        this.email = this.addressPersonAdd;
    }

    public void validateRequestEdit () throws Exception{
        if(StringUtils.isEmpty(this.namePersonEdit) || StringUtils.isEmpty(this.phonePersonEdit)){
            throw new Exception("Vui lòng không bỏ trống các trường bắt buộc.");
        }
        this.name = this.namePersonEdit;
        this.phone = this.phonePersonEdit;
        this.address = this.addressPersonEdit;
        this.email = this.addressPersonEdit;
    }
}
