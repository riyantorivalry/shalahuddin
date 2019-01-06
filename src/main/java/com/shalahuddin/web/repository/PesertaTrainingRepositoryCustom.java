/**
 *
 */
package com.shalahuddin.web.repository;

import java.util.Date;
import java.util.List;

import com.shalahuddin.web.model.PesertaTraining;

/**
 * @author USER11
 *
 */
public interface PesertaTrainingRepositoryCustom {
	List<PesertaTraining> findAll(String namaDepan, String namaBelakang, Integer nilaiTraining, String company, Date dateFrom, Date dateTo);
}
