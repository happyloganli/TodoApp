import { apiClient } from "./apiClient";
import config from "bootstrap/js/src/util/config";

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicAuthenticate`
    ,{
        headers: {
            Authorization: token
        }
    })

export const executeJwtAuthenticationService
    = (username, password, token) =>
        apiClient.post(`/authenticate`,{username,password}, {
          headers: {
            Authorization: token
          }
        })
