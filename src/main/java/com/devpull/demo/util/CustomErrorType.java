package com.devpull.demo.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.MultiValueMap;

public class CustomErrorType implements MultiValueMap<String, String> {

	private String errorMsg;

	public CustomErrorType(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Entry<String, List<String>>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> put(String arg0, List<String> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends List<String>> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<List<String>> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirst(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAll(String key, List<? extends String> values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAll(MultiValueMap<String, String> values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAll(Map<String, String> values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, String> toSingleValueMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
