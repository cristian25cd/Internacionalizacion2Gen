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

package co.edu.uniandes.csw.RoyalSystem.homologacion.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.RoyalSystem.homologacion.logic.dto.HomologacionDTO;
import co.edu.uniandes.csw.RoyalSystem.homologacion.logic.dto.HomologacionPageDTO;
import co.edu.uniandes.csw.RoyalSystem.homologacion.persistence.api._IHomologacionPersistence;
import co.edu.uniandes.csw.RoyalSystem.homologacion.persistence.converter.HomologacionConverter;
import co.edu.uniandes.csw.RoyalSystem.homologacion.persistence.entity.HomologacionEntity;

public abstract class _HomologacionPersistence implements _IHomologacionPersistence {

  	@PersistenceContext(unitName="internacionalizacionPU")
 
	protected EntityManager entityManager;
	
	public HomologacionDTO createHomologacion(HomologacionDTO homologacion) {
		HomologacionEntity entity=HomologacionConverter.persistenceDTO2Entity(homologacion);
		entityManager.persist(entity);
		return HomologacionConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<HomologacionDTO> getHomologacions() {
		Query q = entityManager.createQuery("select u from HomologacionEntity u");
		return HomologacionConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public HomologacionPageDTO getHomologacions(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from HomologacionEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from HomologacionEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		HomologacionPageDTO response = new HomologacionPageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(HomologacionConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public HomologacionDTO getHomologacion(Long id) {
		return HomologacionConverter.entity2PersistenceDTO(entityManager.find(HomologacionEntity.class, id));
	}

	public void deleteHomologacion(Long id) {
		HomologacionEntity entity=entityManager.find(HomologacionEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateHomologacion(HomologacionDTO detail) {
		HomologacionEntity entity=entityManager.merge(HomologacionConverter.persistenceDTO2Entity(detail));
		HomologacionConverter.entity2PersistenceDTO(entity);
	}

}