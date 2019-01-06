/**
 *
 */
package com.shalahuddin.web.form;

import java.util.Arrays;

import org.hibernate.validator.constraints.NotBlank;

public class RoleForm {
	@NotBlank
	private String id;
	@NotBlank
	private String name;
	private String description;
	private boolean active;
	private String[] menus;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String[] getMenus() {
		return menus;
	}
	public void setMenus(String[] menus) {
		this.menus = menus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleForm [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", active=");
		builder.append(active);
		builder.append(", menus=");
		builder.append(Arrays.toString(menus));
		builder.append("]");
		return builder.toString();
	}
}
