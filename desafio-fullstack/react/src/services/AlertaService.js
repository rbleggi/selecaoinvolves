import axios from 'axios';

class AlertaService {
    _urlBase = `http://localhost:8080/alertas`;

    _axiosInstance = axios.create({
        baseURL: this._urlBase
    });

    findById = (id) => {
        const _url = `${this._urlBase}/${id}`;
        return this._axiosInstance.get(_url).then(response => {
            return response.data
        }).catch(error => {
            console.log(error);
        });
    }

    findAll = (fieldName, fieldValue) => {
        let _url;
        if (fieldName !== 'todos')
            _url = `${this._urlBase}/?${fieldName}=${fieldValue}`;
        else
            _url = `${this._urlBase}/`;
        return this._axiosInstance.get(_url).then(response => {
            return response.data
        }).catch(error => {
            console.log(error);
        });
    }

}
export default AlertaService;