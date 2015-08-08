<%@ page language="java" import="java.util.*,java.io.*,com.test.util.*"
	pageEncoding="utf-8"%>
	<%
		/* 1、设置支付私钥：从商户自服务系统获取 */
		String appkey = "M0I4Q0EyNzk5NEEzM0VFOUJCNTE5MDU0NjMzRDA1NEFFRDYzMUQzQk1UWTFNVGcyTURFMk9ETXhPREE0T1RjNU9ETXJNVEU1T0RVd01qRXhOalV6TkRBNE1qYzNOekV4TWpreE1qZ3dNakkzT0RVNE1UWXdOVEkz";

		/* 2、提取支付结果通知数据 */
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				in, "UTF-8"));//请注意是UTF-8编码			
		String line = null;
		StringBuilder tranData = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			tranData.append(line);
		}
		line = tranData.toString();
		System.out.println("info:支付结果通知内容["+line+"]");//记录收到数据
		
		String result = "FAILURE";
		/* 3、解析支付结果通知数据成业务数据  */
		if (null == line || "".equals(line.trim())) {			
			System.out.println("error:支付结果通知内容为空");
		} else {
			int index = line.indexOf('&');
			if (0 > index) {				
				System.out.println("error:支付结果通知内容格式不对，请确认格式为tranddate={}&sing=。");
			} else {
				String transdata = line.substring(10,index);//transdata=
				String sign = line.substring(index+6);//sign=
				System.out.println("info:支付结果通知内容transdata["+transdata+"]");
				System.out.println("info:支付结果通知签名sign["+sign+"]");
				if (transdata == null || sign == null
						|| "".equalsIgnoreCase(transdata)
						|| "".equalsIgnoreCase(sign)) {					
					System.out.println("error:支付结果通知内容格式不对，请确认格式为tranddate={}&sing=。");
				} else {					
					boolean checkFlag = CpTransSyncSignValid.validSign(
							transdata, sign, appkey);
					if (checkFlag) {
						/* 4、业务处理  */
						result = "SUCCESS";
						System.out.println("info:支付结果通知内容验签成功");
					} else {						
						System.out.println("error:支付结果通知内容验签失败");
					}
				}
			}
		}
		/* 5、返回处理结果  */
		out.write(result);		
	%>