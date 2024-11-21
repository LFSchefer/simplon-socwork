<script>
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
                const options = {
                    method: 'POST',
                    headers: {
                        'content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.inputs)
                }
                const response = await fetch("http://localhost:8080/accounts/sign-in",options);
                if(response.status === 200) {
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