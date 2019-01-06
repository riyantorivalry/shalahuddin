package com.shalahuddin.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.shalahuddin.web.common.Auditable;

@Entity
@Table(name="alumni")
public class Alumni extends Auditable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2801291179412544847L;

	@Id
	@GeneratedValue(generator="gen_alumni")
	@SequenceGenerator(name="gen_alumni", sequenceName="SEQ_ALUMNI", initialValue=1, allocationSize=1)
	@Column(name="ID", nullable=false, length=1000)
	private Long idAlumni;

	@Column(name="ANGGOTA_ID", nullable=false, length=1000)
	private Long idAnggota;

	@Column(name="PROFESI", length=50)
	private String profesi;

	@Column(name="ALAMAT", length=50)
	private String alamat;

	@Column(name="KONTAK", length=50)
	private String kontak;

	@Column(name="EMAIL", length=50)
	private String email;

	public Alumni() {
		super();
	}

	public Long getIdAlumni() {
		return idAlumni;
	}

	public void setIdAlumni(Long idAlumni) {
		this.idAlumni = idAlumni;
	}

	public Long getIdAnggota() {
		return idAnggota;
	}

	public void setIdAnggota(Long idAnggota) {
		this.idAnggota = idAnggota;
	}

	public String getProfesi() {
		return profesi;
	}

	public void setProfesi(String profesi) {
		this.profesi = profesi;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKontak() {
		return kontak;
	}

	public void setKontak(String kontak) {
		this.kontak = kontak;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alamat == null) ? 0 : alamat.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idAlumni == null) ? 0 : idAlumni.hashCode());
		result = prime * result + ((idAnggota == null) ? 0 : idAnggota.hashCode());
		result = prime * result + ((kontak == null) ? 0 : kontak.hashCode());
		result = prime * result + ((profesi == null) ? 0 : profesi.hashCode());
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
		Alumni other = (Alumni) obj;
		if (alamat == null) {
			if (other.alamat != null) {
				return false;
			}
		} else if (!alamat.equals(other.alamat)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (idAlumni == null) {
			if (other.idAlumni != null) {
				return false;
			}
		} else if (!idAlumni.equals(other.idAlumni)) {
			return false;
		}
		if (idAnggota == null) {
			if (other.idAnggota != null) {
				return false;
			}
		} else if (!idAnggota.equals(other.idAnggota)) {
			return false;
		}
		if (kontak == null) {
			if (other.kontak != null) {
				return false;
			}
		} else if (!kontak.equals(other.kontak)) {
			return false;
		}
		if (profesi == null) {
			if (other.profesi != null) {
				return false;
			}
		} else if (!profesi.equals(other.profesi)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Alumni [idAlumni=");
		builder.append(idAlumni);
		builder.append(", idAnggota=");
		builder.append(idAnggota);
		builder.append(", profesi=");
		builder.append(profesi);
		builder.append(", alamat=");
		builder.append(alamat);
		builder.append(", kontak=");
		builder.append(kontak);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
}
