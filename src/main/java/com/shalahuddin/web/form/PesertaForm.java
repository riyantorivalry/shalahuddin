package com.shalahuddin.web.form;

import java.util.Date;

public class PesertaForm {
	private String namaDepan;
	private String namaBelakang;
	private Date tanggalLahir;
	private Integer nilaiTraining;
	private String company;

	public PesertaForm() {
		super();
	}


	public PesertaForm(String namaDepan, String namaBelakang, Date tanggalLahir, Integer nilaiTraining,
			String company) {
		super();
		this.namaDepan = namaDepan;
		this.namaBelakang = namaBelakang;
		this.tanggalLahir = tanggalLahir;
		this.nilaiTraining = nilaiTraining;
		this.company = company;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PesertaForm [namaDepan=");
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
