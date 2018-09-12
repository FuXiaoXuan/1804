package cn.jt.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

	// 底层利用包扫描创建实例
public class ZuulFallback implements ZuulFallbackProvider {

	@Override
	public String getRoute() { // application.name
		// TODO Auto-generated method stub
		return "provider-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		// TODO Auto-generated method stub
		return new ClientHttpResponse() {

			@Override // 设置头信息
			public HttpHeaders getHeaders() {
				HttpHeaders heads = new HttpHeaders();
				heads.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return heads;
			}

			@Override // 设置返回内容,当出现异常时,断路器返回的默认值
			public InputStream getBody() throws IOException {

				return new ByteArrayInputStream(new String("tony").getBytes());
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST.value();
			}

			@Override
			public void close() {

			}

		};
	}

}
