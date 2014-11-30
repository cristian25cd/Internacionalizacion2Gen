/* ========================================================================
 * Copyright 2014 RoyalSystem
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 RoyalSystem

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201411201032

*/

package co.edu.uniandes.csw.RoyalSystem.departamentouniandes.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.logic.dto.DepartamentoUniandesDTO;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.logic.dto.DepartamentoUniandesPageDTO;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.persistence.api._IDepartamentoUniandesPersistence;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.persistence.converter.DepartamentoUniandesConverter;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.persistence.entity.DepartamentoUniandesEntity;

public abstract class _DepartamentoUniandesPersistence implements _IDepartamentoUniandesPersistence {

  	@PersistenceContext(unitName="internacionalizacionPU")
 
	protected EntityManager entityManager;
	
	public DepartamentoUniandesDTO createDepartamentoUniandes(DepartamentoUniandesDTO departamentoUniandes) {
		DepartamentoUniandesEntity entity=DepartamentoUniandesConverter.persistenceDTO2Entity(departamentoUniandes);
		entityManager.persist(entity);
		return DepartamentoUniandesConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<DepartamentoUniandesDTO> getDepartamentoUniandess() {
		Query q = entityManager.createQuery("select u from DepartamentoUniandesEntity u");
		return DepartamentoUniandesConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public DepartamentoUniandesPageDTO getDepartamentoUniandess(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from DepartamentoUniandesEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from DepartamentoUniandesEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		DepartamentoUniandesPageDTO response = new DepartamentoUniandesPageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(DepartamentoUniandesConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public DepartamentoUniandesDTO getDepartamentoUniandes(Long id) {
		return DepartamentoUniandesConverter.entity2PersistenceDTO(entityManager.find(DepartamentoUniandesEntity.class, id));
	}

	public void deleteDepartamentoUniandes(Long id) {
		DepartamentoUniandesEntity entity=entityManager.find(DepartamentoUniandesEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateDepartamentoUniandes(DepartamentoUniandesDTO detail) {
		DepartamentoUniandesEntity entity=entityManager.merge(DepartamentoUniandesConverter.persistenceDTO2Entity(detail));
		DepartamentoUniandesConverter.entity2PersistenceDTO(entity);
	}

}