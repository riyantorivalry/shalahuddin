package com.shalahuddin.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="struktur_organisasi")
public class StrukturOrganisasi {
	@Id
	@Column(name="ID", nullable=false, length=1000)
	private Long id;

	@Column(name="NAMA", length=50)
	private String nama;

	public StrukturOrganisasi() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
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
		StrukturOrganisasi other = (StrukturOrganisasi) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nama == null) {
			if (other.nama != null) {
				return false;
			}
		} else if (!nama.equals(other.nama)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StrukturOrganisasi [id=");
		builder.append(id);
		builder.append(", nama=");
		builder.append(nama);
		builder.append("]");
		return builder.toString();
	}
}
