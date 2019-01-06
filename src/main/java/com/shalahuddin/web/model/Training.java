/**
 *
 */
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

/**
 * @author USER11
 *
 */

@Entity
@Table (name="training")
public class Training extends Auditable{
	@Id
	@GeneratedValue(generator = "gen_training")
	@SequenceGenerator(name = "gen_training", sequenceName = "SEQUENCE_TRAINING", initialValue=1, allocationSize=1)
	@Column(name="ID", nullable=false)
	private Long id;

	@Column(name="NAMA_TRAINING", length=300)
	private String namaTraining;

	@Temporal(TemporalType.DATE)
	@Column(name="TANGGAL_TRAINING")
	private Date tanggalTraining;

	@Column(name="JUMLAH_PESERTA")
	private Integer jumlahPeserta;

	@Column(name="COMPANY", length=50)
	private String company;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamaTraining() {
		return namaTraining;
	}

	public void setNamaTraining(String namaTraining) {
		this.namaTraining = namaTraining;
	}

	public Date getTanggalTraining() {
		return tanggalTraining;
	}

	public void setTanggalTraining(Date tanggalTraining) {
		this.tanggalTraining = tanggalTraining;
	}

	public Integer getJumlahPeserta() {
		return jumlahPeserta;
	}

	public void setJumlahPeserta(Integer jumlahPeserta) {
		this.jumlahPeserta = jumlahPeserta;
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
		result = prime * result + ((jumlahPeserta == null) ? 0 : jumlahPeserta.hashCode());
		result = prime * result + ((namaTraining == null) ? 0 : namaTraining.hashCode());
		result = prime * result + ((tanggalTraining == null) ? 0 : tanggalTraining.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Training other = (Training) obj;
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		if (jumlahPeserta == null) {
			if (other.jumlahPeserta != null) {
				return false;
			}
		} else if (!jumlahPeserta.equals(other.jumlahPeserta)) {
			return false;
		}
		if (namaTraining == null) {
			if (other.namaTraining != null) {
				return false;
			}
		} else if (!namaTraining.equals(other.namaTraining)) {
			return false;
		}
		if (tanggalTraining == null) {
			if (other.tanggalTraining != null) {
				return false;
			}
		} else if (!tanggalTraining.equals(other.tanggalTraining)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Training [id=");
		builder.append(id);
		builder.append(", namaTraining=");
		builder.append(namaTraining);
		builder.append(", tanggalTraining=");
		builder.append(tanggalTraining);
		builder.append(", jumlahPeserta=");
		builder.append(jumlahPeserta);
		builder.append(", company=");
		builder.append(company);
		builder.append("]");
		return builder.toString();
	}







}
