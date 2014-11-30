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

package co.edu.uniandes.csw.RoyalSystem.pais.persistence;

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


import co.edu.uniandes.csw.RoyalSystem.pais.logic.dto.PaisPageDTO;
import co.edu.uniandes.csw.RoyalSystem.pais.logic.dto.PaisDTO;
import co.edu.uniandes.csw.RoyalSystem.pais.persistence.api.IPaisPersistence;
import co.edu.uniandes.csw.RoyalSystem.pais.persistence.entity.PaisEntity;
import co.edu.uniandes.csw.RoyalSystem.pais.persistence.converter.PaisConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class PaisPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(PaisPersistence.class.getPackage())
				.addPackage(PaisEntity.class.getPackage())
				.addPackage(IPaisPersistence.class.getPackage())
                .addPackage(PaisDTO.class.getPackage())
                .addPackage(PaisConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IPaisPersistence paisPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from PaisEntity").executeUpdate();
	}

	private List<PaisEntity> data=new ArrayList<PaisEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			PaisEntity entity=new PaisEntity();
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createPaisTest(){
		PaisDTO dto=new PaisDTO();
		dto.setName(generateRandom(String.class));
		
		PaisDTO result=paisPersistence.createPais(dto);
		
		Assert.assertNotNull(result);
		
		PaisEntity entity=em.find(PaisEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());
	}
	
	@Test
	public void getPaissTest(){
		List<PaisDTO> list=paisPersistence.getPaiss();
		Assert.assertEquals(list.size(), data.size());
        for(PaisDTO dto:list){
        	boolean found=false;
            for(PaisEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getPaisTest(){
		PaisEntity entity=data.get(0);
		PaisDTO dto=paisPersistence.getPais(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deletePaisTest(){
		PaisEntity entity=data.get(0);
		paisPersistence.deletePais(entity.getId());
        PaisEntity deleted=em.find(PaisEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updatePaisTest(){
		PaisEntity entity=data.get(0);
		
		PaisDTO dto=new PaisDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		
		
		paisPersistence.updatePais(dto);
		
		
		PaisEntity resp=em.find(PaisEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
	}
	
	@Test
	public void getPaisPaginationTest(){
		//Page 1
		PaisPageDTO dto1=paisPersistence.getPaiss(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        PaisPageDTO dto2=paisPersistence.getPaiss(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(PaisDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(PaisEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(PaisDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(PaisEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}