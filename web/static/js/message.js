let conversationManager = {
    "currConversation" : null,
    "select" : function (counterpartName) {
        let conversation = document.querySelector('#con_' + counterpartName);
        let toggle = function (conversation) {
            if(conversation.style.display == 'none') {
                conversation.style.display = 'block';
            } else {
                conversation.style.display = 'none';
            }
        };
        /** 현재 대화가 있으면 꺼줌 */
        if(this.currConversation) {
           toggle(this.currConversation)
        }

        toggle(conversation);
    }
}

const counterpartBoxList = document.querySelectorAll('.cp_box');

for(let counterpartBox of counterpartBoxList) {
    counterpartBox.addEventListener('click', (e) => {
        conversationManager.select(counterpartBox.getAttribute('cpname'));
    })
}

const axios = window.axios;
const unsubscribeBtn = document.querySelector('#unsubscribeBtn');
const userName = document.querySelector('#userName').textContent;
unsubscribeBtn.addEventListener('click', (e) => {
    e.preventDefault();
    e.stopPropagation();

    const url = "/users/" + userName + "/messages";
    // const params = new URLSearchParams();
    // params.append('counterpartName', unsubscribeBtn.parentElement.getAttribute("cpname"));
    //
    // const config = {
    //     headers: {
    //         'Content-Type': "application/x-www-form-urlencoded; charset=UTF-8;"
    //     }
    // }
    axios.put(
        url,
        {'counterpartName' : unsubscribeBtn.parentElement.getAttribute("cpname")}
    ).then(res => {
        console.log(res);
        if(res.data.error) {
            alert(res.data.message);
        }
        window.location = res.data.redirect;
    })

})

const msgSendBtn = document.querySelector('#message_send_btn');
msgSendBtn.addEventListener('click', e => {
    e.preventDefault();

    const counterpartName = msgSendBtn.parentElement.getAttribute("cpname");
    const url = '/users/' + counterpartName + '/messages';
    const content = document.querySelector("#message_textarea").value;

    axios.defaults.xsrfCookieName = 'XSRF-TOKEN';
    axios.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';
    axios.post(
        url,
        { 'content' : content }
    ).then( res => {
        console.log(res);
        if(res.data.error) {
            alert(res.data.message);
        }
        window.location = res.data.redirect;
    })
})