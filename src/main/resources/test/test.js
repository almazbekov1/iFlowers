 import img from './haha.png'
 // FormData.
let response = await fetch(url, {
    method: "POST",
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        email: email,
        password: password,
        firstName: firstName,
        lastName: lastName,
        age: age,
        role: [
            {
                id: 1,
                name: 'ROLE_ADMIN'
            }
        ],
        image:img
    })


})
addUser()
