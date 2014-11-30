define(['model/_experienciaMasterModel'], function() { 
    App.Model.ExperienciaMasterModel = App.Model._ExperienciaMasterModel.extend({

    });

    App.Model.ExperienciaMasterList = App.Model._ExperienciaMasterList.extend({
        model: App.Model.ExperienciaMasterModel
    });

    return  App.Model.ExperienciaMasterModel;

});