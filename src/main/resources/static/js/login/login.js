window.onload = () => {

    const loginBtn = document.getElementById("login-btn");

    loginBtn.addEventListener("click", async (event) => {
        event.preventDefault()
        await login();
    })

}

async function login() {
    const param = {
        username : document.getElementById("username").value,
        password: document.getElementById("password").value
    }

    try {
        const response = await fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams(param),
            credentials: 'include'
        });

        if (response.ok)
            window.location.href = '/index';
        else
           throw new Error(`HTTP Error: ${response.status}`)

    } catch(err) {
        console.error("Fetch Error: ", err.message)
    }

}