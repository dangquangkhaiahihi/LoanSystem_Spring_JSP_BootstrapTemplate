package com.example.demo.service.dto;

import lombok.Data;

@Data
public class ChangePassDto {
    private String oldPass;
    private String newPass;
    private String reNewPass;

    public void trimAll(){
        this.oldPass = this.oldPass.trim();
        this.newPass = this.newPass.trim();
        this.reNewPass = this.reNewPass.trim();
    }
}
