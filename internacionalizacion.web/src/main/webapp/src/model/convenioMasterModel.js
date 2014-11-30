define(['model/_convenioMasterModel'], function() { 
    App.Model.ConvenioMasterModel = App.Model._ConvenioMasterModel.extend({

    });

    App.Model.ConvenioMasterList = App.Model._ConvenioMasterList.extend({
        model: App.Model.ConvenioMasterModel
    });

    return  App.Model.ConvenioMasterModel;

});