package com.cms.common;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Data
@Component
@SessionScope
public class SessionInfo {

	private String user;
	private String menuIndex;

}
