package com.genx.base.user.model;

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
 * @date 2021/07/06
 */
@Data
public class User {

	@Id
	private String id;
	
	private String uid;
	

    private String firstname;

    private String lastname;
	
}