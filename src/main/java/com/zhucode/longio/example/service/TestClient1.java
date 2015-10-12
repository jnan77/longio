/**

The MIT License (MIT)

Copyright (c) <2015> <author or authors>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/
package com.zhucode.longio.example.service;

import java.util.Map;

import com.zhucode.longio.annotation.Lio;
import com.zhucode.longio.annotation.LsAutowired;
import com.zhucode.longio.context.parameter.Key;
import com.zhucode.longio.context.parameter.Pack;
import com.zhucode.longio.example.message.UserMsg;
import com.zhucode.longio.transport.ProtocolType;
import com.zhucode.longio.transport.TransportType;


/**
 * @author zhu jinxian
 * @date  2015年10月12日
 * 
 */
@LsAutowired(app = "com.lehuihome", path = "com.lehuihome", tt=TransportType.SOCKET, ip="127.0.0.1", port=9001, pt=ProtocolType.MESSAGE_PACK)
public interface TestClient1 {
	
	@Lio(cmd = "getUser")
	@Pack("com.zhucode.longio.example.message.UserMsg")
	public Map<String, Map<String, UserMsg>> getUser(@Key("user_id")int userId);

	@Lio(cmd = "getVoid")
	public void testVoid();
	
	@Lio(cmd = "getInt")
	public int testInt();
	
	@Lio(cmd = "getString")
	public String testString();
}
