package com.shop.enums;

import java.util.Date;

import lombok.Getter;

public interface StatusCode {
	
    Date getTimestamp();
    String getMessage();
    String getDetails();

}
