const sayHiBtn = document.querySelector('#sayHi');
const axios = window.axios;

sayHiBtn.addEventListener('click', (e) => {
    const userName = document.querySelector('#userName').textContent;
    const url = '/users/' + userName + '/messages';

    axios.defaults.xsrfCookieName = 'XSRF-TOKEN';
    axios.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';
    axios.post(
        url,
        { 'content' : "안녕하세요 :)" }
    ).then( res => {
        console.log(res);
        if(res.data.error) {
            alert(res.data.message);
        }
        window.location = res.data.redirect;
    })
})