 

   ALTER TABLE APP.ExperienciaarchivoEntity ADD FOREIGN KEY (experienciaId) REFERENCES  APP.ExperienciaEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
   ALTER TABLE APP.ExperienciaarchivoEntity ADD FOREIGN KEY (archivoId) REFERENCES  APP.ArchivoEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
   ALTER TABLE APP.ExperienciacomentarioEntity ADD FOREIGN KEY (experienciaId) REFERENCES  APP.ExperienciaEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
   ALTER TABLE APP.ExperienciacomentarioEntity ADD FOREIGN KEY (comentarioId) REFERENCES  APP.ComentarioEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
		
   ALTER TABLE APP.ConveniohomologacionEntity ADD FOREIGN KEY (convenioId) REFERENCES  APP.ConvenioEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
   ALTER TABLE APP.ConveniohomologacionEntity ADD FOREIGN KEY (homologacionId) REFERENCES  APP.HomologacionEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
		
