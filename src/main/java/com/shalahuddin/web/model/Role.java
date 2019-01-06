/**
 *
 */
package com.shalahuddin.web.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.shalahuddin.web.common.Auditable;

@Entity
@Table(name = "ADM_ROLE")
public class Role extends Auditable{
	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -6387969431927817553L;
	@Id  //as primary key
	@Column(name="ROLE_CODE", length=50)
	private String id;
	@Column(name="ROLE_NAME", length=100, nullable=false)
	private String name;
	@Column(name="DESCRIPTION", length=300)
	private String description;
	@Column(name="IS_ACTIVE", nullable=false)
	private boolean active;
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name = "ADM_ROLE_MENU",
			joinColumns = { @JoinColumn(name = "ROLE_CODE") },
			inverseJoinColumns = @JoinColumn(name = "MENU_CODE")
			)
	private Set<Menu> menus;

	/**
	 * Default constructor - creates a new instance with no values set.
	 */
	public Role() {
		super();
		this.active = true;
	}

	/**
	 * Create a new instance and set the name.
	 *
	 * @param name name of the role.
	 */
	public Role(final String code, final String name) {
		super();
		this.id = code;
		this.name = name;
		this.active = true;
	}

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

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public void addMenu(Menu menu){
		this.getMenus().add(menu);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Role other = (Role) obj;
		if (active != other.active) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
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
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}
}
