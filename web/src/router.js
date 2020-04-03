import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import Home from './views/Home.vue'
import My from './views/My.vue'
import Friends from './views/Friends.vue'
import Forum from './views/Forum.vue'
import Page from './views/Page.vue'
import Game from './views/Game.vue'
import Account from './views/Account.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'login',
            component: Login
        },
        {
            path: '/register',
            name: 'register',
            component: Register
        },
        {
            path: '/page',
            name: 'page',
            component: Page,
            redirect: '/page/home',
            children: [
                {
                    path: '/page/home',
                    name: 'home',
                    component: Home
                },
                {
                    path: '/page/game',
                    name: 'game',
                    component: Game
                },
                {
                    path: '/page/my',
                    name: 'my',
                    component: My
                },
                {
                    path: '/page/account',
                    name: 'account',
                    component: Account
                },
                {
                    path: '/page/friends',
                    name: 'friends',
                    component: Friends
                },
                {
                    path: '/page/forum',
                    name: 'forum',
                    component: Forum
                }
            ]
        },
        {
            path: '*',
            redirect: '/'
        }
    ]
})
