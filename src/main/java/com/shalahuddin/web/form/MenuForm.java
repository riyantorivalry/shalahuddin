/**
 *
 */
package com.shalahuddin.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * @author budi
 *
 */
public class MenuForm {
	private String id;
	private String name;
	private Integer position;
	private String parentId;
	private String accessLink;
	private boolean active;
	private boolean checked;
	private int level;

	private List<MenuForm> child = new ArrayList<>();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getAccessLink() {
		return accessLink;
	}
	public void setAccessLink(String accessLink) {
		this.accessLink = accessLink;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<MenuForm> getChild() {
		return child;
	}
	public void setChild(List<MenuForm> child) {
		this.child = child;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuForm [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", position=");
		builder.append(position);
		builder.append(", parentId=");
		builder.append(parentId);
		builder.append(", accessLink=");
		builder.append(accessLink);
		builder.append(", active=");
		builder.append(active);
		builder.append(", checked=");
		builder.append(checked);
		builder.append(", level=");
		builder.append(level);
		builder.append("]");
		return builder.toString();
	}



}
