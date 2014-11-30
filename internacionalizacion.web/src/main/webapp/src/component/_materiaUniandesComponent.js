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
define(['component/_CRUDComponent', 'model/materiaUniandesModel', 'controller/materiaUniandesController'], function() {
    App.Component._MateriaUniandesComponent = App.Component._CRUDComponent.extend({
        name: 'materiaUniandes',
        modelClass: App.Model.MateriaUniandesModel,
        listModelClass: App.Model.MateriaUniandesList,
        controller : App.Controller.MateriaUniandesController,
        configUI: function(){
        	this.listComponent.addColumn('name','Name');
        	this.listComponent.addColumn('codigo','Codigo');
        	this.listComponent.addColumn('correo','Correo');
        	this.listComponent.addColumn('profesor','Profesor');
        }
    });
    return App.Component._MateriaUniandesComponent;
});