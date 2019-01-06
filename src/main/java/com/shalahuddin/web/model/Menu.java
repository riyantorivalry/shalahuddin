/**
 *
 */
package com.shalahuddin.web.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shalahuddin.web.common.Auditable;

@Entity
@Table(name = "ADM_MENU")
public class Menu extends Auditable{
	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -9069146794188802163L;
	@Id
	@Column(name="MENU_CODE", length=50)
	private String id;
	@Column(name="MENU_NAME", length=100, nullable=false)
	private String name;
	@Column(name="POSITION")
	private Integer position;
	@Column(name="PARENT_CODE", length=50)
	private String parentId;
	@Column(name="ACCESS_LINK", length=300)
	private String accessLink;
	@Column(name="IS_ACTIVE", nullable=false)
	private boolean active;
	@Transient
	private List<Menu> child;
	@Transient
	private int level;

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

	public List<Menu> getChild() {
		return child;
	}
	public void setChild(List<Menu> child) {
		this.child = child;
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessLink == null) ? 0 : accessLink.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Menu other = (Menu) obj;
		if (accessLink == null) {
			if (other.accessLink != null) {
				return false;
			}
		} else if (!accessLink.equals(other.accessLink)) {
			return false;
		}
		if (active != other.active) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parentId == null) {
			if (other.parentId != null) {
				return false;
			}
		} else if (!parentId.equals(other.parentId)) {
			return false;
		}
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu [id=");
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
		builder.append("]");
		return builder.toString();
	}

}
