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

package co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.logic.dto.TipoEstudianteDTO;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.logic.dto.TipoEstudiantePageDTO;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.api._ITipoEstudiantePersistence;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.converter.TipoEstudianteConverter;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.entity.TipoEstudianteEntity;

public abstract class _TipoEstudiantePersistence implements _ITipoEstudiantePersistence {

  	@PersistenceContext(unitName="internacionalizacionPU")
 
	protected EntityManager entityManager;
	
	public TipoEstudianteDTO createTipoEstudiante(TipoEstudianteDTO tipoEstudiante) {
		TipoEstudianteEntity entity=TipoEstudianteConverter.persistenceDTO2Entity(tipoEstudiante);
		entityManager.persist(entity);
		return TipoEstudianteConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoEstudianteDTO> getTipoEstudiantes() {
		Query q = entityManager.createQuery("select u from TipoEstudianteEntity u");
		return TipoEstudianteConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public TipoEstudiantePageDTO getTipoEstudiantes(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from TipoEstudianteEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from TipoEstudianteEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		TipoEstudiantePageDTO response = new TipoEstudiantePageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(TipoEstudianteConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public TipoEstudianteDTO getTipoEstudiante(Long id) {
		return TipoEstudianteConverter.entity2PersistenceDTO(entityManager.find(TipoEstudianteEntity.class, id));
	}

	public void deleteTipoEstudiante(Long id) {
		TipoEstudianteEntity entity=entityManager.find(TipoEstudianteEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateTipoEstudiante(TipoEstudianteDTO detail) {
		TipoEstudianteEntity entity=entityManager.merge(TipoEstudianteConverter.persistenceDTO2Entity(detail));
		TipoEstudianteConverter.entity2PersistenceDTO(entity);
	}

}