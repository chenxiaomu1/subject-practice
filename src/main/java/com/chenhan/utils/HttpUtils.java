package com.chenhan.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Http通讯工具
 * 
 * @author chenhan
 * @2020年11月12日
 * 
 */

public class HttpUtils {

	private static String UTF_8 = "UTF-8";

	private RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(6000).setSocketTimeout(14000)
			.setConnectionRequestTimeout(8000).build();

	/**
	 * HttpClient Get方式发送，无header要求
	 */
	public String sendGet(String url, Map<String,String> params) {
		
		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		

		HttpGet request = new HttpGet();
		try {
			request.setURI(new URI(buildUrl(url, params)));//buildUrl()组装参数到请求地址
			HttpResponse response = client.execute(request);
			return EntityUtils.toString(response.getEntity());//EntityUtils用来把entity转为String返回
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			request.releaseConnection();
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return url;
	}

	/**
	 * HttpClient Get方式发送，添加header参数
	 */
	public String sendGet(String url, Map<String,String> params, Map<String,String> headerMap) {

		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

		//组装参数到请求地址
		HttpGet request = new HttpGet();
		try {
			request.setURI(new URI(buildUrl(url, params)));

			//header信息
			assemblingHeaders(request, headerMap);

			HttpResponse response = client.execute(request);
			return EntityUtils.toString(response.getEntity());//EntityUtils用来把entity转为String返回
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			request.releaseConnection();
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return url;
	}

	public String sendPost(String url, Map<String,String> params, Map<String,String> headerMap){
		List<NameValuePair> list = packList(params);
		if(CollectionUtils.isEmpty(list)) throw new RuntimeException("参数不全，请稍后重试");

		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(list, UTF_8));
			assemblingHeaders(httpPost, headerMap);
			CloseableHttpResponse response = client.execute(httpPost);
			return EntityUtils.toString(response.getEntity());
		} catch (Throwable throwable) {
			throw new RuntimeException("参数不全，请稍后重试");
		}finally {
			httpPost.releaseConnection();
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * json形式的body参数 + 请求头参数
	 * @param url
	 * @param json
	 * @param headerMap
	 * @return
	 */
	public String sendPostJson(String url, String json, Map<String,String> headerMap){

		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		HttpPost request = null;
		try {
			request = new HttpPost(url);
			request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
			assemblingHeaders(request, headerMap);

			CloseableHttpResponse response = client.execute(request);
			return EntityUtils.toString(response.getEntity(),UTF_8);
		} catch (Throwable throwable) {
			throw new RuntimeException("参数不全，请稍后重试");
		}finally {
			request.releaseConnection();
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将map参数组装至url
	 * @param reqUrl
	 * @param params
	 * @return
	 */
	public String buildUrl(String reqUrl, Map<String,String> params) {
		if (params == null || params.isEmpty()) return reqUrl;

		StringBuilder query = new StringBuilder();
		Set<String> set = params.keySet();
		for (String key : set) {
			query.append(String.format("%s=%s&", key, params.get(key)));
		}

		String parmasString = query.toString().substring(0, query.toString().length() - 1);
		if (reqUrl.contains("?")) {
			if (reqUrl.endsWith("&")) {
				return reqUrl + parmasString;
			} else {
				return reqUrl + "&" + parmasString;
			}
		}
		return reqUrl + "?" + parmasString;
		
	}

	public List<NameValuePair> packList(Map<String,String> params){
		List<NameValuePair> list = new ArrayList<>();
		Set<String> keys = params.keySet();
		for(String key : keys){
			list.add(new BasicNameValuePair(key, params.get(key)));
		}
		return list;
	}

	/**
	 * 给request添加header参数
	 * @param request 目标请求
	 * @param params  需要添加的header参数
	 */
	public void assemblingHeaders(HttpRequest request, Map<String, String> params){
		//header信息
		if(!CollectionUtils.isEmpty(params)){
			Set<String> keySet = params.keySet();
			for(String key : keySet){
				request.setHeader(key, params.get(key));//最终是更新/添加到HeaderGroup中的List<Header>，可以多次setHeader
			}
		}
	}

	public static void main(String[] args) {
		HttpUtils utils = new HttpUtils();
		/*Map<String,String> map = new HashMap<>();
		map.put("agent_project_no","X9807");

		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Content_Type","aplication/json");

		String response = utils.sendPostJson("http://localhost:5088/project/query", JSONObject.toJSONString(map), headerMap);
		System.out.println(response);*/

		//访问其他平台接口
		Map<String,String> map = new HashMap<>();
		map.put("grant_type","client_credentials");
		map.put("client_id","fang140ea6487e81872f");
		map.put("client_secret","e7167e2b593645e8478661a91c718cdf2368ded5602fe94a403de5e2b26eab85");

		//httpClient方式
//		String resp = utils.sendGet("http://10.122.143.10:8888/oauth/token", map);
//		System.out.println(resp);

		//restTemplate方式
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://10.122.143.10:8888/oauth/token?grant_type={grant_type}&client_id={client_id}&client_secret={client_secret}";
		String body = restTemplate.getForEntity(url, String.class, map).getBody();
		System.out.println(body);

	}
}
