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

header {
 position: fixed;
 top: 0; 
}

.app {
  min-height: 100vh;
}
</style>
