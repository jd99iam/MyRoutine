import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/myPage',
    name: 'myPage',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/MypageView.vue')
  },
  {
    path: '/search',
    name: 'search',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/SearchView.vue')
  },
  {
    path: '/friends',
    name: 'friends',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/FriendView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  },
  {
    path: '/singup',
    name: 'signup',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/SignUpView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
