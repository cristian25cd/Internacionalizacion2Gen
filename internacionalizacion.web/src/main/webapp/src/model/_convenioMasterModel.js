define([], function() {
    App.Model._ConvenioMasterModel = Backbone.Model.extend({
     	initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('convenio-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.ConvenioModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.convenioEntity,options);
            }
        }
    });

    App.Model._ConvenioMasterList = Backbone.Collection.extend({
        model: App.Model._ConvenioMasterModel,
        initialize: function() {
        }

    });
    return App.Model._ConvenioMasterModel;
    
});