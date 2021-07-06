package com.genx.base.techsupport.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import lombok.Data;

import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * @author SD
 * @date 2021/06/29
 */
@Data
public class TechSupport {

	@Id
	private String id;
	
	private String uid;
	

    private String customerTitle;

    private String address;

    private String tel;

    private List<String> emails;

    private String authorizedPerson;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private Date requestDate;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private Date serviceStartDate;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private Date serviceEndDate;

    private String serviceType;

    private String scope;

    private String serviceDefinition;

    private String usedMaterial;

    private boolean contracted;

    private String serviceDescription;

    private long duration;

    private long pricing;
	
}