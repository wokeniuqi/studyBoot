package com.example.demo.service.impl;

import com.example.demo.service.JsonConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class JsonConverterJacksonImpl implements JsonConverter,InitializingBean {
	
	private ObjectMapper objectMapper;
	
	/** 配置, 字符串-bean, ObjectMapper的JsonParser.Feature, value为true|false*/
	private Map<JsonParser.Feature, Boolean> jsonParserfeatures;
	
	/** 配置, bean-字符串, ObjectMapper的JsonGenerator.Feature, value为true|false*/
	private Map<Feature, Boolean> jsonGeneratorFeatures;

	public JsonConverterJacksonImpl() {
	}

	public JsonConverterJacksonImpl(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public <T> T fromJson(String json, Class<T> resultType) {
		try {
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return objectMapper.readValue(json, resultType);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toJson(Object value) {
		try {
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			return objectMapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 美化打印的json
	 * @param value 字符串转换为相应类型
	 * @return public
	 */
	@Override
	public String toJsonPretty(Object value) {
		org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void toJson(OutputStream out, Object value) {
		try {
			objectMapper.writeValue(out, value);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void toJson(Writer out, Object value) {
		try {
			objectMapper.writeValue(out, value);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <b>功能描述：</b>spring配置bean后加载jsckson配置<br>
	 * <b>修订记录：</b><br>
	 * <li>20131015&nbsp;&nbsp;|&nbsp;&nbsp;刘庆魁&nbsp;&nbsp;|&nbsp;&nbsp;创建方法</li>
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

		if( jsonGeneratorFeatures!=null ){
			Set<Entry<Feature, Boolean>> entrySet = jsonGeneratorFeatures.entrySet();
			for (Entry<Feature, Boolean> e : entrySet) {
				objectMapper.configure(e.getKey(), e.getValue());
			}
		}

		if( jsonParserfeatures!=null ){
			Set<Entry<JsonParser.Feature, Boolean>> entrySet = jsonParserfeatures.entrySet();
			for (Entry<JsonParser.Feature, Boolean> e : entrySet) {
				objectMapper.configure(e.getKey(), e.getValue());
			}
		}

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//空属性不序列化
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); //json key可以没有双引号
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //忽略解析未知的字段

	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	//------------set get----------
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setJsonParserfeatures(
			Map<JsonParser.Feature, Boolean> jsonParserfeatures) {
		this.jsonParserfeatures = jsonParserfeatures;
	}

	public void setJsonGeneratorFeatures(
			Map<Feature, Boolean> jsonGeneratorFeatures) {
		this.jsonGeneratorFeatures = jsonGeneratorFeatures;
	}
	
}
