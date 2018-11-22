import _ from 'lodash';

export default class ObjectJSONConverter {

  static _parserJSON(objModel, jsonObj) {
    jsonObj = _.pickBy(jsonObj, _.identity);
    let keys = _.intersection(_.keys(objModel), _.keys(jsonObj));
    jsonObj = _.pick(jsonObj, keys);
    return _.merge(objModel, jsonObj);
  }

  static _isObservableArray(jsonObj) {
    /** The ObservableArray class from MobX has the function toJS().
     ** To verify if the argument is an ObservableArray and do not import the specific class from mobx, we just verify if the function exists
     */
    return jsonObj && jsonObj.toJS;
  }

  static parserDeeplyJSON(objModel, jsonObj) {
    if (Array.isArray(jsonObj) || this._isObservableArray(jsonObj)) {
      return this._resolveArrayParse(objModel, jsonObj);
    } else {
      return this._resolveObjectParse(objModel, jsonObj);
    }
  }

  static _resolveArrayParse(objModel, jsonObj) {
    objModel = Array.from(jsonObj);
    return objModel;
  }

  static _resolveObjectParse(objModel, jsonObj) {
    let jsonObjClone = {};
    Object.assign(jsonObjClone, jsonObj);
    let keys = [];
    for (var k in objModel) {
      keys.push(k);
    }
    keys.forEach(key => {
      if (objModel[key] && objModel[key] instanceof Object && !Array.isArray(objModel[key])) {
        objModel[key] = this.parserDeeplyJSON(objModel[key], jsonObjClone[key]);
        delete jsonObjClone[key];
      }
    });
    return this._parserJSON(objModel, jsonObjClone);
  }
}
