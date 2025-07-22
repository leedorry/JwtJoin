window.onload = () => {

    const loginBtn = document.getElementById("login-btn");
    loginBtn.addEventListener("click", () => {
        login();
    })
}

async function login() {
    const param = {
        id : document.getElementById("id"),
        password: document.getElementById("password")
    }

    const data = await fetchCommon("/login", "POST", param);

    console.log(data);
}