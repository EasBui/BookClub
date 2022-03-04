const axios = window.axios;
axios.defaults.xsrfCookieName = 'XSRF-TOKEN';
axios.defaults.xsrfHeaderName = 'X-XSRF-TOKEN';

const approveSignUpBtns = document.querySelectorAll('.approve');
const rejectSignUpBtns = document.querySelectorAll('.reject');
const clubName = document.querySelector('#clubName').textContent;

if(approveSignUpBtns.length > 0 && rejectSignUpBtns.length > 0) {
    const signUpDecisionUrl='/clubs/'+clubName+'/member';
    approveSignUpBtns.forEach(btn => btn.addEventListener('click', e => {
        e.preventDefault();
        axios.post(
            signUpDecisionUrl,
            {
                'isApproved': true,
                'preMemberName': btn.getAttribute('target')
            }
        ).then( res => {
            alert(res.data.message);
            window.location.replace(location.href);
        })
    }))

    rejectSignUpBtns.forEach(btn => btn.addEventListener('click', e => {
        e.preventDefault();
        axios.post(
            signUpDecisionUrl,
            {
                'isApproved': false,
                'preMemberName': btn.getAttribute('target')
            }
        ).then( res => {
            alert(res.data.message);
            window.location.replace(window.location.href);
        })
    }))
}

const signUpBtn = document.querySelector('.signUp');
if(signUpBtn) {
    const signUpUrl ='/clubs/' + clubName + '/sign-up';
    signUpBtn.addEventListener('click', e => {
        e.preventDefault();
        axios.post(
            signUpUrl,
            {}
        ).then( res => {
            alert(res.data.message);
            window.location.replace(window.location.href);
        })
    })
}
