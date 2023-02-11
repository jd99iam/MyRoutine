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
    path: '/signup',
    name: 'signup',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/SignUpView.vue')
  },
  {
    path: '/routinePatch',
    name: 'routinePatch',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/RoutinePatchView.vue')
  },
  {
    path: '/routinePost',
    name: 'routinePost',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/RoutinePostView.vue')
  },
  {
    path: '/routineCopy',
    name: 'routineCopy',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/RoutineCopyView.vue')
  },
  {
    path: '/imageTest',
    name: 'imageTest',
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/ImageTestView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
