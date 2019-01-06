package com.shalahuddin.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.shalahuddin.web.common.Auditable;

@Entity
@Table(name="anggota")
public class Anggota extends Auditable {
	/**
	 *
	 */
	private static final long serialVersionUID = 6880557780123960769L;

	@Id
	@GeneratedValue(generator="gen_anggota")
	@SequenceGenerator(name="gen_anggota", sequenceName="SEQ_ANGGOTA", initialValue=1, allocationSize=1)
	@Column(name="ID", nullable=false, length=1000)
	private Long idAnggota;

	@Column(name="NAMA", length=50)
	private String nama;

	@Temporal(TemporalType.DATE)
	@Column(name="TANGGAL_LAHIR", length=7)
	private Date tanggalLahir;

	@Column(name="ALAMAT", length=100)
	private String alamat;

	@Column(name="PRODI", length=50)
	private String prodi;

	@Column(name="UNIVERSITAS", length=50)
	private String universitas;

	@Column(name="ANGKATAN", length=4)
	private String angkatan;

	@Column(name="KONTAK", length=50)
	private String kontak;

	@Column(name="EMAIL", length=50)
	private String email;

	@Column(name="DEPARTEMEN_ID", length=50)
	private Long departemenId;

	@Column(name="KEANGGOTAAN", length=3)
	private String keanggotaan;

	@Column(name="IS_ACTIVE", nullable=true)
	private Boolean active;

	//	private List<PrestasiAnggota> prestasi;


	public Anggota() {
		super();
	}

	public Long getIdAnggota() {
		return idAnggota;
	}

	public void setIdAnggota(Long idAnggota) {
		this.idAnggota = idAnggota;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getProdi() {
		return prodi;
	}

	public void setProdi(String prodi) {
		this.prodi = prodi;
	}

	public String getUniversitas() {
		return universitas;
	}

	public void setUniversitas(String universitas) {
		this.universitas = universitas;
	}

	public String getAngkatan() {
		return angkatan;
	}

	public void setAngkatan(String angkatan) {
		this.angkatan = angkatan;
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

	public Long getDepartemenId() {
		return departemenId;
	}

	public void setDepartemenId(Long departemenId) {
		this.departemenId = departemenId;
	}

	public String getKeanggotaan() {
		return keanggotaan;
	}

	public void setKeanggotaan(String keanggotaan) {
		this.keanggotaan = keanggotaan;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	//	public List<PrestasiAnggota> getPrestasi() {
	//		return prestasi;
	//	}
	//
	//	public void setPrestasi(List<PrestasiAnggota> prestasi) {
	//		this.prestasi = prestasi;
	//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((alamat == null) ? 0 : alamat.hashCode());
		result = prime * result + ((angkatan == null) ? 0 : angkatan.hashCode());
		result = prime * result + ((departemenId == null) ? 0 : departemenId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idAnggota == null) ? 0 : idAnggota.hashCode());
		result = prime * result + ((keanggotaan == null) ? 0 : keanggotaan.hashCode());
		result = prime * result + ((kontak == null) ? 0 : kontak.hashCode());
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
		//		result = prime * result + ((prestasi == null) ? 0 : prestasi.hashCode());
		result = prime * result + ((prodi == null) ? 0 : prodi.hashCode());
		result = prime * result + ((tanggalLahir == null) ? 0 : tanggalLahir.hashCode());
		result = prime * result + ((universitas == null) ? 0 : universitas.hashCode());
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
		Anggota other = (Anggota) obj;
		if (active != other.active) {
			return false;
		}
		if (alamat == null) {
			if (other.alamat != null) {
				return false;
			}
		} else if (!alamat.equals(other.alamat)) {
			return false;
		}
		if (angkatan == null) {
			if (other.angkatan != null) {
				return false;
			}
		} else if (!angkatan.equals(other.angkatan)) {
			return false;
		}
		if (departemenId == null) {
			if (other.departemenId != null) {
				return false;
			}
		} else if (!departemenId.equals(other.departemenId)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (idAnggota == null) {
			if (other.idAnggota != null) {
				return false;
			}
		} else if (!idAnggota.equals(other.idAnggota)) {
			return false;
		}
		if (keanggotaan == null) {
			if (other.keanggotaan != null) {
				return false;
			}
		} else if (!keanggotaan.equals(other.keanggotaan)) {
			return false;
		}
		if (kontak == null) {
			if (other.kontak != null) {
				return false;
			}
		} else if (!kontak.equals(other.kontak)) {
			return false;
		}
		if (nama == null) {
			if (other.nama != null) {
				return false;
			}
		} else if (!nama.equals(other.nama)) {
			return false;
		}
		//		if (prestasi == null) {
		//			if (other.prestasi != null) {
		//				return false;
		//			}
		//		} else if (!prestasi.equals(other.prestasi)) {
		//			return false;
		//		}
		if (prodi == null) {
			if (other.prodi != null) {
				return false;
			}
		} else if (!prodi.equals(other.prodi)) {
			return false;
		}
		if (tanggalLahir == null) {
			if (other.tanggalLahir != null) {
				return false;
			}
		} else if (!tanggalLahir.equals(other.tanggalLahir)) {
			return false;
		}
		if (universitas == null) {
			if (other.universitas != null) {
				return false;
			}
		} else if (!universitas.equals(other.universitas)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Anggota [idAnggota=");
		builder.append(idAnggota);
		builder.append(", nama=");
		builder.append(nama);
		builder.append(", tanggalLahir=");
		builder.append(tanggalLahir);
		builder.append(", alamat=");
		builder.append(alamat);
		builder.append(", prodi=");
		builder.append(prodi);
		builder.append(", universitas=");
		builder.append(universitas);
		builder.append(", angkatan=");
		builder.append(angkatan);
		builder.append(", kontak=");
		builder.append(kontak);
		builder.append(", email=");
		builder.append(email);
		builder.append(", departemenId=");
		builder.append(departemenId);
		builder.append(", keanggotaan=");
		builder.append(keanggotaan);
		builder.append(", active=");
		builder.append(active);
		//		builder.append(", prestasi=");
		//		builder.append(prestasi);
		builder.append("]");
		return builder.toString();
	}
}
