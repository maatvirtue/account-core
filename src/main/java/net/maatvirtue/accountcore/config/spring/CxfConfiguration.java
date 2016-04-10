package net.maatvirtue.accountcore.config.spring;

import org.apache.cxf.jaxrs.spring.SpringComponentScanServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringComponentScanServer.class)
public class CxfConfiguration
{
	//
}
