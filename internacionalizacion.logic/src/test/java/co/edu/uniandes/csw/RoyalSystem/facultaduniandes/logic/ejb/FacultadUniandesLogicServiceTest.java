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

package co.edu.uniandes.csw.RoyalSystem.facultaduniandes.logic.ejb;

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


import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.logic.dto.FacultadUniandesPageDTO;
import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.logic.dto.FacultadUniandesDTO;
import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.logic.api.IFacultadUniandesLogicService;
import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.persistence.FacultadUniandesPersistence;
import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.persistence.api.IFacultadUniandesPersistence;
import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.persistence.entity.FacultadUniandesEntity;
import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.persistence.converter.FacultadUniandesConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class FacultadUniandesLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(FacultadUniandesLogicService.class.getPackage())
				.addPackage(IFacultadUniandesLogicService.class.getPackage())
				.addPackage(FacultadUniandesPersistence.class.getPackage())
				.addPackage(FacultadUniandesEntity.class.getPackage())
				.addPackage(IFacultadUniandesPersistence.class.getPackage())
                .addPackage(FacultadUniandesDTO.class.getPackage())
                .addPackage(FacultadUniandesConverter.class.getPackage())
                .addPackage(FacultadUniandesEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IFacultadUniandesLogicService facultadUniandesLogicService;
	
	@Inject
	private IFacultadUniandesPersistence facultadUniandesPersistence;	

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
		List<FacultadUniandesDTO> dtos=facultadUniandesPersistence.getFacultadUniandess();
		for(FacultadUniandesDTO dto:dtos){
			facultadUniandesPersistence.deleteFacultadUniandes(dto.getId());
		}
	}

	private List<FacultadUniandesDTO> data=new ArrayList<FacultadUniandesDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			FacultadUniandesDTO pdto=new FacultadUniandesDTO();
			pdto.setName(generateRandom(String.class));
			pdto=facultadUniandesPersistence.createFacultadUniandes(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createFacultadUniandesTest(){
		FacultadUniandesDTO ldto=new FacultadUniandesDTO();
		ldto.setName(generateRandom(String.class));
		
		
		FacultadUniandesDTO result=facultadUniandesLogicService.createFacultadUniandes(ldto);
		
		Assert.assertNotNull(result);
		
		FacultadUniandesDTO pdto=facultadUniandesPersistence.getFacultadUniandes(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getFacultadUniandessTest(){
		List<FacultadUniandesDTO> list=facultadUniandesLogicService.getFacultadUniandess();
		Assert.assertEquals(list.size(), data.size());
        for(FacultadUniandesDTO ldto:list){
        	boolean found=false;
            for(FacultadUniandesDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getFacultadUniandesTest(){
		FacultadUniandesDTO pdto=data.get(0);
		FacultadUniandesDTO ldto=facultadUniandesLogicService.getFacultadUniandes(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deleteFacultadUniandesTest(){
		FacultadUniandesDTO pdto=data.get(0);
		facultadUniandesLogicService.deleteFacultadUniandes(pdto.getId());
        FacultadUniandesDTO deleted=facultadUniandesPersistence.getFacultadUniandes(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateFacultadUniandesTest(){
		FacultadUniandesDTO pdto=data.get(0);
		
		FacultadUniandesDTO ldto=new FacultadUniandesDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		
		
		facultadUniandesLogicService.updateFacultadUniandes(ldto);
		
		
		FacultadUniandesDTO resp=facultadUniandesPersistence.getFacultadUniandes(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
	}
	
	@Test
	public void getFacultadUniandesPaginationTest(){
		
		FacultadUniandesPageDTO dto1=facultadUniandesLogicService.getFacultadUniandess(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		FacultadUniandesPageDTO dto2=facultadUniandesLogicService.getFacultadUniandess(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(FacultadUniandesDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(FacultadUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(FacultadUniandesDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(FacultadUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        FacultadUniandesPageDTO dto3=facultadUniandesLogicService.getFacultadUniandess(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(FacultadUniandesDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(FacultadUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}