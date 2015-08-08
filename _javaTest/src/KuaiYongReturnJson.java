import java.io.Serializable;
import java.util.Map;


public class KuaiYongReturnJson implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int code;
	private String msg;
	private String data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "code="+code+"\tmsg="+msg+"\tdata==null?"+data;
	}
}
