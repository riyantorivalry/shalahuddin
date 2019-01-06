package com.shalahuddin.web.form;

import java.util.Date;

public class TrainingForm {
	private String namaTraining;
	private Date tanggalTraining;
	private Integer jumlahPeserta;
	private String company;

	public TrainingForm() { //konstructor ini harus dideklarasikan menggunakan super() karena dibawah juga sudah ada constructor dg parameter
		super();  //jika tidak didefinisikan(meskipun default) maka tidak akan terpanggil/tidak bisa diinstansiate
	}

	public TrainingForm(String namaTraining, Date tanggalTraining, Integer jumlahPeserta, String company) {
		super();
		this.namaTraining = namaTraining;
		this.tanggalTraining = tanggalTraining;
		this.jumlahPeserta = jumlahPeserta;
		this.company = company;
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
	public String toString() {
		return "TrainingForm [namaTraining=" + namaTraining + ", tanggalTraining=" + tanggalTraining
				+ ", jumlahPeserta=" + jumlahPeserta + ", company=" + company + "]";
	}



}
