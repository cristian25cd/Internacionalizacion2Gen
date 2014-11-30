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

package co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.dto.MateriaUniandesDTO;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.dto.MateriaUniandesPageDTO;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.api._IMateriaUniandesLogicService;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence.api.IMateriaUniandesPersistence;

public abstract class _MateriaUniandesLogicService implements _IMateriaUniandesLogicService {

	@Inject
	protected IMateriaUniandesPersistence persistance;

	public MateriaUniandesDTO createMateriaUniandes(MateriaUniandesDTO materiaUniandes){
		return persistance.createMateriaUniandes( materiaUniandes); 
    }

	public List<MateriaUniandesDTO> getMateriaUniandess(){
		return persistance.getMateriaUniandess(); 
	}

	public MateriaUniandesPageDTO getMateriaUniandess(Integer page, Integer maxRecords){
		return persistance.getMateriaUniandess(page, maxRecords); 
	}

	public MateriaUniandesDTO getMateriaUniandes(Long id){
		return persistance.getMateriaUniandes(id); 
	}

	public void deleteMateriaUniandes(Long id){
	    persistance.deleteMateriaUniandes(id); 
	}

	public void updateMateriaUniandes(MateriaUniandesDTO materiaUniandes){
	    persistance.updateMateriaUniandes(materiaUniandes); 
	}	
}