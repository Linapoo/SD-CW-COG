import Vue from 'vue'
import App from './App.vue'
import router from './router'
import User from './components/User.vue'
import AtComponents from 'at-ui'

import 'at-ui-style'    // 引入组件样式

import $http from './utils/http'

Vue.use(AtComponents)
Vue.component('User', User)
Vue.config.productionTip = false
Vue.prototype.$http = $http


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
