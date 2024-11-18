<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
        <RouterLink to="/cree-compte">Crée un compte</RouterLink>
      </nav>
  </header>
  <RouterView />
</template>

<style scoped>

.app {
  min-height: 100vh;
}
</style>
