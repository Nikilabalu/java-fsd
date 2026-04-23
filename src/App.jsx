import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import UserList from './components/UserList.jsx'
import AddUser from './components/AddUser.jsx'

const routes = createBrowserRouter([
    {
        path: "/",
        element: <UserList />
    },
    {
        path: "/users",
        element: <UserList />
    },
    {
        path: "/add-user",
        element: <AddUser />
    }
])

const App = () => {
    return (
        <RouterProvider router={routes} />
    )
}

export default App