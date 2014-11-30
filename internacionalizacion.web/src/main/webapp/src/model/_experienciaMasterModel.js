define([], function() {
    App.Model._ExperienciaMasterModel = Backbone.Model.extend({
     	initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('experiencia-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.ExperienciaModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.experienciaEntity,options);
            }
        }
    });

    App.Model._ExperienciaMasterList = Backbone.Collection.extend({
        model: App.Model._ExperienciaMasterModel,
        initialize: function() {
        }

    });
    return App.Model._ExperienciaMasterModel;
    
});