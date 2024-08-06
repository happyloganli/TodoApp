import { createContext, useContext, useState } from "react";
import { executeBasicAuthenticationService } from "../api/AuthenticationApiService";
import {apiClient} from "../api/apiClient";

// Base Auth
export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

//2: Share the created context with other components
export default function AuthProvider({ children }) {

    //3: Put some state in the context
    const [isAuthenticated, setAuthenticated] = useState(false)

    const [username, setUsername] = useState(null)

    const [token, setToken] = useState(null)



    async function login(username, password) {

        const token = 'Basic ' + window.btoa(username + ":" + password)

        try {

            const response = await executeBasicAuthenticationService(token)

            if (response.status == 200) {
                setAuthenticated(true)
                setUsername(username)
                setToken(token)

                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = token
                        return config
                    }
                )

                return true
            } else {
                logout()
                return false
            }
        } catch (error) {
            logout()
            return false
        }
    }


    function logout() {
        setAuthenticated(false)
        setToken(null)
        setUsername(null)
    }

    return (
        <AuthContext.Provider value={ {isAuthenticated, login, logout, username, token}  }>
            {children}
        </AuthContext.Provider>
    )
} 