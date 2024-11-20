import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      alias: ['/cree-compte', '/index.html'],  
      name: 'acount-create',
      component: () => import('../views/AcountCreateView.vue'),
    },
    {
      path: '/sign-in',
      name: 'sign-in',
      component: () => import('../views/SignInView.vue'),
    },
    {
      path: '/:notFound',
      name: '404',
      component: () => import('../views/404View.vue'),
    },
  ],
})

export default router
