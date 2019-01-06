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
@Table(name="peserta_Training") //yang akan menjadi nama table
public class PesertaTraining extends Auditable {
	@Id
	@GeneratedValue(generator="gen_pesertaTraining")
	@SequenceGenerator(name="gen_pesertaTraining", sequenceName="SEQ_PESERTA_TRAINING", initialValue=1, allocationSize=1)
	@Column(name="ID", nullable=false, length=22)
	private Long idPeserta;

	@Column(name="NAMA_DEPAN", length=50)
	private String namaDepan;

	@Column(name="NAMA_BELAKANG", length=50)
	private String namaBelakang;

	@Temporal(TemporalType.DATE)
	@Column(name="TANGGAL_LAHIR", length=7)
	private Date tanggalLahir;

	@Column(name="NILAI_TRAINING", length=22)
	private Integer nilaiTraining;

	@Column(name="COMPANY", length=50)
	private String company;


	//GETTER AND SETTER
	public Long getIdPeserta() {
		return idPeserta;
	}

	public void setIdPeserta(Long idPeserta) {
		this.idPeserta = idPeserta;
	}

	public String getNamaDepan() {
		return namaDepan;
	}

	public void setNamaDepan(String namaDepan) {
		this.namaDepan = namaDepan;
	}

	public String getNamaBelakang() {
		return namaBelakang;
	}

	public void setNamaBelakang(String namaBelakang) {
		this.namaBelakang = namaBelakang;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public Integer getNilaiTraining() {
		return nilaiTraining;
	}

	public void setNilaiTraining(Integer nilaiTraining) {
		this.nilaiTraining = nilaiTraining;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((idPeserta == null) ? 0 : idPeserta.hashCode());
		result = prime * result + ((namaBelakang == null) ? 0 : namaBelakang.hashCode());
		result = prime * result + ((namaDepan == null) ? 0 : namaDepan.hashCode());
		result = prime * result + ((nilaiTraining == null) ? 0 : nilaiTraining.hashCode());
		result = prime * result + ((tanggalLahir == null) ? 0 : tanggalLahir.hashCode());
		return result;
	}

	//HASCODE() AND EQUALS()
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
		PesertaTraining other = (PesertaTraining) obj;
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		if (idPeserta == null) {
			if (other.idPeserta != null) {
				return false;
			}
		} else if (!idPeserta.equals(other.idPeserta)) {
			return false;
		}
		if (namaBelakang == null) {
			if (other.namaBelakang != null) {
				return false;
			}
		} else if (!namaBelakang.equals(other.namaBelakang)) {
			return false;
		}
		if (namaDepan == null) {
			if (other.namaDepan != null) {
				return false;
			}
		} else if (!namaDepan.equals(other.namaDepan)) {
			return false;
		}
		if (nilaiTraining == null) {
			if (other.nilaiTraining != null) {
				return false;
			}
		} else if (!nilaiTraining.equals(other.nilaiTraining)) {
			return false;
		}
		if (tanggalLahir == null) {
			if (other.tanggalLahir != null) {
				return false;
			}
		} else if (!tanggalLahir.equals(other.tanggalLahir)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PesertaTraining [idPeserta=");
		builder.append(idPeserta);
		builder.append(", namaDepan=");
		builder.append(namaDepan);
		builder.append(", namaBelakang=");
		builder.append(namaBelakang);
		builder.append(", tanggalLahir=");
		builder.append(tanggalLahir);
		builder.append(", nilaiTraining=");
		builder.append(nilaiTraining);
		builder.append(", company=");
		builder.append(company);
		builder.append("]");
		return builder.toString();
	}



}
