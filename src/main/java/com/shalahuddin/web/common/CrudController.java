package com.shalahuddin.web.common;

import java.util.ArrayList;
import java.util.List;

public abstract class CrudController<F, E> extends BaseController {

	protected E toEntity(F form){
		return toEntity(form, false);
	}

	protected abstract E toEntity(F form, boolean isEdit);

	protected abstract F toForm(E entity);

	protected List<E> toListEntity(List<F> listF) {
		List<E> listE = new ArrayList<>();
		for (F f : listF) {
			listE.add(toEntity(f));
		}
		return listE;
	}

	protected List<F> toListForm(List<E> listE) {
		List<F> listF = new ArrayList<>();
		for (E e : listE) {
			listF.add(toForm(e));
		}
		return listF;
	}
}