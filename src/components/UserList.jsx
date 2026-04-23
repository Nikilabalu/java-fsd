import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { Link } from "react-router-dom"
import axios from "axios"


const UserList = () => {

    const [users, setUsers] = useState([])
    const [successMsg, setSuccessMsg] = useState(undefined)
    const [errorMsg, setErrorMsg] = useState(undefined)
    const navigate = useNavigate()
    const getapi = "https://jsonplaceholder.typicode.com/users"
    const deleteApi = "https://jsonplaceholder.typicode.com/users/"


    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.get(getapi)
                setUsers(response.data)
            }
            catch (err) {
                setErrorMsg(err.message)
                navigate("/")
            }
        }
        fetchUsers()
    }, [])

    const deleteUser = async (id) => {
        try {
            await axios.delete(deleteApi + id)
            setUsers(users.filter((p) => p.id !== id))
            console.log("deleted")
        }
        catch (err) {
            setErrorMsg(err.message)
        }
    }
    return (
        <div className="container">
            <div className="row mt-4">
                <div className="col mg-8">
                    <div>
                        <Link to="/users" className="btn btn-outline-light me-2">User List</Link>
                        <Link to="/add-user" className="btn btn-outline-light">Add User</Link>
                    </div>
                    <div className="card">
                        <div className="card-header">Users List </div>
                        <div className="card-body">
                            <table className="table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Phone</th>
                                        <th>Company Name</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    {users.map((p, id) => (
                                        <tr key={id}>
                                            <th scope="row">{p.id}</th>
                                            <td>{p.name}</td>
                                            <td>{p.email}</td>
                                            <td>{p.phone}</td>
                                            <td>{p.company.name}</td>
                                            <td>
                                                <button className="btn btn-primary"
                                                    onClick={() => deleteUser(p.id)}>
                                                    Delete
                                                </button>
                                            </td>
                                        </tr>
                                    ))
                                    }

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )

}
export default UserList