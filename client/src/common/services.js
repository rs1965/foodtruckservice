import axios from 'axios';

class Services {
    static getApi(url) {
        return axios.get(url, {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Method': '*',
                'Accepts-Version': '1'
            }
        })
    }
    static deleteApi(url) {
        return axios.delete(url, {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Method': '*',
                'Accepts-Version': '1'
            }
        })
    }

    static deleteApi(url, data) {
        return axios.delete(url, {
            data: data,
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Method': '*',
                'Accepts-Version': '1'
            }
        })
    }

    static postApi(url, data) {
        return axios.post(url, data, {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Method': '*',
                'Accepts-Version': '1'
            }
        })
    }
    static putApi(url, data) {
        return axios.put(url, data, {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Method': '*',
                'Accepts-Version': '1'
            }
        })
    }
}

export default Services