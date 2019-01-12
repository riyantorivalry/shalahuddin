package com.shalahuddin.web.form;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.shalahuddin.web.model.PrestasiAnggota;

public class AnggotaForm {
	private String nama;
	private byte[] pic;
	private Date tanggalLahir;
	private String alamat;
	private String prodi;
	private String universitas;
	private String angkatan;
	private String kontak;
	private String email;
	private Long departemenId;
	private String keanggotaan;
	private Boolean active;
	private List<PrestasiAnggota> prestasi;

	public AnggotaForm() {
		super();
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<PrestasiAnggota> getPrestasi() {
		return prestasi;
	}
	public void setPrestasi(List<PrestasiAnggota> prestasi) {
		this.prestasi = prestasi;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((alamat == null) ? 0 : alamat.hashCode());
		result = prime * result + ((angkatan == null) ? 0 : angkatan.hashCode());
		result = prime * result + ((departemenId == null) ? 0 : departemenId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((keanggotaan == null) ? 0 : keanggotaan.hashCode());
		result = prime * result + ((kontak == null) ? 0 : kontak.hashCode());
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
		result = prime * result + Arrays.hashCode(pic);
		result = prime * result + ((prestasi == null) ? 0 : prestasi.hashCode());
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
		AnggotaForm other = (AnggotaForm) obj;
		if (active == null) {
			if (other.active != null) {
				return false;
			}
		} else if (!active.equals(other.active)) {
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
		if (!Arrays.equals(pic, other.pic)) {
			return false;
		}
		if (prestasi == null) {
			if (other.prestasi != null) {
				return false;
			}
		} else if (!prestasi.equals(other.prestasi)) {
			return false;
		}
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
		builder.append("AnggotaForm [nama=");
		builder.append(nama);
		builder.append(", pic=");
		builder.append(Arrays.toString(pic));
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
		builder.append(", prestasi=");
		builder.append(prestasi);
		builder.append("]");
		return builder.toString();
	}
}
