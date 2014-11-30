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

package co.edu.uniandes.csw.RoyalSystem.cliente.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.RoyalSystem.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.RoyalSystem.cliente.logic.dto.ClientePageDTO;
import co.edu.uniandes.csw.RoyalSystem.cliente.logic.api._IClienteLogicService;

public abstract class _ClienteMockLogicService implements _IClienteLogicService {

	private Long id= new Long(1);
	protected List<ClienteDTO> data=new ArrayList<ClienteDTO>();

	public ClienteDTO createCliente(ClienteDTO cliente){
		id++;
		cliente.setId(id);
		data.add(cliente);
		return cliente;
    }
    
    public List<ClienteDTO> getClientes(){
		return data; 
	}

	public ClientePageDTO getClientes(Integer page, Integer maxRecords){
		ClientePageDTO response = new ClientePageDTO();
		response.setTotalRecords(Long.parseLong(data.size()+""));
		response.setRecords(data);
		return response;
	}

	public ClienteDTO getCliente(Long id){
		for(ClienteDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteCliente(Long id){
	    ClienteDTO delete=null;
		for(ClienteDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateCliente(ClienteDTO cliente){
	    ClienteDTO delete=null;
		for(ClienteDTO data1:data){
			if(data1.getId().equals(cliente.getId())){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(cliente);
		} 
	}	
}