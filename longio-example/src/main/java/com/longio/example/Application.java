/**

The MIT License (MIT)

Copyright (c) <2015> <author or authors>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/
package com.longio.example;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.longio.example.proto.Msg;
import com.longio.spring.EnvProperties;
import com.longio.spring.LioBootstrap;
import com.longio.spring.LongioBeanFactoryPostProcessor;
import com.longio.spring.annotation.Boot;
import com.zhucode.longio.conf.AppLookup;
import com.zhucode.longio.conf.CmdLookup;
import com.zhucode.longio.conf.DefaultAppLookup;
import com.zhucode.longio.conf.DefaultCmdLookup;
import com.zhucode.longio.transport.ProtocolType;
import com.zhucode.longio.transport.TransportType;

/**
 * @author zhu jinxian
 * @date  2015年10月13日
 * 
 */

@SpringBootApplication
public class Application  {
	
	
	@Bean(name="cmdLookup")
	CmdLookup getCmdLookup() {
		return new DefaultCmdLookup(); 
	}
	
	@Bean(name="appLookup")
	AppLookup getAppLookup(@Qualifier("environment") Environment env) {
		return new DefaultAppLookup(new EnvProperties(env)); 
	}
	
	@Bean
	BeanFactoryPostProcessor getLioBeanBeanFactoryPostProcessor(
			@Qualifier("appLookup") AppLookup appLookup, 
			@Qualifier("cmdLookup")CmdLookup cmdLookup) {
		return new LongioBeanFactoryPostProcessor(appLookup, cmdLookup, "com.longio");
	}

	@Boot(port = 5002, pt = ProtocolType.JSON, tt = TransportType.HTTP)
	@Boot(port = 5001, pt = ProtocolType.PROTOBUF, tt = TransportType.SOCKET)
	@Bean(name = "longio.bootstrap")
	public LioBootstrap getLioBootstrap() {
		return new LioBootstrap("com.longio");
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		SpringApplication.run(Application.class, args);
	}

}
