<script>
import axiosClient from '@/services/axiosClient';

    export default {
        data() {
            return {
                inputs : {
                    userName: "",
                    password: ""
                }
            }
        },
        methods: {
            async submit() {
                const response = await axiosClient.post("/accounts/sign-in", this.inputs)
                if(response.status === 200) {
                    sessionStorage.setItem("token", response.data);
                    alert(`Authenticated`);
                } else if (response.status === 401) {
                    alert(`Bad credencials`);
                } else {
                    alert(`Oups ! Unexpected error`);
                }
            }
        }
    }
</script>

<template>
    <div class="container">
        <h1>Connection</h1>
        <form @submit.prevent="submit" novalidate>
            <label for="user-name">Email</label>
            <input v-model="inputs.userName" type="email" id="user-name" name="user-name">
            <label for="password">Password</label>
            <input v-model="inputs.password" type="password" id="password" name="password">
            <button type="submit">Connection</button>
        </form>
    </div>
</template>

<style scoped>
input, label {
    display:block;
}
input {
    margin-bottom: 1rem;
}
h1 {
    color: blue;
}
label::after {
    content: " *";
    color: red;
}
</style>