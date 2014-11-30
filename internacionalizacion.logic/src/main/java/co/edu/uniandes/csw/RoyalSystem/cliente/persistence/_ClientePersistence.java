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

package co.edu.uniandes.csw.RoyalSystem.cliente.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.RoyalSystem.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.RoyalSystem.cliente.logic.dto.ClientePageDTO;
import co.edu.uniandes.csw.RoyalSystem.cliente.persistence.api._IClientePersistence;
import co.edu.uniandes.csw.RoyalSystem.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.RoyalSystem.cliente.persistence.entity.ClienteEntity;

public abstract class _ClientePersistence implements _IClientePersistence {

  	@PersistenceContext(unitName="internacionalizacionPU")
 
	protected EntityManager entityManager;
	
	public ClienteDTO createCliente(ClienteDTO cliente) {
		ClienteEntity entity=ClienteConverter.persistenceDTO2Entity(cliente);
		entityManager.persist(entity);
		return ClienteConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<ClienteDTO> getClientes() {
		Query q = entityManager.createQuery("select u from ClienteEntity u");
		return ClienteConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public ClientePageDTO getClientes(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from ClienteEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from ClienteEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		ClientePageDTO response = new ClientePageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(ClienteConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public ClienteDTO getCliente(Long id) {
		return ClienteConverter.entity2PersistenceDTO(entityManager.find(ClienteEntity.class, id));
	}

	public void deleteCliente(Long id) {
		ClienteEntity entity=entityManager.find(ClienteEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateCliente(ClienteDTO detail) {
		ClienteEntity entity=entityManager.merge(ClienteConverter.persistenceDTO2Entity(detail));
		ClienteConverter.entity2PersistenceDTO(entity);
	}

}