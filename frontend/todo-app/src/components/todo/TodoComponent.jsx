import { useEffect, useState } from 'react'
import {useParams, useNavigate} from 'react-router-dom'
import { retrieveTodoApi, updateTodoApi, createTodoApi } from './api/TodoApiService'
import { useAuth } from './security/AuthContext'
import {Formik, Form, Field, ErrorMessage} from 'formik'
import moment from 'moment'

export default function TodoComponent() {
    
    const {id} = useParams()
    
    const[title, setTitle] = useState('')
    const[targetDate, setTargetDate] = useState('')
    const[createDate, setCreateDate] = useState('')
    const[status, setStatus] = useState('')
    const[showCreateInfo, setShowCreateInfo] = useState(false)

    const authContext = useAuth()
    const navigate = useNavigate()
    
    const username = authContext.username
    
    useEffect(
        () => retrieveTodos(),
        [id]
        )

    function retrieveTodos(){
        if(id != -1) {
            retrieveTodoApi(id)
            .then(response => {
                console.log(response.data)
                setTitle(response.data.title)
                setTargetDate(response.data.targetDate)
                setCreateDate(response.data.createDate)
                setStatus(response.data.status)
                setShowCreateInfo(true)
            })
            .catch(error => console.log(error))
        }
    }

    function onSubmit(values) {
        
        const todo = {
            username: username,
            title: values.title,
            targetDate: values.targetDate,
            status: `PROCESSING`
        }

        console.log(todo)

        if(id==-1) {
            createTodoApi(todo)
            .then(response => {
                navigate('/todos')
            })
            .catch(error => console.log(error))
    
        } else {
            updateTodoApi(id, todo)
            .then(response => {
                navigate('/todos')
            })
            .catch(error => console.log(error))
        }
    }

    function validate(values) {
        let errors = {

        }

        if(values.title.length<5) {
            errors.description = 'Enter at least 5 characters'
        }

        if(values.targetDate == null || values.targetDate=='' || !moment(values.targetDate).isValid()) {
            errors.targetDate = 'Enter a target date'
        }

        return errors
    }

    return (
        <div className="container">
            <h1>Enter Todo Details </h1>
            <div>
                <Formik initialValues={ { title, targetDate, status, createDate } }
                    enableReinitialize = {true}
                    onSubmit = {onSubmit}
                    validate = {validate}
                    validateOnChange = {false}
                    validateOnBlur = {false}
                >
                {
                    (props) => (
                        <Form>
                            <ErrorMessage
                                name="title"
                                component="div"
                                className="alert alert-warning"
                            />

                            <ErrorMessage
                                name="targetDate"
                                component="div"
                                className="alert alert-warning"
                            />

                            <fieldset className="form-group">
                                <label>Title</label>
                                <Field type="text" className="form-control"
                                       name="title"/>
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Target Date</label>
                                <Field type="date" className="form-control"
                                       name="targetDate"/>
                            </fieldset>
                            {showCreateInfo && (
                                <div>
                                    <fieldset className="form-group">
                                        <label>Status</label>
                                        <Field type="text" className="form-control"
                                               name="status" readOnly />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Create Date</label>
                                            <Field type="date" className="form-control"name="createDate" readOnly />
                                    </fieldset>
                                </div>
                            )}
                            <div>
                                <button className="btn btn-success m-5"
                                        type="submit">Save
                                </button>
                            </div>
                        </Form>
                    )
                }
                </Formik>
            </div>

        </div>
    )
}