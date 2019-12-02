// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import iView from 'iview';// 引入iview依赖
import 'iview/dist/styles/iview.css'; // 引入iview css样式
import store from '@/vuex/store';
/* import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css' */

//每一个Vuex应用的核心就是store(仓库),store基本上就是一个容器，它包含着你的应用中大部分的状态 (state)
//state, 共同维护的一个状态，state里面可以是很多个全局状态
//getters,获取数据并渲染
//actions,数据的异步操作
//mutations处理数据的唯一途径，state的改变或赋值只能在这里


Vue.use(iView);//使用iview组件
/* Vue.use(ElementUI) */
Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
  iView.LoadingBar.start();
  next();
});

router.afterEach(route => {
  iView.LoadingBar.finish();
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,//在main.js中导入store实例
  components: { App },
  template: '<App/>'
});
