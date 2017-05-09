package com.cg.test;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonUtils.class);

	private static ObjectMapper objectMapper;

	private static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(
					DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,
					true);
		}
		return objectMapper;
	}

	public static <T, C extends Collection<T>> C readAsCollection(String json,
			Class<T> type, Class<C> colType) {

		C tokens = null;
		ObjectMapper objectMapper = getObjectMapper();
		try {
			tokens = objectMapper.readValue(json, objectMapper.getTypeFactory()
					.constructCollectionType(colType, type));
		} catch (IOException e) {
			logger.error("Invalid JSON: " + json + " Collection Type: "
					+ colType + " Type: " + type, e);
		}
		return tokens;
	}

	public static String writeJson(Object object, boolean formatRequired) {
		String value = null;
		ObjectMapper objectMapper = null;
		objectMapper = getObjectMapper();
		try {
			if (formatRequired) {
				// format the JSON
				value = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(object);
			} else {
				value = objectMapper.writeValueAsString(object);
			}
		} catch (JsonProcessingException e) {
			logger.error("Error writting JSON : " + e.getMessage());
		}
		return value;
	}

	public static Optional<Map<?, ?>> readJsonAsMap(String data) {
		Map<?, ?> jsonMap = null;
		ObjectMapper objectMapper = getObjectMapper();
		try {
			jsonMap = objectMapper.readValue(data, Map.class);
		} catch (IOException e) {
			logger.error("Error reading JSON : " + e.getMessage());
		}
		return Optional.ofNullable(jsonMap);
	}

	public static JsonNode readJsonAsNode(String data) {
		JsonNode jsonNode = null;
		ObjectMapper objectMapper = getObjectMapper();
		try {
			jsonNode = objectMapper.readTree(data);
		} catch (IOException e) {
			logger.error("Error reading JSON : " + e.getMessage());
		}
		return jsonNode;
	}

	public static <T> T readJson(String data, Class<T> clazz) {
		T value = null;
		ObjectMapper objectMapper = getObjectMapper();
		try {
			value = objectMapper.readValue(data, clazz);
		} catch (IOException e) {
			logger.error("Error reading JSON : " + e.getMessage());
		}
		return value;
	}
}
