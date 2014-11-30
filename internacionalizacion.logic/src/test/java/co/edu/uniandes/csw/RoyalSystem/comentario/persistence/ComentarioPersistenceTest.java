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

package co.edu.uniandes.csw.RoyalSystem.comentario.persistence;

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


import co.edu.uniandes.csw.RoyalSystem.comentario.logic.dto.ComentarioPageDTO;
import co.edu.uniandes.csw.RoyalSystem.comentario.logic.dto.ComentarioDTO;
import co.edu.uniandes.csw.RoyalSystem.comentario.persistence.api.IComentarioPersistence;
import co.edu.uniandes.csw.RoyalSystem.comentario.persistence.entity.ComentarioEntity;
import co.edu.uniandes.csw.RoyalSystem.comentario.persistence.converter.ComentarioConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class ComentarioPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ComentarioPersistence.class.getPackage())
				.addPackage(ComentarioEntity.class.getPackage())
				.addPackage(IComentarioPersistence.class.getPackage())
                .addPackage(ComentarioDTO.class.getPackage())
                .addPackage(ComentarioConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IComentarioPersistence comentarioPersistence;

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
		em.createQuery("delete from ComentarioEntity").executeUpdate();
	}

	private List<ComentarioEntity> data=new ArrayList<ComentarioEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ComentarioEntity entity=new ComentarioEntity();
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createComentarioTest(){
		ComentarioDTO dto=new ComentarioDTO();
		dto.setName(generateRandom(String.class));
		
		ComentarioDTO result=comentarioPersistence.createComentario(dto);
		
		Assert.assertNotNull(result);
		
		ComentarioEntity entity=em.find(ComentarioEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());
	}
	
	@Test
	public void getComentariosTest(){
		List<ComentarioDTO> list=comentarioPersistence.getComentarios();
		Assert.assertEquals(list.size(), data.size());
        for(ComentarioDTO dto:list){
        	boolean found=false;
            for(ComentarioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getComentarioTest(){
		ComentarioEntity entity=data.get(0);
		ComentarioDTO dto=comentarioPersistence.getComentario(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deleteComentarioTest(){
		ComentarioEntity entity=data.get(0);
		comentarioPersistence.deleteComentario(entity.getId());
        ComentarioEntity deleted=em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateComentarioTest(){
		ComentarioEntity entity=data.get(0);
		
		ComentarioDTO dto=new ComentarioDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		
		
		comentarioPersistence.updateComentario(dto);
		
		
		ComentarioEntity resp=em.find(ComentarioEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
	}
	
	@Test
	public void getComentarioPaginationTest(){
		//Page 1
		ComentarioPageDTO dto1=comentarioPersistence.getComentarios(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        ComentarioPageDTO dto2=comentarioPersistence.getComentarios(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(ComentarioDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(ComentarioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(ComentarioDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(ComentarioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}