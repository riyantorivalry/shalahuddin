package com.shalahuddin.web.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="penceramah")
public class Penceramah {
	@Id
	@GeneratedValue(generator="gen_penceramah")
	@SequenceGenerator(name="gen_penceramah", sequenceName="SEQ_PENCERAMAH", initialValue=1, allocationSize=1)
	@Column(name="ID", nullable=false, length=500)
	private Long idPenceramah;

	@Column(name="NAMA", length=50)
	private String nama;

	@Lob
	@Column(name="PIC")
	private byte[] pic;

	@Column(name="BIDANG_KAJIAN", length=50)
	private String bidangKajian;

	@Column(name="ALAMAT", length=255)
	private String alamat;

	@Column(name="KONTAK", length=50)
	private String kontak;

	@Column(name="EMAIL", length=50)
	private String email;

	@Column(name="RIWAYAT_ID", length=50)
	private Long riwayatID;

	@Column(name="CATATAN", length=255)
	private String catatan;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_DATE", length=7)
	private Date updateDate;

	public Long getIdPenceramah() {
		return idPenceramah;
	}

	public void setIdPenceramah(Long idPenceramah) {
		this.idPenceramah = idPenceramah;
	}

	public String getNama() {
		return nama;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getBidangKajian() {
		return bidangKajian;
	}

	public void setBidangKajian(String bidangKajian) {
		this.bidangKajian = bidangKajian;
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

	public Long getRiwayatID() {
		return riwayatID;
	}

	public void setRiwayatID(Long riwayatID) {
		this.riwayatID = riwayatID;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alamat == null) ? 0 : alamat.hashCode());
		result = prime * result + ((bidangKajian == null) ? 0 : bidangKajian.hashCode());
		result = prime * result + ((catatan == null) ? 0 : catatan.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idPenceramah == null) ? 0 : idPenceramah.hashCode());
		result = prime * result + ((kontak == null) ? 0 : kontak.hashCode());
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
		result = prime * result + Arrays.hashCode(pic);
		result = prime * result + ((riwayatID == null) ? 0 : riwayatID.hashCode());
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
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
		Penceramah other = (Penceramah) obj;
		if (alamat == null) {
			if (other.alamat != null) {
				return false;
			}
		} else if (!alamat.equals(other.alamat)) {
			return false;
		}
		if (bidangKajian == null) {
			if (other.bidangKajian != null) {
				return false;
			}
		} else if (!bidangKajian.equals(other.bidangKajian)) {
			return false;
		}
		if (catatan == null) {
			if (other.catatan != null) {
				return false;
			}
		} else if (!catatan.equals(other.catatan)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (idPenceramah == null) {
			if (other.idPenceramah != null) {
				return false;
			}
		} else if (!idPenceramah.equals(other.idPenceramah)) {
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
		if (riwayatID == null) {
			if (other.riwayatID != null) {
				return false;
			}
		} else if (!riwayatID.equals(other.riwayatID)) {
			return false;
		}
		if (updateDate == null) {
			if (other.updateDate != null) {
				return false;
			}
		} else if (!updateDate.equals(other.updateDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Penceramah [idPenceramah=");
		builder.append(idPenceramah);
		builder.append(", nama=");
		builder.append(nama);
		builder.append(", pic=");
		builder.append(Arrays.toString(pic));
		builder.append(", bidangKajian=");
		builder.append(bidangKajian);
		builder.append(", alamat=");
		builder.append(alamat);
		builder.append(", kontak=");
		builder.append(kontak);
		builder.append(", email=");
		builder.append(email);
		builder.append(", riwayatID=");
		builder.append(riwayatID);
		builder.append(", catatan=");
		builder.append(catatan);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append("]");
		return builder.toString();
	}
}
