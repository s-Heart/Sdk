import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;


public class MapToJsonString {

	public static void main(String[] args) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orderId", 123321);
		map.put("money", 112);
		String jsonString=JSON.toJSONString(map);
		System.out.println(jsonString);
	}

}
