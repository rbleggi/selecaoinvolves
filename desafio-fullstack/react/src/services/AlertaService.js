import axios from "axios";

class AlertaService {
  _urlBase = `http://localhost:8080/alertas`;

  _axiosInstance = axios.create({
    baseURL: this._urlBase
  });

  buscar = (fieldName, fieldValue) => {
    let _url;
    if (fieldName === "todos") _url = `${this._urlBase}/`;
    else _url = `${this._urlBase}/?${fieldName}=${fieldValue}`;
    return this._axiosInstance
      .get(_url)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.log(error);
      });
  };

  remover = () => {
    return this._axiosInstance
      .delete(`${this._urlBase}/`)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.log(error);
      });
  };

  processar = () => {
    return this._axiosInstance
      .get(`${this._urlBase}/processar`)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.log(error);
      });
  };
}
export default AlertaService;
