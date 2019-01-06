package com.shalahuddin.web.repository;

import java.util.Date;
import java.util.List;

import com.shalahuddin.web.model.Training;

public interface TrainingRepositoryCustom {
	List<Training> findBetweenDate(String namaTraining, Integer jumlahPeserta, Date dateFrom, Date dateTo);

}
