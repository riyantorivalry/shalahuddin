package com.shalahuddin.web.model;

public class PrestasiAnggota {
	private Long id;
	private Long anggotaId;
	private String jenisPrestasi;
	private String tingkat;
	private String tahun;

	public PrestasiAnggota() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAnggotaId() {
		return anggotaId;
	}

	public void setAnggotaId(Long anggotaId) {
		this.anggotaId = anggotaId;
	}

	public String getJenisPrestasi() {
		return jenisPrestasi;
	}

	public void setJenisPrestasi(String jenisPrestasi) {
		this.jenisPrestasi = jenisPrestasi;
	}

	public String getTingkat() {
		return tingkat;
	}

	public void setTingkat(String tingkat) {
		this.tingkat = tingkat;
	}

	public String getTahun() {
		return tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anggotaId == null) ? 0 : anggotaId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jenisPrestasi == null) ? 0 : jenisPrestasi.hashCode());
		result = prime * result + ((tahun == null) ? 0 : tahun.hashCode());
		result = prime * result + ((tingkat == null) ? 0 : tingkat.hashCode());
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
		PrestasiAnggota other = (PrestasiAnggota) obj;
		if (anggotaId == null) {
			if (other.anggotaId != null) {
				return false;
			}
		} else if (!anggotaId.equals(other.anggotaId)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (jenisPrestasi == null) {
			if (other.jenisPrestasi != null) {
				return false;
			}
		} else if (!jenisPrestasi.equals(other.jenisPrestasi)) {
			return false;
		}
		if (tahun == null) {
			if (other.tahun != null) {
				return false;
			}
		} else if (!tahun.equals(other.tahun)) {
			return false;
		}
		if (tingkat == null) {
			if (other.tingkat != null) {
				return false;
			}
		} else if (!tingkat.equals(other.tingkat)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrestasiAnggota [id=");
		builder.append(id);
		builder.append(", anggotaId=");
		builder.append(anggotaId);
		builder.append(", jenisPrestasi=");
		builder.append(jenisPrestasi);
		builder.append(", tingkat=");
		builder.append(tingkat);
		builder.append(", tahun=");
		builder.append(tahun);
		builder.append("]");
		return builder.toString();
	}
}
