import Vue from 'vue'
import App from './App.vue'

import router from './router'

// 下面俩是element-ui需要用到的
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import './assets/gloable.css'
import request from "@/utils/request";

Vue.config.productionTip = false

Vue.use(ElementUI, { size: "small" });

Vue.prototype.request=request

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
