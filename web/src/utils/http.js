import Vue from 'vue'

const BASE_URL = 'http://www.cog.codes/api'

const http = ({ url, method, data }) => {
    let u = BASE_URL + url
    const m = method.toUpperCase()
    const params = {
        headers: {
            'content-type': 'application/json'
        },
        method: m
    }
    if (m === 'GET' || m === 'DELETE') {
        let q = new URLSearchParams()
        for (let key in data) {
            q.append(key, data[key])
        }
        u = `${u}${u.includes('?') ? '&' : '?'}${q.toString()}`
    } else {
        params.body = JSON.stringify(data)
    }
    let isOk = false
    return fetch(u, params).then(async res => {
        isOk = res.ok
        let ret = {}
        try {
            ret = await res.json()
        } catch (error) {

        }
        return ret
    }).then(res => {
        if (isOk) {
            return res
        } else {
            Vue.prototype.$Notify({
                title: 'Oops',
                message: res.message || 'Something went Wrong',
                type: 'error'
            })
            throw Error(res.message)
        }
    })
}
export default http