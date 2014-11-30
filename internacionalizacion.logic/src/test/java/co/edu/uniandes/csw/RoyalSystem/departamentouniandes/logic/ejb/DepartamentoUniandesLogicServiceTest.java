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

package co.edu.uniandes.csw.RoyalSystem.departamentouniandes.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.logic.dto.DepartamentoUniandesPageDTO;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.logic.dto.DepartamentoUniandesDTO;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.logic.api.IDepartamentoUniandesLogicService;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.persistence.DepartamentoUniandesPersistence;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.persistence.api.IDepartamentoUniandesPersistence;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.persistence.entity.DepartamentoUniandesEntity;
import co.edu.uniandes.csw.RoyalSystem.departamentouniandes.persistence.converter.DepartamentoUniandesConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class DepartamentoUniandesLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(DepartamentoUniandesLogicService.class.getPackage())
				.addPackage(IDepartamentoUniandesLogicService.class.getPackage())
				.addPackage(DepartamentoUniandesPersistence.class.getPackage())
				.addPackage(DepartamentoUniandesEntity.class.getPackage())
				.addPackage(IDepartamentoUniandesPersistence.class.getPackage())
                .addPackage(DepartamentoUniandesDTO.class.getPackage())
                .addPackage(DepartamentoUniandesConverter.class.getPackage())
                .addPackage(DepartamentoUniandesEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IDepartamentoUniandesLogicService departamentoUniandesLogicService;
	
	@Inject
	private IDepartamentoUniandesPersistence departamentoUniandesPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<DepartamentoUniandesDTO> dtos=departamentoUniandesPersistence.getDepartamentoUniandess();
		for(DepartamentoUniandesDTO dto:dtos){
			departamentoUniandesPersistence.deleteDepartamentoUniandes(dto.getId());
		}
	}

	private List<DepartamentoUniandesDTO> data=new ArrayList<DepartamentoUniandesDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			DepartamentoUniandesDTO pdto=new DepartamentoUniandesDTO();
			pdto.setName(generateRandom(String.class));
			pdto=departamentoUniandesPersistence.createDepartamentoUniandes(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createDepartamentoUniandesTest(){
		DepartamentoUniandesDTO ldto=new DepartamentoUniandesDTO();
		ldto.setName(generateRandom(String.class));
		
		
		DepartamentoUniandesDTO result=departamentoUniandesLogicService.createDepartamentoUniandes(ldto);
		
		Assert.assertNotNull(result);
		
		DepartamentoUniandesDTO pdto=departamentoUniandesPersistence.getDepartamentoUniandes(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getDepartamentoUniandessTest(){
		List<DepartamentoUniandesDTO> list=departamentoUniandesLogicService.getDepartamentoUniandess();
		Assert.assertEquals(list.size(), data.size());
        for(DepartamentoUniandesDTO ldto:list){
        	boolean found=false;
            for(DepartamentoUniandesDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getDepartamentoUniandesTest(){
		DepartamentoUniandesDTO pdto=data.get(0);
		DepartamentoUniandesDTO ldto=departamentoUniandesLogicService.getDepartamentoUniandes(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deleteDepartamentoUniandesTest(){
		DepartamentoUniandesDTO pdto=data.get(0);
		departamentoUniandesLogicService.deleteDepartamentoUniandes(pdto.getId());
        DepartamentoUniandesDTO deleted=departamentoUniandesPersistence.getDepartamentoUniandes(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateDepartamentoUniandesTest(){
		DepartamentoUniandesDTO pdto=data.get(0);
		
		DepartamentoUniandesDTO ldto=new DepartamentoUniandesDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		
		
		departamentoUniandesLogicService.updateDepartamentoUniandes(ldto);
		
		
		DepartamentoUniandesDTO resp=departamentoUniandesPersistence.getDepartamentoUniandes(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
	}
	
	@Test
	public void getDepartamentoUniandesPaginationTest(){
		
		DepartamentoUniandesPageDTO dto1=departamentoUniandesLogicService.getDepartamentoUniandess(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		DepartamentoUniandesPageDTO dto2=departamentoUniandesLogicService.getDepartamentoUniandess(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(DepartamentoUniandesDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(DepartamentoUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(DepartamentoUniandesDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(DepartamentoUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        DepartamentoUniandesPageDTO dto3=departamentoUniandesLogicService.getDepartamentoUniandess(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(DepartamentoUniandesDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(DepartamentoUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}