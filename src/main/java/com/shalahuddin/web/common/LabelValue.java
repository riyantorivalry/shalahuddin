package com.shalahuddin.web.common;

import java.io.Serializable;

public class LabelValue<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 3334627854722625537L;

	public T value;
	private String label;

	public LabelValue(T value) {
		this.value = value;
	}

	public LabelValue(T value, String label) {
		this.value = value;
		this.label = label;
	}

	public T getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "LabelValue [value=" + value + ", label=" + label + "]";
	}

}