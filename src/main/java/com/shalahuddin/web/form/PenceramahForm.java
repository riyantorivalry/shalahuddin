package com.shalahuddin.web.form;

import java.sql.Date;
import java.util.Arrays;

public class PenceramahForm {
	private String nama;
	private byte[] pic;
	private String bidangKajian;
	private String alamat;
	private String kontak;
	private String email;
	private Long riwayatId;
	private String catatan;
	private Date updateDate;

	public PenceramahForm() {
		super();
	}

	public PenceramahForm(String nama, byte[] pic, String bidangKajian, String alamat, String kontak, String email,
			Long riwayatId, String catatan, Date updateDate) {
		super();
		this.nama = nama;
		this.pic = pic;
		this.bidangKajian = bidangKajian;
		this.alamat = alamat;
		this.kontak = kontak;
		this.email = email;
		this.riwayatId = riwayatId;
		this.catatan = catatan;
		this.updateDate = updateDate;
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

	public Long getRiwayatId() {
		return riwayatId;
	}

	public void setRiwayatId(Long riwayatId) {
		this.riwayatId = riwayatId;
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
		result = prime * result + ((kontak == null) ? 0 : kontak.hashCode());
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
		result = prime * result + Arrays.hashCode(pic);
		result = prime * result + ((riwayatId == null) ? 0 : riwayatId.hashCode());
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
		PenceramahForm other = (PenceramahForm) obj;
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
		if (riwayatId == null) {
			if (other.riwayatId != null) {
				return false;
			}
		} else if (!riwayatId.equals(other.riwayatId)) {
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
		builder.append("PenceramahForm [nama=");
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
		builder.append(", riwayatId=");
		builder.append(riwayatId);
		builder.append(", catatan=");
		builder.append(catatan);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append("]");
		return builder.toString();
	}
}
