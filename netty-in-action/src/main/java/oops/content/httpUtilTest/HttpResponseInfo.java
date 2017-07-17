package oops.content.httpUtilTest;

/**
 * http请求返回对象
 * Created by liruipeng on 2017/3/16.
 */
public class HttpResponseInfo {
	private String id;
	private String data;
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
