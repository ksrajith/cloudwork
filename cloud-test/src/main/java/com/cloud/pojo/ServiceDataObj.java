package com.cloud.pojo;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.annotation.RowNumber;
import lombok.Getter;

@Getter
public class ServiceDataObj {
    @RowNumber
    private int rowNumber;

    @Column(index=0, name="TEST_CASE_NAME")
    private String testCaseName;
    @Column(index=1, name="TEST_CASE_ID")
    private String testcaseId;

    @Column(index=2, name="API_NAME")
    private String apiName;

    @Column(index=3, name="URL")
    private String urlSuffix;
    @Column(index=4, name="REQUEST")
    private String request;
    @Column(index=5, name="RESPONSE")
    private String response;

    @Column(index=6, name="RESPONSECODE")
    private int responseCode;

}
