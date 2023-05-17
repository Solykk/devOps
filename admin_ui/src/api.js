import axios from "axios";

function api () {

    const api = axios.create({
        baseURL: process.env.REACT_APP_API_BASE_URL + "/api",
        withCredentials: false,
        headers: {
            'Access-Control-Allow-Origin' : '*',
            'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
        }
    });

    return {
        create: (message) => {
            return api.post('/create',{
                message: message
            })
        },
        find: (page) => {
            return api.get('/find', {
                params: {
                    page: page,
                    size: 20
                }
            })
        }
    }
}

export default api()
