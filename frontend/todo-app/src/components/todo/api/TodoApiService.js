import { apiClient } from './apiClient'

export const retrieveAllTodosForUsernameApi
    = (username) => apiClient.get(`/todos/user/${username}`)

export const deleteTodoApi
    = (id) => apiClient.delete(`/todos/${id}`)

export const retrieveTodoApi
    = (id) => apiClient.get(`/todos/${id}`)

export const updateTodoApi
    = (id, todo) => apiClient.put(`/todos/${id}`, todo)

export const createTodoApi
    = (todo) => apiClient.post(`/todos`, todo)
