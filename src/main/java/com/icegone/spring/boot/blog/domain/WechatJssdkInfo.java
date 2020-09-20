package com.icegone.spring.boot.blog.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Icegone
 * @date 2019/6/1
 */
@Entity
@Table(name = "wechatinfo")
public class WechatJssdkInfo implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,length = 20)
    private Long id;

    @Column(name = "networktypehide",nullable = true,length = 20)
    private String networkTypeHide;

    @Column(name = "qrcoderesulthide",nullable = true,length = 255)
    private String QRCodeResultHide;

    @Column(name = "locationlatitudehide",nullable = true,length = 255)
    private String LocationLatitudeHide;

    @Column(name = "locationaccuracyhide",nullable = true,length = 255)
    private String LocationAccuracyHide;

    @Column(name = "localidrecordhide",nullable = true,length = 255)
    private String localIdRecordHide;

    @Column(name = "serveridrecordhide",nullable = true,length = 255)
    private String serverIdRecordHide;

    @Column(name = "afteruploadlocalidrecordhide",nullable = true,length = 255)
    private String afterUploadLocalIdRecordHide;

    @Column(name = "translatevoicehide",nullable = true,length = 255)
    private String translateVoiceHide;

    @Column(name = "imageonehide",nullable = true,length = 255)
    private String imageOneHide;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetworkTypeHide() {
        return networkTypeHide;
    }

    public void setNetworkTypeHide(String networkTypeHide) {
        this.networkTypeHide = networkTypeHide;
    }

    public String getQRCodeResultHide() {
        return QRCodeResultHide;
    }

    public void setQRCodeResultHide(String QRCodeResultHide) {
        this.QRCodeResultHide = QRCodeResultHide;
    }

    public String getLocationLatitudeHide() {
        return LocationLatitudeHide;
    }

    public void setLocationLatitudeHide(String locationLatitudeHide) {
        LocationLatitudeHide = locationLatitudeHide;
    }

    public String getLocationAccuracyHide() {
        return LocationAccuracyHide;
    }

    public void setLocationAccuracyHide(String locationAccuracyHide) {
        LocationAccuracyHide = locationAccuracyHide;
    }

    public String getLocalIdRecordHide() {
        return localIdRecordHide;
    }

    public void setLocalIdRecordHide(String localIdRecordHide) {
        this.localIdRecordHide = localIdRecordHide;
    }

    public String getServerIdRecordHide() {
        return serverIdRecordHide;
    }

    public void setServerIdRecordHide(String serverIdRecordHide) {
        this.serverIdRecordHide = serverIdRecordHide;
    }

    public String getAfterUploadLocalIdRecordHide() {
        return afterUploadLocalIdRecordHide;
    }

    public void setAfterUploadLocalIdRecordHide(String afterUploadLocalIdRecordHide) {
        this.afterUploadLocalIdRecordHide = afterUploadLocalIdRecordHide;
    }

    public String getTranslateVoiceHide() {
        return translateVoiceHide;
    }

    public void setTranslateVoiceHide(String translateVoiceHide) {
        this.translateVoiceHide = translateVoiceHide;
    }

    public String getImageOneHide() {
        return imageOneHide;
    }

    public void setImageOneHide(String imageOneHide) {
        this.imageOneHide = imageOneHide;
    }

    //JPA 规范
    public WechatJssdkInfo(){
    }
}
