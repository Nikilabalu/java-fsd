import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import axios from "axios"
const AddUser = () => {
    const [name, setName] = useState(undefined)
    const [email, setEmail] = useState(undefined)
    const [phone, setPhone] = useState(undefined)
    const [companyName, setCompanyName] = useState(undefined)
    const [successMsg, setSuccessMsg] = useState(undefined)
    const [errorMsg, setErrorMsg] = useState(undefined)
    const navigate=useNavigate()
    const api = "https://jsonplaceholder.typicode.com/users"

    const addUsers = async (e) => {
        e.preventDefault()
        try {
            const response = await axios.post(api, {
                "name": name,
                "email": email,
                "phone": phone,
                "companyName": companyName
            })
            console.log(response.data) 
            setSuccessMsg("User added successfully!")
            //navigate("/users")
            
        }
        catch (err) {
            setErrorMsg("Cannot add user")
        }
    }
    return (
        <div className="container">
            <div>
                <Link to="/users" className="btn btn-outline-light me-2">User List</Link>
                <Link to="/add-user" className="btn btn-outline-light">Add User</Link>
            </div>
            <div className="card">
                <div className="card-header">Add user</div>
                <div className="card-body">
                    {
                        errorMsg==undefined?" ":
                        <div className="alert alert-danger mt-4">
                          {errorMsg}
                        </div>
                    }
                    {
                        successMsg==undefined?" ":
                        <div className="alert alert-primary mt-4">
                           {successMsg}
                        </div>
                    }
                    <form onSubmit={(e)=>addUsers(e)}>
                        <div className="mt-4">
                            <label>Name:</label>
                            <input type="text" className="form-control" required="required"
                              onChange={(e)=>setName(e.target.value)}/>
                        </div>
                        <div className="mt-4">
                            <label>Email:</label>
                            <input type="email" className="form-control" required="required"
                              onChange={(e)=>setEmail(e.target.value)}/>
                        </div>
                        <div className="mt-4">
                            <label>Phone:</label>
                            <input type="text" className="form-control" 
                            required="required" onChange={(e)=>setPhone(e.target.value)}/>
                        </div>
                        <div className="mt-4">
                            <label>Company Name:</label>
                            <input type="text" className="form-control"required="required"
                            onChange={(e)=>setCompanyName(e.target.value)}/>
                        </div>
                        <div className="mt-4">
                            <input type="submit" className="btn btn-primary" value="Add User"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}
export default AddUser